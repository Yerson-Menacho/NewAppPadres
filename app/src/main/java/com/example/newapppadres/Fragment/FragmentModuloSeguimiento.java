package com.example.newapppadres.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newapppadres.Alimentacion.Alimentacion;
import com.example.newapppadres.Crecimiento.Crecimiento;
import com.example.newapppadres.R;
import com.example.newapppadres.Salud.SaludActivity;
import com.example.newapppadres.Suenio.Suenio;

public class FragmentModuloSeguimiento extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modulo_seguimiento_fragment, container, false);

        ImageView saludImageView = view.findViewById(R.id.Salud);
        ImageView crecimientoImageView = view.findViewById(R.id.Crecimiento);
        ImageView alimentacionImageView = view.findViewById(R.id.Alimentacion);
        ImageView suenoImageView = view.findViewById(R.id.Salud_bebe);

        saludImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SaludActivity.class);
                startActivity(intent);
            }
        });

        crecimientoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para la actividad de Crecimiento
                Intent intent = new Intent(getActivity(), Crecimiento.class);
                startActivity(intent);
            }
        });

        alimentacionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para la actividad de Alimentación
                Intent intent = new Intent(getActivity(), Alimentacion.class);
                startActivity(intent);
            }
        });

        suenoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para la actividad de Sueño
                Intent intent = new Intent(getActivity(), Suenio.class);
                startActivity(intent);
            }
        });

        return view;
    }
}


