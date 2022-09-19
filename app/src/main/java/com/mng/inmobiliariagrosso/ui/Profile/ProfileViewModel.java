package com.mng.inmobiliariagrosso.ui.Profile;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mng.inmobiliariagrosso.modelo.Propietario;
import com.mng.inmobiliariagrosso.request.ApiClient;

public class ProfileViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Propietario> mPropietario;
    private MutableLiveData<Boolean> propiedadEnable;
    private MutableLiveData<String> textoBoton;

    private ApiClient api= ApiClient.getApi();

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


}