package com.marinmoreno.todolist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private final List<String> tasks;
    private final OnItemClickListener listener;

    // Constructor de TaskAdapter
    public TaskAdapter(List<String> tasks, OnItemClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        String currentTask = tasks.get(position);
        holder.textViewTask.setText(currentTask);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    // Interfaz para manejar los clics en los botones
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    // ViewHolder para los elementos del RecyclerView
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTask;
        public Button buttonDelete;

        public TaskViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            // Maneja el clic en el botón de eliminar
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
