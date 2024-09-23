package com.softnahu.tp4;
import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> mUsuario;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMUsuario() {
        if (mUsuario == null) {
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public void verificarIngreso(String usuario, String password) {
        if (usuario.equalsIgnoreCase("admin1") && password.equals("admin1")) {
            mUsuario.setValue("Clave correcta");
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        } else {
            mUsuario.setValue("Clave incorrecta");
        }
    }
}
