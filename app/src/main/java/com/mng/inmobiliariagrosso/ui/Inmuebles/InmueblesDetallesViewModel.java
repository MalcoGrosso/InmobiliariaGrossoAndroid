package com.mng.inmobiliariagrosso.ui.Inmuebles;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.mng.inmobiliariagrosso.request.ApiClient;

public class InmueblesDetallesViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private ApiClient api;
    private MutableLiveData<Inmueble> iMutable;

    public InmueblesDetallesViewModel() {
        this.api = ApiClient.getApi();
    }

    public void setInmueble(Bundle b) {
        iMutable.setValue((Inmueble)b.getSerializable("inmuebles"));
    }

    public LiveData<Inmueble> getInmuebleMutable() {
        if (iMutable == null) {
            iMutable = new MutableLiveData<>();
        }
        return iMutable;
    }

    public void setDisponible(boolean b) {
        Inmueble i = iMutable.getValue();
        i.setEstado(b);
        api.actualizarInmueble(i);
        iMutable.setValue(i);
    }
}