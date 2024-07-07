package com.example.newapppadres.Suenio;

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

public class Suenio extends AppCompatActivity {

    private String infoText = "Duración del Sueño\n" +
            "Recién Nacidos (0-3 meses):\n" +
            "Horas de Sueño: Los recién nacidos duermen entre 14 y 17 horas al día, divididas en períodos de 2-4 horas, tanto de día como de noche.\n" +
            "Patrón de Sueño: No tienen un ciclo regular de día y noche debido a la necesidad de alimentarse frecuentemente.\n" +
            "\n" +
            "Bebés (4-12 meses):\n" +
            "Horas de Sueño: Los bebés duermen entre 12 y 16 horas al día, incluyendo siestas.\n" +
            "Patrón de Sueño: A partir de los 6 meses, muchos bebés empiezan a dormir períodos más largos durante la noche y tienen 2-3 siestas durante el día.\n" +
            "\n" +
            "Rutina de Sueño\n" +
            "Importancia de una Rutina de Sueño:\n" +
            "Consistencia: Establecer una rutina de sueño regular ayuda a regular el reloj biológico del bebé y promueve un sueño saludable.\n" +
            "Tranquilidad: Una rutina tranquila y predecible puede facilitar la transición del bebé al sueño.\n" +
            "\n" +
            "Elementos de una Rutina de Sueño:\n" +
            "Baño: Un baño tibio puede ser relajante y señalizar el comienzo de la rutina de dormir.\n" +
            "Alimentación: Alimentar al bebé antes de acostarlo puede ayudarlo a dormir por períodos más largos.\n" +
            "Lectura de Cuentos: Leer cuentos cortos o cantar canciones suaves puede ser reconfortante.\n" +
            "Ambiente Adecuado: Asegúrate de que el entorno de sueño sea tranquilo, oscuro y a una temperatura adecuada.\n" +
            "\n" +
            "Ambiente de Sueño:\n" +
            "Temperatura: Mantén la habitación a una temperatura agradable y evita el sobrecalentamiento. Una temperatura de entre 20-22°C (68-72°F) es ideal.\n" +
            "Ropa de Cama: Viste al bebé con ropa de dormir adecuada para la temperatura ambiente y utiliza sacos de dormir en lugar de mantas sueltas.\n" +
            "\n" +
            "Despertares Nocturnos\n" +
            "Causas Comunes:\n" +
            "Hambre: Especialmente en los primeros meses, los bebés pueden despertarse frecuentemente para alimentarse.\n" +
            "Miedos y Ansiedades: A partir de los 6-9 meses, los bebés pueden experimentar ansiedad por separación.\n" +
            "Muecas y Molestias: Las molestias físicas, como los gases o la dentición, pueden interrumpir el sueño.\n" +
            "\n" +
            "Manejo de Despertares:\n" +
            "Calma y Consuelo: Responde rápidamente para consolar al bebé, pero evita estimularlo demasiado.\n" +
            "Rutinas Calificadas: Mantén una rutina de sueño constante para ayudar a reducir los despertares nocturnos.\n" +
            "\n" +
            "Consultas Médicas\n" +
            "Consulta regularmente con el pediatra para discutir los patrones de sueño del bebé y recibir orientación personalizada.\n" +
            "\n" +
            "Para más información, consulta en: https://www.healthychildren.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_suenio);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView infoIcon = findViewById(R.id.infoIcon);
        infoIcon.setOnClickListener(v -> showInfoDialog());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
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
        });

        // Set default selection
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.hora_suenio);
        }
    }

    private void showInfoDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Información sobre el Sueño del Bebé");
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
