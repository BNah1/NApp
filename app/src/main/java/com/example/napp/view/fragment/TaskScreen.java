package com.example.napp.view.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.napp.R;
import com.example.napp.adapter.TaskAdapter;
import com.example.napp.data.model.Task;
import com.example.napp.data.sql.DatabaseManager;
import com.example.napp.databinding.FragmentTaskScreenBinding;
import com.example.napp.viewmodel.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class TaskScreen extends Fragment {

    private RecyclerView recyclerView;
    private Button button;
    private TaskAdapter adapter;
    private TextView clearButton;
    private TaskViewModel viewModel;
    private DatabaseManager databaseManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTaskScreenBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_screen, container, false);
        init(binding);
        return binding.getRoot();
    }

    private void init(FragmentTaskScreenBinding binding) {
        // Khởi tạo SQLITE
        databaseManager = DatabaseManager.getInstance(getContext());

        // Khởi tạo ViewModel
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TaskViewModel.class);

        // Gán ViewModel vào DataBinding
        binding.setTaskViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        // Set layout
        button = binding.taskBtnAdd; // Thay vì findViewById
        clearButton = binding.taskBtnClear;
        recyclerView = binding.taskRecycleView; // Thay vì findViewById
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        viewModel.getListTaskLiveData().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if (adapter == null) {
                    adapter = new TaskAdapter(tasks, new TaskAdapter.OnTapActionListener() {
                        @Override
                        public void onTapAction(Task task, String action) {
                            switch (action) {
                                case "UP":
                                    viewModel.upTask(task);
                                    break;
                                case "DOWN":
                                    viewModel.downTask(task);
                                    break;
                                case "DELETE":
                                    viewModel.deleteTask(task);
                                    databaseManager.deleteData(task.getId());
                                    Toast.makeText(getContext(), "Xóa", Toast.LENGTH_LONG).show();
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
        ClearTask();
    }

    private void ClickAdd() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addTask();
            }
        });
    }

    private void ClearTask(){
        DatabaseManager databaseManager = DatabaseManager.getInstance(getContext());
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = databaseManager.dropTable();
                if (result) {
                    Toast.makeText(getContext(), "Cleared", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Fail", Toast.LENGTH_LONG).show();
                }

                viewModel.getListTask().clear();
                viewModel.getListTaskLiveData().setValue(viewModel.getListTask());
//                viewModel.clearAllTasks();
            }

        });
    }
}