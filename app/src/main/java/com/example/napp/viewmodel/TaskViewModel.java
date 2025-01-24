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

    public TaskViewModel() {
        listTaskLiveData = new MutableLiveData<>();
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
    public void addTask(Task task){
        listTask.add(0,task);
        listTaskLiveData.setValue(listTask);
    }
}
