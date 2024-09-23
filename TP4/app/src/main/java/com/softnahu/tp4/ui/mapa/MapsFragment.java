package com.softnahu.tp4.ui.mapa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;
import com.softnahu.tp4.R;


public class MapsFragment extends Fragment {
    private MapaFragmentViewModel vm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(MapaFragmentViewModel.class);
        vm.getMMap().observe(getViewLifecycleOwner(), new Observer<MapaFragmentViewModel.Mapa>() {
            @Override
            public void onChanged(MapaFragmentViewModel.Mapa mapa) {
                ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(mapa);
//                ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(mapa);
            }
        });
        vm.obtenerMapa();
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}