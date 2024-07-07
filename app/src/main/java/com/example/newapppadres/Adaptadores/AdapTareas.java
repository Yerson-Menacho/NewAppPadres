package com.example.newapppadres.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapppadres.Entidades.entTareas;
import com.example.newapppadres.Fragment.DetalleTareaActivity;
import com.example.newapppadres.R;

import java.util.ArrayList;

public class AdapTareas extends RecyclerView.Adapter<AdapTareas.ViewHolder> {

    private Context context;
    private ArrayList<entTareas> listaTareas;

    public AdapTareas(Context context, ArrayList<entTareas> listaTareas) {
        this.context = context;
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        entTareas tarea = listaTareas.get(position);
        holder.nombreTarea.setText(tarea.getNombreTarea());
        holder.detallesTarea.setText(tarea.getDetallesTarea());
        holder.imagenTarea.setImageResource(tarea.getImagen());
        holder.progresoTarea.setProgress(tarea.getProgreso());

        actualizarColorProgreso(holder.progresoTarea, tarea.getProgreso());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetalleTareaActivity.class);
            intent.putExtra("tareaId", tarea.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public void actualizarProgreso(int tareaId, int progreso) {
        for (int i = 0; i < listaTareas.size(); i++) {
            entTareas tarea = listaTareas.get(i);
            if (tarea.getId() == tareaId) {
                tarea.setProgreso(progreso);
                notifyItemChanged(i);
                break;
            }
        }
    }

    private void actualizarColorProgreso(ProgressBar progresoTarea, int progreso) {
        if (progreso < 20) {
            progresoTarea.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.rojo)));
        } else if (progreso < 40) {
            progresoTarea.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.naranja)));
        } else if (progreso < 60) {
            progresoTarea.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.amarillo)));
        } else if (progreso < 80) {
            progresoTarea.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.semiVerde)));
        } else {
            progresoTarea.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.verde)));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTarea, detallesTarea;
        ImageView imagenTarea;
        ProgressBar progresoTarea;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTarea = itemView.findViewById(R.id.NombreTarea);
            detallesTarea = itemView.findViewById(R.id.DetalleTarea);
            imagenTarea = itemView.findViewById(R.id.imagnLista);
            progresoTarea = itemView.findViewById(R.id.progresoTarea);
        }
    }
}

