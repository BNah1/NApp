package com.example.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.napp.R;
import com.example.napp.adapter.TaskAdapter;
import com.example.napp.data.model.Task;
import com.example.napp.databinding.ActivityTaskBinding;
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
        init();
    }



    private void init(){

        // khởi tạo viewmodel
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Gán ViewModel vào DataBinding
        ActivityTaskBinding activityTaskBinding = DataBindingUtil.setContentView(this,R.layout.activity_task);
        activityTaskBinding.setTaskViewModel(viewModel);
        activityTaskBinding.setLifecycleOwner(this);


        //Set layout
        button = findViewById(R.id.task_btn_add);
        recyclerView = findViewById(R.id.task_recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);



        viewModel.getListTaskLiveData().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if(adapter == null) {
                    adapter = new TaskAdapter(tasks, new TaskAdapter.OnTapActionListener() {
                        @Override
                        public void onTapAction(Task task, String action) {
                            switch (action){
                                case "UP":
                                    viewModel.upTask(task);
                                    break;
                                case "DOWN":
                                    viewModel.downTask(task);
                                    break;
                                case "DELETE":
                                    viewModel.deleteTask(task);
                                    Toast.makeText(ActivityTask.this,"Xoa",Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
            }
        });

        ClickAdd();
    }

    private void ClickAdd(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addTask();
            }
        });
    }


}