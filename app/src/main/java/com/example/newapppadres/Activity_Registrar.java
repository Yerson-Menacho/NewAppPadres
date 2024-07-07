package com.example.newapppadres;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newapppadres.BD.bdUsuarios;
import com.example.newapppadres.databinding.ActivityRegistrarBinding;

public class Activity_Registrar extends AppCompatActivity {

    ActivityRegistrarBinding binding;
    bdUsuarios BD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BD = new bdUsuarios(this);

        binding.registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String email = binding.emailreg.getText().toString();
                String pass = binding.passreg.getText().toString();
                String confpass = binding.confpassreg.getText().toString();

                if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty() || confpass.isEmpty()) {
                    Toast.makeText(Activity_Registrar.this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(confpass)) {
                    Toast.makeText(Activity_Registrar.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean verificarEmail = BD.ComprobarEmail(email);
                    if (!verificarEmail) {
                        Boolean insert = BD.insertData(nombre, email, pass);
                        if (insert) {
                            Toast.makeText(Activity_Registrar.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Activity_Registrar.this, Activity_login.class);
                            startActivity(intent);
                            finish(); // Cerrar la actividad de registro después del éxito
                        } else {
                            Toast.makeText(Activity_Registrar.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Activity_Registrar.this, "El usuario ya existe, por favor inicia sesión", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Subrayar y hacer clicable el texto "Inicia Sesión"
        TextView txtCancelar = binding.Cancelar;
        String text = "Ya eres usuario? Inicia Sesion";
        SpannableString spannableString = new SpannableString(text);

        // Subrayar "Inicia Sesion" y hacerlo clicable
        int startIndex = text.indexOf("Inicia Sesion");
        int endIndex = startIndex + "Inicia Sesion".length();
        spannableString.setSpan(new UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(Activity_Registrar.this, Activity_login.class);
                startActivity(intent);
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtCancelar.setText(spannableString);
        txtCancelar.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
