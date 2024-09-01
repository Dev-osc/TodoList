package com.marinmoreno.todolist;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa la lista de tareas
        tasks = new ArrayList<>();

        // Configura el RecyclerView

        recyclerView = findViewById(R.id.recyclerView);
        if (recyclerView == null) {
            Log.e("MainActivity", "RecyclerView not found in layout");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configura el Adapter y lo enlaza con el RecyclerView
        adapter = new TaskAdapter(tasks, new TaskAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                // Eliminar la tarea de la lista
                tasks.remove(position);
                // Notificar al Adapter que se ha eliminado un elemento
                adapter.notifyItemRemoved(position);
            }
        });

        recyclerView.setAdapter(adapter);

        // Configura el botón de añadir tarea
        Button buttonAddTask = findViewById(R.id.buttonAddTask);
        final EditText editTextTask = findViewById(R.id.editTextTask);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTextTask.getText().toString().trim();
                if (!task.isEmpty()) {
                    // Añadir la nueva tarea a la lista
                    tasks.add(task);
                    // Notificar al Adapter que se ha añadido un nuevo elemento
                    adapter.notifyItemInserted(tasks.size() - 1);
                    // Limpiar el campo de texto
                    editTextTask.setText("");
                }
            }
        });
    }
}
