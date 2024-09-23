package com.softnahu.tp4.ui.farmacias;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softnahu.tp4.MainActivity;
import com.softnahu.tp4.model.Farmacia;

import java.util.ArrayList;

public class FarmaciasFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Farmacia>> mLista;

    public FarmaciasFragmentViewModel(@NonNull Application application) {
        super(application);
        mLista = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<ArrayList<Farmacia>> getMLista() {
        return mLista;
    }

    public void imprimirLista() {
        if (MainActivity.listaFarmacias != null && !MainActivity.listaFarmacias.isEmpty()) {
            mLista.setValue(MainActivity.listaFarmacias);
        } else {
            mLista.setValue(new ArrayList<>()); // Lista vacía si no hay farmacias
        }
    }
}