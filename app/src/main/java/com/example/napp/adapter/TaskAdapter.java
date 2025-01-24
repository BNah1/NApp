package com.example.napp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.napp.R;
import com.example.napp.data.model.Task;

import org.w3c.dom.Text;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private List<Task> mListTask;

    public TaskAdapter(List<Task> mListTask) {
        this.mListTask = mListTask;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = mListTask.get(position);
        if(task == null){
            return;
        }
        holder.Bind(task);
    }

    @Override
    public int getItemCount() {
        if(mListTask != null){
            return mListTask.size();
        }
        return 0;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name,description;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_task_img);
            name = itemView.findViewById(R.id.item_task_txtName);
            description = itemView.findViewById(R.id.item_task_txtSr);

        }

        private void Bind(Task task){
            img.setImageResource(task.getImage());
            name.setText(task.getName());
            description.setText(task.getDescription());
        }
    }
}
