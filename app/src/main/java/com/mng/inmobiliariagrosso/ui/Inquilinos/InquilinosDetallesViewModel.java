/*
package com.mng.inmobiliariagrosso.ui.Inquilinos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.mng.inmobiliariagrosso.modelo.Inquilino;
import com.mng.inmobiliariagrosso.request.ApiClient;

public class InquilinosDetallesViewModel extends ViewModel {

    private ApiClient api;
    private MutableLiveData<Inquilino> iMutable;

    public InquilinosDetallesViewModel() {
        this.api = ApiClient.getApi();
    }

    public void setInquilino(Bundle b) {
        iMutable.setValue(api.obtenerInquilino((Inmueble)b.getSerializable("inmuebles")));
    }

    public LiveData<Inquilino> getInquilinoMutable() {
        if (iMutable == null) {
            iMutable = new MutableLiveData<>();
        }
        return iMutable;
    }
}

 */