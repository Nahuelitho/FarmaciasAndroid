package com.softnahu.tp4;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.softnahu.tp4.databinding.ActivityMainBinding;
import com.softnahu.tp4.model.Farmacia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static ArrayList<Farmacia> listaFarmacias = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listaFarmacias.add(new Farmacia("Farmacia Santa Lucia", "Avenida Serrana Manzana 140 Parcela 03, La Punta San Luis", R.drawable.farmaciasantalucia, "Entregas a domicilio: 2664-584038", -33.000000, -64.000000));
        listaFarmacias.add(new Farmacia("Farmacia Sierras Del Sol", "La Punta San Luis", R.drawable.farmaciasierrasdelsol, "Atencion de: domingo a lunes 9 a.m - 12 a.m", -33.000001, -64.000001));
        listaFarmacias.add(new Farmacia("Farmacia Quintana", "Av. Serrana, La Punta San Luis", R.drawable.farmaciaquintana, "Atencion de: lunes a sabado 8:30 a.m - 1:30 p.m, 16:30 p.m - 21:30 p.m", -33.000002, -64.000002));
        listaFarmacias.add(new Farmacia("Farmacia Divino Niño", "Mod 3, Mzna 47, Casa 11, La Punta San Luis", R.drawable.farmaciadivinonino, "Atencion de: lunes a sabado 8:30 a.m - 10:00 p.m", -33.000003, -64.000003));

        // Inflar el diseño usando View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        solicitarPermisos();
        setSupportActionBar(binding.appBarMain.toolbar);
        setupNavigation();
    }


    private void setupNavigation() {
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.map, R.id.nav_farmacias)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void solicitarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && (checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) ||
                (checkSelfPermission(ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 1000);
        }
    }
}