package com.example.newapppadres;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.example.newapppadres.BD.bdUsuarios;
import com.example.newapppadres.Fragment.FragmentComunidad;
import com.example.newapppadres.Fragment.FragmentConfiguracion;
import com.example.newapppadres.Fragment.FragmentModuloSeguimiento;
import com.example.newapppadres.Fragment.FragmentNosotros;
import com.example.newapppadres.Fragment.FragmentShop;
import com.example.newapppadres.Fragment.FragmentTareas;
import com.example.newapppadres.Fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private bdUsuarios BD;
    private TextView lblNombre;
    private TextView lblEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.principal);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        BD = new bdUsuarios(this);

        // Obtener la vista del encabezado desde el NavigationView
        View headerView = navigationView.getHeaderView(0);
        lblNombre = headerView.findViewById(R.id.lblnombre);
        lblEmail = headerView.findViewById(R.id.lblemail);

        // Recuperar el correo del usuario desde SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String userEmail = sharedPref.getString("user_email", null);

        if (userEmail != null) {
            // Consultar la base de datos para obtener el nombre y el correo del usuario
            Cursor cursor = BD.ActualizaNombreEmail(userEmail);
            if (cursor.moveToFirst()) {
                String nombre = cursor.getString(0);
                String email = cursor.getString(1);

                // Establecer los TextViews con los detalles del usuario
                lblNombre.setText(nombre);
                lblEmail.setText(email);
            }
            cursor.close();
        }

        // Encontrar el ImageView del icono de editar perfil
        ImageButton editarPerfilIcon = headerView.findViewById(R.id.editarPerfil);

        // Agregar un listener de clic al icono de edición del perfil
        editarPerfilIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de edición del perfil
                Intent intent = new Intent(MainActivity.this, ActivityEditarPerfil.class);
                startActivity(intent);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.Home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.Home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (itemId == R.id.Tareas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentTareas()).commit();
        } else if (itemId == R.id.Comunidad) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentComunidad()).commit();
        } else if (itemId == R.id.Shop) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentShop()).commit();
        } else if (itemId == R.id.Videos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentModuloSeguimiento()).commit();
        } else if (itemId == R.id.configuracion) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentConfiguracion()).commit();
        } else if (itemId == R.id.nosotros) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentNosotros()).commit();
        } else if (itemId == R.id.Salir) {
            limpiarSharedPreferences();
            Intent intent = new Intent(MainActivity.this, Activity_login.class);
            startActivity(intent);
            finish(); // Finalizar la actividad principal
        }

        drawer.close();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void limpiarSharedPreferences() {
        SharedPreferences sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }
}
