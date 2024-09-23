package com.softnahu.tp4.ui.mapa;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    private MutableLiveData<Mapa> mMap;
    private ArrayList<Farmacia> listaFarmacias;

    public MapaFragmentViewModel(@NonNull Application application) {
        super(application);
        // Inicia la lista de farmacias
        listaFarmacias = new ArrayList<>();
        cargarFarmacias();
    }

    public LiveData<Mapa> getMMap() {
        if (mMap == null) {
            mMap = new MutableLiveData<>();
        }
        return mMap;
    }

    public void obtenerMapa() {
        Mapa m = new Mapa();
        mMap.setValue(m);
    }

    // Método para cargar las farmacias con coordenadas, fotos y detalles
    private void cargarFarmacias() {
        listaFarmacias.add(new Farmacia("Farmacia Santa Lucia", "Avenida Serrana Manzana 140 Parcela 03, La Punta San Luis", R.drawable.farmaciasantalucia, "Entregas a domicilio: 2664-584038", -33.183099, -66.313148));
        listaFarmacias.add(new Farmacia("Farmacia Sierras Del Sol", "La Punta San Luis", R.drawable.farmaciasierrasdelsol, "Atencion de: domingo a lunes 9 a.m - 12 a.m", -33.185517, -66.312102));
        listaFarmacias.add(new Farmacia("Farmacia Quintana", "Av. Serrana, La Punta San Luis", R.drawable.farmaciaquintana, "Atencion de: lunes a sabado 8:30 a.m - 1:30 p.m, 16:30 p.m - 21:30 p.m", -33.181782, -66.313713));
        listaFarmacias.add(new Farmacia("Farmacia Divino Niño", "Mod 3, Mzna 47, Casa 11, La Punta San Luis", R.drawable.farmaciadivinonino, "Atencion de: lunes a sabado 8:30 a.m - 10:00 p.m", -33.184297, -66.315401));
    }

    public class Mapa implements OnMapReadyCallback {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            // Cambiar el tipo de mapa a normal (calle)
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            // Agregar marcadores de las farmacias
            for (Farmacia farmacia : listaFarmacias) {
                LatLng ubicacion = new LatLng(farmacia.getLatitud(), farmacia.getLongitud());
                Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(ubicacion)
                        .title(farmacia.getNombre())
                        .snippet(farmacia.getDireccion())); // El snippet es el texto que se muestra debajo del título
                marker.setTag(farmacia); // Guarda la farmacia como "tag" del marcador
            }

            // Centrar el mapa en la primera farmacia (puedes cambiar esto para que use la ubicación actual)
            LatLng firstFarmacia = new LatLng(listaFarmacias.get(0).getLatitud(), listaFarmacias.get(0).getLongitud());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstFarmacia, 15));

            // Configura el adaptador de ventanas de información personalizado
            googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null; // Usamos el método `getInfoContents` en su lugar
                }

                @Override
                public View getInfoContents(Marker marker) {
                    // Infla el layout personalizado de la ventana de información
                    View infoWindow = View.inflate(getApplication(), R.layout.custom_info_window, null);

                    // Obtén los views del layout
                    TextView tvNombre = infoWindow.findViewById(R.id.tvNombre);
                    TextView tvDireccion = infoWindow.findViewById(R.id.tvDireccion);
                    ImageView ivFoto = infoWindow.findViewById(R.id.ivFoto);

                    // Obtén la farmacia del tag del marcador
                    Farmacia farmacia = (Farmacia) marker.getTag();

                    if (farmacia != null) {
                        tvNombre.setText(farmacia.getNombre());
                        tvDireccion.setText(farmacia.getDireccion());
                        ivFoto.setImageDrawable(ContextCompat.getDrawable(getApplication(), farmacia.getFoto())); // Establece la imagen desde drawable
                    }

                    return infoWindow;
                }
            });
        }
    }
}