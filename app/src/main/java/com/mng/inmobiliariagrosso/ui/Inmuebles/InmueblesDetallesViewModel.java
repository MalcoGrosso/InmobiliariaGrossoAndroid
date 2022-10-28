package com.mng.inmobiliariagrosso.ui.Inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.mng.inmobiliariagrosso.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesDetallesViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmueble ;
    private Inmueble i;
    private Context context;

    public InmueblesDetallesViewModel(@NonNull Application application) {
        super(application);
        inmueble = new MutableLiveData<>();
        context = application.getApplicationContext();
    }

    public MutableLiveData<Inmueble> getInmueble() {
        return inmueble;
    }

    public void setInmueble(Bundle bundle){
        i = (Inmueble) bundle.getSerializable("inmuebles");
        inmueble.setValue(i);
    }



    public void setDisponible(boolean dis){

        i.setEstado(dis);
        SharedPreferences sp = context.getSharedPreferences("token",0);
        String token = sp.getString("token","-1");
        Call<Inmueble> inm = ApiRetrofit.getServiceInmobiliaria().actualizarInmueble(token, i.getIdInmueble(), i.isEstado());
        inm.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}