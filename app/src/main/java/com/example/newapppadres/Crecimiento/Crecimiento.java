package com.example.newapppadres.Crecimiento;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.newapppadres.Salud.MedicamentoFragment;
import com.example.newapppadres.Salud.TemperaturaFragment;
import com.example.newapppadres.Salud.VacunasFragment;
import com.example.newapppadres.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Crecimiento extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_crecimiento);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.estarura) {
                    selectedFragment = new EstaturaFragment();
                } else if (itemId == R.id.peso) {
                    selectedFragment = new PesoFragment();
                } else if (itemId == R.id.resumen) {
                    selectedFragment = new ResumenFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });

        // Set default selection
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.estarura);
        }
    }
}
