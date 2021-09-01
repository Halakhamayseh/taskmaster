package com.example.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    List<TaskClass> allTasks=new ArrayList<TaskClass>();

    public TaskAdapter(List<TaskClass> allTasks) {
        this.allTasks = allTasks;
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        // the model object
        public TaskClass task;
        //view object
        View itemView;

        public TaskViewHolder(@NotNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToDetailsPagePutExtra=new Intent(v.getContext(),TaskDetails.class);
                    goToDetailsPagePutExtra.putExtra("taskNameClickListener",task.taskTitle);
                    goToDetailsPagePutExtra.putExtra("taskBodyClickListener",task.taskBody);
                    goToDetailsPagePutExtra.putExtra("taskStateClickListener",task.taskState);
                    v.getContext().startActivity(goToDetailsPagePutExtra);
                }
            });
        }
    }

    @NotNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_item,parent,false);
        TaskViewHolder taskViewHolder=new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.task=allTasks.get(position);
        TextView taskTitle=holder.itemView.findViewById(R.id.taskTitelId);
        TextView taskBody=holder.itemView.findViewById(R.id.taskBodyId);
        TextView taskState=holder.itemView.findViewById(R.id.taskStateId);
        taskTitle.setText(holder.task.taskTitle);
        taskBody.setText(holder.task.taskBody);
        taskState.setText(holder.task.taskState);

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

}
