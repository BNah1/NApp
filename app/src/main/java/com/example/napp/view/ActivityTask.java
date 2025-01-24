package com.example.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.napp.R;
import com.example.napp.adapter.TaskAdapter;
import com.example.napp.data.model.Task;
import com.example.napp.viewmodel.TaskViewModel;

import java.util.List;

public class ActivityTask extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button button;
    private TaskAdapter adapter;
    private TaskViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        init();
    }



    private void init(){
        button = findViewById(R.id.task_btn_add);
        recyclerView = findViewById(R.id.task_recycleView);

        // Set layout
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        viewModel.getListTaskLiveData().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if(adapter == null) {
                    adapter = new TaskAdapter(tasks);
                }
                recyclerView.setAdapter(adapter);
            }
        });

        ClickAdd();
    }

    private void ClickAdd(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task(R.drawable.ic_launcher_background,"Oyeah","sadadsadad");
                viewModel.addTask(task);
            }
        });
    }


}