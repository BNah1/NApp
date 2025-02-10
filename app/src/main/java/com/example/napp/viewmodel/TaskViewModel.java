package com.example.napp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.napp.R;
import com.example.napp.data.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends ViewModel {
    private MutableLiveData<List<Task>> listTaskLiveData;
    private List<Task> listTask;
    private MutableLiveData<String> text_name, text_description;

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


    public TaskViewModel() {
        listTaskLiveData = new MutableLiveData<>();
        text_name = new MutableLiveData<>();
        text_description = new MutableLiveData<>();
        initData();
    }

    private void initData(){
        listTask = new ArrayList<>();
        listTask.add(new Task(R.drawable.ic_launcher_foreground,"Nah","DSDSADASDSADSA"));
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
        if(inputTxtN == null && inputTxtD == null){
            inputTxtD = " Null ";
            inputTxtN = " Null ";
        }
        listTask.add(0,new Task(R.drawable.ic_launcher_foreground, inputTxtN, inputTxtD));
        setText_name("");
        setText_description("");
        listTaskLiveData.setValue(listTask);
    }

    public void downTask(Task task){
        int i = listTask.indexOf(task);
        if(i< listTask.size()-1 && i != -1){
            Task tsk = listTask.get(i+1);
            listTask.set(i + 1, task);
            listTask.set(i,tsk);
        }
        listTaskLiveData.setValue(listTask);
    }

    public void upTask(Task task){
        int i = listTask.indexOf(task);
        if(i > 0){
            Task tsk = listTask.get(i-1);
            listTask.set(i - 1, task);
            listTask.set(i,tsk);
        }
        listTaskLiveData.setValue(listTask);
    }

    public void deleteTask(Task task){
        int i = listTask.indexOf(task);
        listTask.remove(i);
        listTaskLiveData.setValue(listTask);
    }
}
