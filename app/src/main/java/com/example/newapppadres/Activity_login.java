package com.example.newapppadres;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapppadres.BD.bdUsuarios;
import com.example.newapppadres.databinding.ActivityLoginBinding;

public class Activity_login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private bdUsuarios BD;
    private ImageButton facebookButton;
    private ImageButton instagramButton;
    private ImageButton whatsappButton;
    private ImageButton youtubeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        BD = new bdUsuarios(this);
        Log.d("Activity_login", "onCreate: Login activity started");

        facebookButton = findViewById(R.id.facebook);
        instagramButton = findViewById(R.id.instagram);
        whatsappButton = findViewById(R.id.whatsapp);
        youtubeButton = findViewById(R.id.youtube);

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPaginaWeb("https://www.facebook.com");
            }
        });

        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPaginaWeb("https://www.instagram.com");
            }
        });

        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPaginaWeb("https://wa.me/");
            }
        });

        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPaginaWeb("https://www.youtube.com");
            }
        });

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

        TextView IrRegistrate = binding.IrRegistrar;
        String text = "Ya eres usuario? Registrate";
        SpannableString spannableString = new SpannableString(text);

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

    private void abrirPaginaWeb(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void guardarEmailSharedPreferences(String email) {
        SharedPreferences sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user_email", email);
        editor.apply();
        Log.d("Activity_login", "guardarEmailSharedPreferences: Email saved to SharedPreferences");
    }
}
