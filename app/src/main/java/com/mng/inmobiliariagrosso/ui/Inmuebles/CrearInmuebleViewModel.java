package com.mng.inmobiliariagrosso.ui.Inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.mng.inmobiliariagrosso.request.ApiRetrofit;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearInmuebleViewModel extends AndroidViewModel {

    public Object getTipoInmueble;
    private MutableLiveData<byte []> foto;
    private MutableLiveData<Inmueble> inmue;
    private MutableLiveData<String[]> inmueble;
    private MutableLiveData<String[]> inmueble2;
    private Context context;

    public CrearInmuebleViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();

    }

    public LiveData<byte[]> getFoto(){
        if(foto==null){
            foto=new MutableLiveData<>();
        }
        return foto;
    }
    public LiveData<Inmueble> getInmueble(){
        if(inmue==null){
            inmue=new MutableLiveData<>();
        }
        return inmue;
    }

    public MutableLiveData<String[]> getTipoInmueble(){
        if(inmueble==null){
            inmueble=new MutableLiveData<>();
        }


        return inmueble;
    }

    public MutableLiveData<String[]> getUsoInmueble(){
        if(inmueble2==null){
            inmueble2=new MutableLiveData<>();
        }


        return inmueble2;
    }

    public void obtenerUsoInmueble() {
        getUsoInmueble();
        String[] inmu = new String[5];
        inmu[0] = "Casa";
        inmu[1] = "Departamento";
        inmu[2] = "Local";
        inmu[3] = "Deposito";
        inmu[4] = "Otros";

        inmueble2.setValue(inmu);

    }


    public void obtenerTipoInmueble() {
        getTipoInmueble();
        String[] inmu = new String[2];
        inmu[0] = "Residencial";
        inmu[1] = "Comercial";
        inmueble.setValue(inmu);
    }


    public void respuestaDeCamara(int requestCode, int resultCode, @Nullable Intent data, int REQUEST_IMAGE_CAPTURE) {
        Log.d("salida", requestCode + "");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Recupero los datos provenientes de la camara.
            Bundle extras = data.getExtras();
            //Casteo a bitmap lo obtenido de la camara.
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);

            //Rutina para convertir a un arreglo de byte los datos de la imagen
            byte [] b=baos.toByteArray();
            foto.setValue(b);

        }
    }

    public void crearInmueble(Inmueble inmueble) {
        if(foto.getValue()!=null ) {
            Log.d("paso", inmueble.toString());
            SharedPreferences sp = context.getSharedPreferences("token", 0);
            String token = sp.getString("token", "-1");
            Call<Inmueble> inm = ApiRetrofit.getServiceInmobiliaria().crearInmueble(token, inmueble);
            inm.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful()) {
                        inmue.postValue(response.body());
                        Toast.makeText(context, "Se guardo con exito", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("paso", response.code() + " " + response.message() + " " + response.body());
                    }
                }

                @Override
                public void onFailure(Call<Inmueble> call, Throwable t) {
                    Toast.makeText(context, "hubo un error inesperado" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(context, "debe elegir una foto", Toast.LENGTH_SHORT).show();
        }
    }


}