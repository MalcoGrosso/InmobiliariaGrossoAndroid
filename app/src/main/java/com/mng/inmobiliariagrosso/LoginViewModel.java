package com.mng.inmobiliariagrosso;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mng.inmobiliariagrosso.modelo.Usuario;
import com.mng.inmobiliariagrosso.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> error_visibility;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Integer> getErrorVisibility() {
        if (error_visibility == null) { error_visibility = new MutableLiveData<>(); }
        return error_visibility;
    }
/*
    public void login(String email, String pass) {
        ApiClient api = ApiClient.getApi();
        Propietario p = api.login(email, pass);

        if (p != null) {
            error_visibility.setValue(View.INVISIBLE);
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            error_visibility.setValue(View.VISIBLE);
        }
    }*/

    public void login(String email, String pass) {

        Usuario usuario = new Usuario(email, pass);
        Log.d("222222222", email);
        Log.d("111111111", pass);
        Log.d("999999999", usuario.toString());
        Call<String> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().login(usuario);
        Log.d("555555555", tokenPromesa.toString());
        tokenPromesa.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String token = response.body();
                    Log.d("salida", token);

                    SharedPreferences sharedP = context.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = sharedP.edit();
                    editor.putString("token", "Bearer " + token);
                    editor.commit();

                    Intent i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);


                }else{
                    Log.d("salida", "sin respuesta");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });

    }

}
