package com.example.newapppadres.Salud;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.newapppadres.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SaludActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_salud);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.temperatura) {
                    selectedFragment = new TemperaturaFragment();
                } else if (itemId == R.id.medicamento) {
                    selectedFragment = new MedicamentoFragment();
                } else if (itemId == R.id.vacunas) {
                    selectedFragment = new VacunasFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });

        // Set default selection
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.temperatura);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (itemId == R.id.action_search) {
            // Lógica para mostrar búsqueda
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void showInfoTooltip(View view) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Información sobre la Salud del Bebé");
        builder.setMessage(
                "Temperatura Corporal\n" +
                        "Rango Normal de Temperatura:\n" +
                        "La temperatura normal de un bebé puede variar, pero generalmente se considera normal entre 36.1°C y 37.9°C cuando se mide rectalmente.\n" +
                        "\n" +
                        "Cómo Medir la Temperatura:\n" +
                        "La forma más precisa de medir la temperatura de un bebé es con un termómetro digital rectal.\n" +
                        "Los termómetros de oído pueden ser útiles para bebés mayores de 6 meses.\n" +
                        "Los termómetros axilares son menos precisos pero pueden usarse para una lectura rápida.\n" +
                        "\n" +
                        "Cuándo Consultar al Médico:\n" +
                        "Si un bebé menor de 3 meses tiene una fiebre de 38°C o más.\n" +
                        "Si un bebé de 3 a 6 meses tiene una fiebre de 38.9°C o más.\n" +
                        "Si un bebé mayor de 6 meses tiene una fiebre de 39.4°C o más.\n" +
                        "\n" +
                        "Medicamentos\n" +
                        "Consideraciones Generales:\n" +
                        "Siempre consulta a un pediatra antes de administrar cualquier medicamento a un bebé.\n" +
                        "Usa medicamentos específicamente formulados para bebés y sigue las dosis recomendadas.\n" +
                        "Medicamentos Comunes:\n" +
                        "Acetaminofén (Paracetamol): Se usa para aliviar el dolor y la fiebre. Es seguro para bebés de 2 meses en adelante, siguiendo la dosis adecuada según el peso.\n" +
                        "Ibuprofeno: También se usa para el dolor y la fiebre, pero solo es adecuado para bebés de 6 meses en adelante. No se debe usar en bebés deshidratados o con problemas renales.\n" +
                        "\n" +
                        "Vacunas\n" +
                        "Calendario de Vacunación:\n" +
                        "Nacimiento: Hepatitis B (HepB)\n" +
                        "2 meses: Difteria, tétanos y tos ferina acelular (DTaP), Polio (IPV), Haemophilus influenzae tipo b (Hib), Neumococo conjugada (PCV13), Rotavirus (RV), Hepatitis B (HepB)\n" +
                        "4 meses: DTaP, IPV, Hib, PCV13, RV\n" +
                        "6 meses: DTaP, IPV, Hib, PCV13, RV, HepB\n" +
                        "12-15 meses: Sarampión, paperas y rubéola (MMR), Varicela (VAR), Hepatitis A (HepA), Hib, PCV13\n" +
                        "15-18 meses: DTaP\n" +
                        "18-24 meses: HepA (segunda dosis)\n" +
                        "4-6 años: DTaP, IPV, MMR, VAR\n" +
                        "\n" +
                        "Consideraciones Adicionales:\n" +
                        "La vacuna contra la gripe se recomienda anualmente a partir de los 6 meses de edad.\n" +
                        "Es crucial seguir el calendario de vacunación recomendado para proteger al bebé contra enfermedades graves.\n" +
                        "\n" +
                        "Para mayor información consulta en: https://www.healthychildren.org/"
        );
        builder.setPositiveButton("Cerrar", null);
        builder.show();
    }
}


