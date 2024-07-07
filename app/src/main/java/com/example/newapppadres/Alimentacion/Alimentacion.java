package com.example.newapppadres.Alimentacion;

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

public class Alimentacion extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_alimentacion);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.pecho) {
                    selectedFragment = new PechoFragment();
                } else if (itemId == R.id.biberon) {
                    selectedFragment = new BiberonFragment();
                } else if (itemId == R.id.solidos) {
                    selectedFragment = new SolidosFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });

        // Set default selection
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.pecho);
        }
    }
}
