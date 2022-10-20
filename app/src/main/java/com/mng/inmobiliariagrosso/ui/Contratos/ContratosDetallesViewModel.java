

package com.mng.inmobiliariagrosso.ui.Contratos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mng.inmobiliariagrosso.modelo.Contrato;
import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.mng.inmobiliariagrosso.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosDetallesViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> cMutable;
    private Context context;
    private Inmueble i;
    private Contrato c;

    public ContratosDetallesViewModel(@NonNull Application application) {
        super(application);
        cMutable = new MutableLiveData<>();
        context = application.getApplicationContext();
    }


    public MutableLiveData<Contrato> getContrato() {
        return cMutable;
    }

    public void setContrato(Bundle bundle) {
        c = (Contrato) bundle.getSerializable("contratos");
        SharedPreferences sp = context.getSharedPreferences("token",0);
        String token = sp.getString("token","-1");
        Call<Contrato> con = ApiRetrofit.getServiceInmobiliaria().obtenerContratos(token,  c.getIdContrato());
        con.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                Log.d("salida", response.toString());
                Log.d("salida", "hola");

                if(response.isSuccessful()){
                    cMutable.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

    }

}