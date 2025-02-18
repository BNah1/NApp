package com.example.napp.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.napp.R;
import com.example.napp.data.model.Task;
import com.example.napp.data.sql.DatabaseManager;

import java.util.List;

public class TaskViewModel extends ViewModel {
    private MutableLiveData<List<Task>> listTaskLiveData;
    private List<Task> listTask;
    private MutableLiveData<String> text_name, text_description;
    private Context context;

    private DatabaseManager databaseManager;

    public MutableLiveData<String> getText_name() {
        return text_name;
    }

    public void setText_name(String text_name) {
        this.text_name.setValue(text_name);
    }

    public MutableLiveData<String> getText_description() {
        return text_description;
    }

    public void setText_description(String text_description) {
        this.text_description.setValue(text_description);
    }


    public TaskViewModel(Context context) {
        this.context = context.getApplicationContext();
        listTaskLiveData = new MutableLiveData<>();
        text_name = new MutableLiveData<>();
        text_description = new MutableLiveData<>();
        initData();
    }

    private void initData(){
        databaseManager = DatabaseManager.getInstance(this.context);
        listTask = databaseManager.getData();
//        listTask.add(new Task(R.drawable.ic_launcher_foreground,"Nah","DSDSADASDSADSA"));
        listTaskLiveData.setValue(listTask);
    }

    public MutableLiveData<List<Task>> getListTaskLiveData() {
        return listTaskLiveData;
    }

    public List<Task> getListTask() {
        return listTask;
    }

    public void addTask(){
        String inputTxtN = text_name.getValue();
        String inputTxtD = text_description.getValue();

        // gia tri mac dinh
        if(inputTxtN == null && inputTxtD == null){
            inputTxtD = " Null ";
            inputTxtN = " Null ";
        }

        Task task = new Task(R.drawable.buster, inputTxtN, inputTxtD, 0, null);
        listTask.add(task.getPosition(),task);
        databaseManager.addData(task);

        // cap nhap lai postion trong sqlite
        updateSQLITE();

        setText_name("");
        setText_description("");
        listTaskLiveData.setValue(listTask);
    }

    private void updateSQLITE() {
        for (Task task : listTask) {
            boolean success = databaseManager.updatePostion(task, listTask.indexOf(task));
            if (!success) {
                Log.d("TaskViewModel", "Cập nhật thất bại cho task: " + task.getId());
            }
        }
    }


    public void downTask(Task task){
        int i = listTask.indexOf(task);
        if(i< listTask.size()-1 && i != -1){
            Task tsk = listTask.get(i+1);
            listTask.set(i + 1, task);
            listTask.set(i,tsk);
        }

        // cap nhap lai postion trong sqlite
        updateSQLITE();

        listTaskLiveData.setValue(listTask);

    }

    public void upTask(Task task){
        int i = listTask.indexOf(task);
        if(i > 0){
            Task tsk = listTask.get(i-1);
            listTask.set(i - 1, task);
            listTask.set(i,tsk);
        }

        // cap nhap lai postion trong sqlite
        updateSQLITE();

        listTaskLiveData.setValue(listTask);

    }

    public void deleteTask(Task task){
        int i = listTask.indexOf(task);
        listTask.remove(i);
        listTaskLiveData.setValue(listTask);
    }
}
