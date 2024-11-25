package com.example.prc3_joscorrochano;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.prc3_joscorrochano.databinding.ActivityBikeBinding;

public class BikeActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityBikeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el diseño asociado a esta actividad y vincularlo con el objeto binding
        binding = ActivityBikeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar el toolbar como la barra de acción predeterminada
        setSupportActionBar(binding.toolbar);

        // Obtener el controlador de navegación para gestionar los fragmentos y la navegación entre ellos
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bike);

        // Configurar el AppBarConfiguration con el gráfico de navegación actual
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        // Configurar la barra de acción para trabajar con el controlador de navegación
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }


    @Override
    public boolean onSupportNavigateUp() {

        // Manejar la acción de navegar con el controlador de navegación
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bike);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}