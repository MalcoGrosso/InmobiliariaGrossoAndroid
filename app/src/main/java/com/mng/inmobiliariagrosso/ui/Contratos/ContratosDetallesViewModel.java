package com.mng.inmobiliariagrosso.ui.Contratos;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.mng.inmobiliariagrosso.R;
import com.mng.inmobiliariagrosso.modelo.Contrato;
import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.mng.inmobiliariagrosso.request.ApiClient;

public class ContratosDetallesViewModel extends ViewModel {
    private ApiClient api;
    private MutableLiveData<Contrato> cMutable;

    public ContratosDetallesViewModel() {
        this.api = ApiClient.getApi();
    }

    public void setContrato(Bundle b) {
        cMutable.setValue(api.obtenerContratoVigente((Inmueble)b.getSerializable("inmuebles")));
    }

    public LiveData<Contrato> getContratoMutable() {
        if (cMutable == null) {
            cMutable = new MutableLiveData<>();
        }
        return cMutable;
    }

    public void openPagos(View root) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("contrato", cMutable.getValue());
        Navigation.findNavController(root).navigate(R.id.pagosFragment, bundle);
    }
}