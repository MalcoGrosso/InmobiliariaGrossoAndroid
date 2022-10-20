/*

package com.mng.inmobiliariagrosso.ui.Inquilinos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.mng.inmobiliariagrosso.request.ApiClient;

import java.util.List;

public class InquilinosViewModel extends ViewModel {
    private ApiClient api;
    private MutableLiveData<List<Inmueble>> inmueblesMutable;

    public InquilinosViewModel() {
        this.api = ApiClient.getApi();
    }

    public LiveData<List<Inmueble>> getRealEstatesMutable() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    public void setInmueblesMutable() {
        this.inmueblesMutable.setValue(api.obtenerPropiedadesAlquiladas());

    }
}


 */