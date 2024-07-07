package com.example.newapppadres.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newapppadres.Adaptadores.AdapSubtareas;
import com.example.newapppadres.Adaptadores.AdapTareas;
import com.example.newapppadres.BD.BdTareas;
import com.example.newapppadres.Entidades.Subtarea;
import com.example.newapppadres.Entidades.entTareas;
import com.example.newapppadres.R;
import java.util.ArrayList;
import java.util.List;

public class DetalleTareaActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSubtareas;
    private AdapSubtareas adapSubtareas;
    private BdTareas bdTareas;
    private int tareaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_sub_tarea);

        recyclerViewSubtareas = findViewById(R.id.recyclerVieweDetalletarea);
        recyclerViewSubtareas.setLayoutManager(new LinearLayoutManager(this));

        bdTareas = new BdTareas(this);

        // Obtener el ID de la tarea desde el intent
        tareaId = getIntent().getIntExtra("tareaId", -1);

        // Obtener la tarea desde la base de datos
        entTareas tarea = bdTareas.getTarea(this, tareaId);

        // Verificar si la tarea y las subtareas no son nulas
        if (tarea != null && tarea.getSubtareas() != null) {
            // Crear el adaptador de subtareas
            adapSubtareas = new AdapSubtareas((Context) this, (ArrayList<Subtarea>) tarea.getSubtareas(), tareaId, null); // Revisa el último argumento aquí

            recyclerViewSubtareas.setAdapter(adapSubtareas);
        } else {
            // Manejar el caso cuando la tarea o las subtareas son nulas
            // Puedes mostrar un mensaje al usuario o manejarlo de otra manera
        }
    }
}


