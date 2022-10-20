package com.mng.inmobiliariagrosso.ui.Profile;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mng.inmobiliariagrosso.modelo.Propietario;
import com.mng.inmobiliariagrosso.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Propietario> mPropietario;
    private MutableLiveData<Boolean> propiedadEnable;
    private MutableLiveData<String> textoBoton;
    private ApiRetrofit api;

 //   private ApiClient api= ApiClient.getApi();

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();

    }
    public LiveData<Propietario> getMutablePropietario(){
        if(mPropietario==null){
            mPropietario=new MutableLiveData<>();
        }
        return mPropietario;
    }
    public LiveData<Boolean> getMutablePropiedadEnable(){
        if(propiedadEnable==null){
            propiedadEnable=new MutableLiveData<>();
        }
        return propiedadEnable;
    }
    public LiveData<String> getMutableTextoBoton(){
        if(textoBoton==null){
            textoBoton=new MutableLiveData<>();
        }
        return textoBoton;
    }


    private String ObtenerToken(){
        SharedPreferences sp= context.getSharedPreferences("token",0);
        String token=sp.getString("token","-1");
        return token;

    };

    public void ObtenerUsuario(){
        // Propietario p= api.obtenerUsuarioActual();


        Propietario propietario;

        SharedPreferences sp= context.getSharedPreferences("token",0);
        String token=sp.getString("token","-1");
        Call<Propietario> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerPerfil(token);
        tokenPromesa.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                Log.d("salida", response.toString());
                if (response.isSuccessful()) {

                    Propietario propietario = response.body();

                    mPropietario.setValue(propietario);

                } else {
                    Log.d("salida", "propietario sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });

        // mPropietario.setValue(p);

    }

    public void actualizarPerfil(@NonNull String boton, Propietario p){
        if(boton.equals("GUARDAR"))
        {

            Propietario propietario;

            SharedPreferences sp= context.getSharedPreferences("token",0);
            String token=sp.getString("token","-1");
            Call tokenPromesa = ApiRetrofit.getServiceInmobiliaria().actualizarPerfil(token,p);
            Log.d("salida", tokenPromesa.toString());
            tokenPromesa.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    Log.d("salida", response.toString());
                    if (response.isSuccessful()) {

                        Propietario propietario = response.body();

                        Toast.makeText(context.getApplicationContext(), "Propietario Actualizado Correctamente.", Toast.LENGTH_SHORT).show();
                        mPropietario.setValue(propietario);

                    } else {
                        Log.d("salida", "Actualizar propietario sin respuesta");

                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Log.d("salida ", t.getMessage());

                }
            });
            propiedadEnable.setValue(false);
            textoBoton.setValue("EDITAR");
        }
        else{
            propiedadEnable.setValue(true);
            textoBoton.setValue("GUARDAR");
        }
    }




/*
    public void ObtenerUsuario(){
        Propietario p= api.obtenerUsuarioActual();
        mPropietario.setValue(p);
    }
    public void actualizarPropietario(String boton, Propietario p){
        if(boton.equals("GUARDAR"))
        {
            api.actualizarPerfil(p);
            propiedadEnable.setValue(false);
            textoBoton.setValue("EDITAR");
        }
        else{
            propiedadEnable.setValue(true);
            textoBoton.setValue("GUARDAR");
        }
    }
*/

}