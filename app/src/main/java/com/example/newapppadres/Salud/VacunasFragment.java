package com.example.newapppadres.Salud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newapppadres.Fragment.FragmentModuloSeguimiento;
import com.example.newapppadres.R;

public class VacunasFragment extends FragmentModuloSeguimiento {

    public VacunasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.salud_vacuna, container, false);
    }
}
