package com.example.newapppadres.Suenio;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.newapppadres.Crecimiento.ResumenFragment;
import com.example.newapppadres.R;
import com.example.newapppadres.Salud.MedicamentoFragment;
import com.example.newapppadres.Salud.TemperaturaFragment;
import com.example.newapppadres.Salud.VacunasFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Suenio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_suenio);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.hora_suenio) {
                    selectedFragment = new HorasFragment();
                } else if (itemId == R.id.Resumen) {
                    selectedFragment = new ResumenSuenio();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });

        // Set default selection
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.hora_suenio);
        }
    }
}
