package com.softnahu.tp4.ui.mapa;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.softnahu.tp4.R;
import com.softnahu.tp4.model.Farmacia;

import java.util.ArrayList;

public class MapaFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<Mapa> mapaActual;
    private ArrayList<Farmacia> listaFarmacias;
    private FusedLocationProviderClient fusedLocationClient;

    public MapaFragmentViewModel(@NonNull Application application) {
        super(application);
        listaFarmacias = new ArrayList<>();
        cargarFarmacias();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application);
    }

    public LiveData<Mapa> getMapaActual() {
        if (mapaActual == null) {
            mapaActual = new MutableLiveData<>();
        }
        return mapaActual;
    }

    public void mostrarMapa() {
        mapaActual.setValue(new Mapa());
    }

    private void cargarFarmacias() {
        listaFarmacias.add(new Farmacia("Farmacia Santa Lucia", "Avenida Serrana Manzana 140 Parcela 03, La Punta San Luis", R.drawable.farmaciasantalucia, "Entregas a domicilio: 2664-584038", -33.183099, -66.313148));
        listaFarmacias.add(new Farmacia("Farmacia Sierras Del Sol", "La Punta San Luis", R.drawable.farmaciasierrasdelsol, "Atencion de: domingo a lunes 9 a.m - 12 a.m", -33.185517, -66.312102));
        listaFarmacias.add(new Farmacia("Farmacia Quintana", "Av. Serrana, La Punta San Luis", R.drawable.farmaciaquintana, "Atencion de: lunes a sabado 8:30 a.m - 1:30 p.m, 16:30 p.m - 21:30 p.m", -33.181782, -66.313713));
        listaFarmacias.add(new Farmacia("Farmacia Divino Niño", "Mod 3, Mzna 47, Casa 11, La Punta San Luis", R.drawable.farmaciadivinonino, "Atencion de: lunes a sabado 8:30 a.m - 10:00 p.m", -33.184297, -66.315401));
    }

    public class Mapa implements OnMapReadyCallback {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            // Agregar marcadores de las farmacias
            for (Farmacia farmacia : listaFarmacias) {
                LatLng ubicacion = new LatLng(farmacia.getLatitud(), farmacia.getLongitud());
                googleMap.addMarker(new MarkerOptions()
                        .position(ubicacion)
                        .title(farmacia.getNombre())
                        .snippet(farmacia.getDireccion()));
            }

            // Habilitar la ubicación actual
            if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Aquí deberías solicitar permisos
                return;
            }
            googleMap.setMyLocationEnabled(true); // Habilitar la ubicación actual
            obtenerUbicacionActual(googleMap);
        }
    }

    private void obtenerUbicacionActual(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            LocationRequest locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
                    .setWaitForAccurateLocation(false)
                    .setMinUpdateIntervalMillis(10000)
                    .setMaxUpdateDelayMillis(10000)
                    .build();
            if (location != null) {
                LatLng ubicacionActual = new LatLng(location.getLatitude(), location.getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionActual, 14));
            } else {
                Log.e("MapaViewModel", "No se pudo obtener la ubicación actual.");
                // Podrías centrar en una ubicación predeterminada aquí
            }
        });
    }
}
