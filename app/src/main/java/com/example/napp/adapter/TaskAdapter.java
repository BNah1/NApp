package com.example.napp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.napp.R;
import com.example.napp.data.model.Task;
import com.example.napp.databinding.ItemTaskBinding;

import org.w3c.dom.Text;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private List<Task> mListTask;
    private OnTapActionListener listener;


    public TaskAdapter(List<Task> mListTask, OnTapActionListener listener) {
        this.mListTask = mListTask;
        this.listener = listener;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        ItemTaskBinding itemTaskBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TaskViewHolder(itemTaskBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = mListTask.get(position);
        if(task == null){
            return;
        }
        holder.Bind(task, listener);
    }

    @Override
    public int getItemCount() {
        if(mListTask != null){
            return mListTask.size();
        }
        return 0;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private ItemTaskBinding itemTaskBinding;


        public TaskViewHolder(@NonNull ItemTaskBinding itemTaskBinding) {
            super(itemTaskBinding.getRoot());


            this.itemTaskBinding = itemTaskBinding;

        }

        private void Bind(Task task, OnTapActionListener listener){
            itemTaskBinding.itemTaskImg.setImageResource(task.getImage());
            itemTaskBinding.itemTaskTxtName.setText(task.getName());
            itemTaskBinding.itemTaskTxtSr.setText(task.getDescription());

            itemTaskBinding.btnUp.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTapAction(task, "UP");
                }
            });

            itemTaskBinding.btnDown.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTapAction(task, "DOWN");
                }
            });

            itemTaskBinding.btnDelete.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTapAction(task, "DELETE");
                }
            });

        }
    }

    public interface OnTapActionListener {
        void onTapAction(Task task, String action);
    }

}
