package com.example.newapppadres.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapppadres.Adaptadores.AdapTareas;
import com.example.newapppadres.BD.BdTareas;
import com.example.newapppadres.Entidades.entTareas;
import com.example.newapppadres.R;

import java.util.ArrayList;

public class FragmentTareas extends Fragment {

    private RecyclerView recyclerViewTareas;
    private AdapTareas adapTareas;
    private BdTareas bdTareas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tareas_fragment, container, false);

        recyclerViewTareas = view.findViewById(R.id.recyclerViewTareas);
        recyclerViewTareas.setLayoutManager(new LinearLayoutManager(getContext()));

        bdTareas = new BdTareas(getContext());

        // Obtener todas las tareas
        ArrayList<entTareas> listaTareas = bdTareas.getAllTareas();

        // Configurar el adaptador y establecerlo en el RecyclerView
        adapTareas = new AdapTareas(getContext(), listaTareas);
        recyclerViewTareas.setAdapter(adapTareas);

        return view;
    }
}
