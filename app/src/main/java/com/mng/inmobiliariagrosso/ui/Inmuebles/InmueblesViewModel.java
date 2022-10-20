package com.mng.inmobiliariagrosso.ui.Inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.mng.inmobiliariagrosso.modelo.Propietario;
import com.mng.inmobiliariagrosso.request.ApiRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> inmueblesMutable;
    private Context context;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<List<Inmueble>> getInmueblesMutable() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    public void setInmuebles(){
        Propietario propietario;
        SharedPreferences sp = context.getSharedPreferences("token",0);
        String token = sp.getString("token","-1");
        Call<List<Inmueble>> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().listarInmuebles(token);
        tokenPromesa.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                Log.d("salida", response.toString());
                if(response.isSuccessful()){

                    List<Inmueble> inmuebles = response.body();
                    inmueblesMutable.postValue(inmuebles);;
                }

            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}