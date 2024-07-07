package com.example.newapppadres.Alimentacion;

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

public class Alimentacion extends AppCompatActivity {

    private String infoText = "Lactancia Materna\n" +
            "Beneficios de la Lactancia Materna:\n" +
            "Nutrición Óptima: La leche materna contiene todos los nutrientes necesarios para el crecimiento y desarrollo del bebé.\n" +
            "Inmunidad: Proporciona anticuerpos que ayudan a proteger al bebé de infecciones y enfermedades.\n" +
            "Vínculo Madre-Bebé: Fomenta una conexión emocional entre la madre y el bebé.\n" +
            "Recomendaciones:\n" +
            "Primeros 6 Meses: Se recomienda la lactancia materna exclusiva durante los primeros seis meses de vida.\n" +
            "Después de los 6 Meses: Continuar con la lactancia materna junto con la introducción de alimentos sólidos hasta al menos los 12 meses, y luego tanto tiempo como la madre y el bebé lo deseen.\n" +
            "Frecuencia de las Tomadas:\n" +
            "Recién Nacidos: Cada 2-3 horas, o de 8 a 12 veces en 24 horas.\n" +
            "A partir de los 3 meses: Puede reducirse a cada 3-4 horas.\n" +
            "\n" +
            "Alimentación con Biberón\n" +
            "Fórmula Infantil:\n" +
            "Opciones Disponibles: Fórmulas a base de leche de vaca, fórmulas hipoalergénicas y fórmulas a base de soya.\n" +
            "Preparación: Sigue las instrucciones del fabricante para la mezcla y almacenamiento adecuados.\n" +
            "Recomendaciones:\n" +
            "Primeros 6 Meses: Fórmula infantil exclusiva si no es posible la lactancia materna.\n" +
            "Después de los 6 Meses: Continuar con la fórmula junto con la introducción de alimentos sólidos hasta los 12 meses.\n" +
            "Frecuencia de las Tomadas:\n" +
            "Recién Nacidos: Cada 3-4 horas.\n" +
            "A partir de los 3-4 meses: Cada 4-5 horas.\n" +
            "\n" +
            "Introducción de Alimentos Sólidos\n" +
            "Edad Recomendada:\n" +
            "Generalmente, se recomienda comenzar a introducir alimentos sólidos alrededor de los 6 meses de edad.\n" +
            "Signos de Preparación:\n" +
            "Capacidad para sentarse con apoyo.\n" +
            "Interés en los alimentos que comen los demás.\n" +
            "Habilidad para mantener la cabeza erguida.\n" +
            "\n" +
            "Alimentos Iniciales:\n" +
            "Cereales para Bebés: Fortificados con hierro, mezclados con leche materna o fórmula.\n" +
            "Verduras y Frutas: Purés de verduras como zanahorias, calabacines, y frutas como plátanos, manzanas.\n" +
            "Proteínas: Purés de carne, pollo o lentejas.\n" +
            "\n" +
            "Progresión:\n" +
            "Introducir nuevos alimentos de uno en uno para identificar posibles alergias.\n" +
            "Progresar a texturas más gruesas y alimentos en trozos pequeños a medida que el bebé se acostumbra a masticar.\n" +
            "\n" +
            "Recomendaciones Generales:\n" +
            "Evitar miel y leche de vaca antes de los 12 meses.\n" +
            "Evitar alimentos pequeños y duros que puedan causar asfixia, como nueces enteras, uvas enteras y zanahorias crudas.\n" +
            "\n" +
            "Consultas Médicas\n" +
            "Consulta regularmente con el pediatra para asegurarte de que el bebé está creciendo adecuadamente y recibiendo una nutrición equilibrada.\n" +
            "\n" +
            "Para más información, consulta en: https://www.healthychildren.org/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_alimentacion);

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

    private void showInfoDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Información sobre la alimentacion del Bebé");
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

