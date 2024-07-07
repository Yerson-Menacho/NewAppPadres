package com.example.newapppadres.Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newapppadres.BD.BdTareas;
import com.example.newapppadres.Entidades.Subtarea;
import com.example.newapppadres.R;
import java.util.ArrayList;

public class AdapSubtareas extends RecyclerView.Adapter<AdapSubtareas.ViewHolder> {

    private Context context;
    private ArrayList<Subtarea> listaSubtareas;
    private int tareaId;
    private AdapTareas adapTareas;

    public AdapSubtareas(Context context, ArrayList<Subtarea> listaSubtareas, int tareaId, AdapTareas adapTareas) {
        this.context = context;
        this.listaSubtareas = listaSubtareas;
        this.tareaId = tareaId;
        this.adapTareas = adapTareas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detalles_subtarea, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subtarea subtarea = listaSubtareas.get(position);
        holder.nombreSubtarea.setText(subtarea.getNombreSubtarea());
        holder.checkBox.setOnCheckedChangeListener(null); // Limpiar listener para evitar llamadas recursivas
        holder.checkBox.setChecked(subtarea.isCompletada());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            subtarea.setCompletada(isChecked);
            actualizarSubtareaEnBD(subtarea);
            actualizarProgresoTarea();
        });
    }

    @Override
    public int getItemCount() {
        return listaSubtareas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreSubtarea;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreSubtarea = itemView.findViewById(R.id.nombreSubtarea);
            checkBox = itemView.findViewById(R.id.checkBoxSubtarea);
        }
    }

    private void actualizarSubtareaEnBD(Subtarea subtarea) {
        BdTareas bdTareas = new BdTareas(context);
        SQLiteDatabase db = bdTareas.getWritableDatabase();
        bdTareas.actualizarEstadoSubtarea(subtarea.getId(), subtarea.isCompletada() ? 1 : 0);
        db.close();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void actualizarProgresoTarea() {
        int completadas = 0;
        for (Subtarea subtarea : listaSubtareas) {
            if (subtarea.isCompletada()) {
                completadas++;
            }
        }
        int progreso = (completadas * 100) / listaSubtareas.size();

        BdTareas bdTareas = new BdTareas(context);
        bdTareas.actualizarProgresoTarea(tareaId, progreso);
        bdTareas.close();

        if (adapTareas != null) {
            adapTareas.actualizarProgreso(tareaId, progreso);
        }

        notifyDataSetChanged(); // Asegurar que se actualice la UI después de los cambios
    }
}

