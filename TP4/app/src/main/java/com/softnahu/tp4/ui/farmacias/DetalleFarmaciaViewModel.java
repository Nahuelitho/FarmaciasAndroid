package com.softnahu.tp4.ui.farmacias;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softnahu.tp4.model.Farmacia;

public class DetalleFarmaciaViewModel extends AndroidViewModel {
    private MutableLiveData<Farmacia> mFarmacia;

    public DetalleFarmaciaViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Farmacia> getMFarmacia(){
        if(mFarmacia == null){
            mFarmacia = new MutableLiveData<>();
        }
        return mFarmacia;
    }

    public void recibeFarmacia(Bundle bundle){
        Farmacia f = (Farmacia) bundle.getSerializable("farmacia");
        if(f != null){
            mFarmacia.setValue(f);
        }
    }

}