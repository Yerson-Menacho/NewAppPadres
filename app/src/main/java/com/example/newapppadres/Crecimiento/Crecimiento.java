package com.example.newapppadres.Crecimiento;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.newapppadres.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Crecimiento extends AppCompatActivity {

    private String infoText = "Estatura\n" +
            "Rango Normal de Estatura:\n" +
            "- Recién Nacidos: La longitud promedio al nacer es de alrededor de 50 cm (20 pulgadas), con un rango normal de 46 a 56 cm (18 a 22 pulgadas).\n" +
            "- Primeros 6 Meses: Los bebés crecen aproximadamente 1.5 a 2.5 cm (0.6 a 1 pulgada) por mes.\n" +
            "- 6 a 12 Meses: El crecimiento en longitud se reduce a aproximadamente 1 cm (0.4 pulgadas) por mes.\n" +
            "\n" +
            "Peso\n" +
            "Rango Normal de Peso:\n" +
            "- Recién Nacidos: El peso promedio al nacer es de alrededor de 3.2 kg (7 libras), con un rango normal de 2.5 a 4.5 kg (5.5 a 10 libras).\n" +
            "- Primeros 6 Meses: Los bebés generalmente duplican su peso al nacer para los 5-6 meses.\n" +
            "- 6 a 12 Meses: Los bebés suelen triplicar su peso al nacer para el primer año.\n" +
            "\n" +
            "Factores que Afectan el Crecimiento\n" +
            "- Genética: La estatura y el peso de los padres pueden influir en el crecimiento del bebé.\n" +
            "- Nutrición: Una dieta equilibrada y adecuada es crucial para el crecimiento saludable.\n" +
            "- Salud General: Las condiciones médicas o enfermedades pueden afectar el crecimiento.\n" +
            "- Actividades Físicas: Aunque los bebés no realizan ejercicios, el movimiento y la actividad regular son importantes para su desarrollo.\n" +
            "\n" +
            "Evaluación del Crecimiento\n" +
            "- Tablas de Crecimiento: Los pediatras utilizan tablas de crecimiento para monitorear el desarrollo del bebé. Estas tablas comparan el peso, la longitud y la circunferencia de la cabeza del bebé con las normas establecidas para su edad y género.\n" +
            "- Percentiles: Los percentiles son una forma de comparar el crecimiento del bebé con el de otros niños de la misma edad y género. Por ejemplo, si un bebé está en el percentil 50 para el peso, significa que el 50% de los bebés de la misma edad y género pesan más y el 50% pesan menos.\n" +
            "\n" +
            "Consultas Médicas Regulares\n" +
            "- Es importante asistir a todas las consultas pediátricas programadas para monitorear el crecimiento y el desarrollo del bebé. Los pediatras pueden identificar cualquier problema temprano y proporcionar recomendaciones específicas si es necesario.\n" +
            "\n" +
            "Para mayor información consulta en: https://www.healthychildren.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_crecimiento);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView infoIcon = findViewById(R.id.infoIcon);
        infoIcon.setOnClickListener(v -> showInfoDialog());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.estatura) {
                    selectedFragment = new EstaturaFragment();
                } else if (itemId == R.id.peso) {
                    selectedFragment = new PesoFragment();
                } else if (itemId == R.id.resumen) {
                    selectedFragment = new ResumenFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
                return false;
            }
        });

        // Set default selection
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.estatura);
        }
    }

    private void showInfoDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Información sobre el crecimiento del Bebé");
        builder.setMessage(infoText);
        builder.setPositiveButton("Cerrar", null);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

