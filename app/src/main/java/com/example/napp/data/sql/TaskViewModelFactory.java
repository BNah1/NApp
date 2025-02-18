package com.example.napp.data.sql;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.napp.viewmodel.TaskViewModel;

public class TaskViewModelFactory implements ViewModelProvider.Factory {
    private Context context;
    public TaskViewModelFactory(Context context){
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new TaskViewModel(context);
    }
}
