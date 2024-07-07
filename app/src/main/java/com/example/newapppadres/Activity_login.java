package com.example.newapppadres;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapppadres.BD.bdUsuarios;
import com.example.newapppadres.databinding.ActivityLoginBinding;

public class Activity_login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private bdUsuarios BD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        BD = new bdUsuarios(this);
        Log.d("Activity_login", "onCreate: Login activity started");

        binding.Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.corre.getText().toString();
                String pass = binding.pass.getText().toString();

                Log.d("Activity_login", "onClick: Login button clicked");
                Log.d("Activity_login", "onClick: Email: " + email + ", Password: " + pass);

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(Activity_login.this, "Los campos son obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean verificar = BD.CompromarEmailPass(email, pass);
                    Log.d("Activity_login", "onClick: Verificar: " + verificar);

                    if (verificar) {
                        guardarEmailSharedPreferences(email);
                        Toast.makeText(Activity_login.this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Activity_login.this, MainActivity.class);
                        startActivity(intent);
                        Log.d("Activity_login", "onClick: Starting MainActivity");
                        finish(); // Cerrar la actividad de login
                    } else {
                        Toast.makeText(Activity_login.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Subrayar y hacer clicable el texto "Inicia Sesión"
        TextView IrRegistrate = binding.IrRegistrar;
        String text = "Ya eres usuario? Registrate";
        SpannableString spannableString = new SpannableString(text);

        // Subrayar "Inicia Sesion" y hacerlo clicable
        int startIndex = text.indexOf("Registrate");
        int endIndex = startIndex + "Registrate".length();
        spannableString.setSpan(new UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(Activity_login.this, Activity_Registrar.class);
                startActivity(intent);
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        IrRegistrate.setText(spannableString);
        IrRegistrate.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void guardarEmailSharedPreferences(String email) {
        SharedPreferences sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user_email", email);
        editor.apply();
        Log.d("Activity_login", "guardarEmailSharedPreferences: Email saved to SharedPreferences");
    }
}
