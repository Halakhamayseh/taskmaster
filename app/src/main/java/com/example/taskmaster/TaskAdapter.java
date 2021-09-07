package com.example.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.TaskMaster;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    ArrayList<TaskMaster> allTasks= new ArrayList<>();

    public TaskAdapter(ArrayList<TaskMaster> allTasks) {
        this.allTasks = allTasks;
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        // the model object
        public TaskMaster task;
        //view object
        View itemView;

        public TaskViewHolder(@NotNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToDetailsPagePutExtra=new Intent(v.getContext(),TaskDetails.class);
                    goToDetailsPagePutExtra.putExtra("taskNameClickListener",task.getTaskTitle());
                    goToDetailsPagePutExtra.putExtra("taskBodyClickListener",task.getTaskBody());
                    goToDetailsPagePutExtra.putExtra("taskStateClickListener",task.getTaskState());
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
        taskTitle.setText(holder.task.getTaskTitle());
        taskBody.setText(holder.task.getTaskBody());
        taskState.setText(holder.task.getTaskState());

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

}
