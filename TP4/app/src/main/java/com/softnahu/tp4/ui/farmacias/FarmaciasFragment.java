package com.softnahu.tp4.ui.farmacias;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softnahu.tp4.R;
import com.softnahu.tp4.databinding.FragmentFarmaciasBinding;
import com.softnahu.tp4.model.Farmacia;

import java.util.ArrayList;

public class FarmaciasFragment extends Fragment {

    private FarmaciasFragmentViewModel mViewModel;
    private FragmentFarmaciasBinding binding;

    public static FarmaciasFragment newInstance() {
        return new FarmaciasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(FarmaciasFragmentViewModel.class);
        binding = FragmentFarmaciasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Observa los cambios en la lista de farmacias
        mViewModel.getMLista().observe(getViewLifecycleOwner(), farmacias -> {
            FarmaciaAdapter adapter = new FarmaciaAdapter(farmacias, inflater);
            GridLayoutManager grid = new GridLayoutManager(getContext(), 1);
            binding.listaFarmacias.setAdapter(adapter);
            binding.listaFarmacias.setLayoutManager(grid);
        });

        // Llama a imprimirLista después de configurar la vista
        mViewModel.imprimirLista();

        return root;
    }
}