package com.mng.inmobiliariagrosso.ui.Contratos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mng.inmobiliariagrosso.modelo.Contrato;
import com.mng.inmobiliariagrosso.modelo.Propietario;
import com.mng.inmobiliariagrosso.request.ApiRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {

    private Context context;
 //   private MutableLiveData<List<Inmueble>> inmueblesMutable;
    private MutableLiveData<List<Contrato>> contratosMutable;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
/*
     public ContratosViewModel() {
        this.api = ApiClient.getApi();
    }
*/
    /*
    public LiveData<List<Inmueble>> getContratosMutable() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }
*/
public LiveData<List<Contrato>> getContratosMutable() {
    if (contratosMutable == null) {
        contratosMutable = new MutableLiveData<>();
    }
    return contratosMutable;
}

    public void setInmuebles(){
        Propietario propietario;
        SharedPreferences sp = context.getSharedPreferences("token",0);
        String token = sp.getString("token","-1");
     //   Call<List<Inmueble>> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerInmueblesAlquilados(token);
          Call<List<Contrato>> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerInmueblesAlquilados(token);
        tokenPromesa.enqueue(new Callback<List<Contrato>>() {


            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                Log.d("salida", response.toString());
                if(response.isSuccessful()){

                    List<Contrato> contratos = response.body();
                    contratosMutable.postValue(contratos);;
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }





/*
    public void setInmueblesMutable() {
        this.inmueblesMutable.setValue(api.obtenerPropiedadesAlquiladas());
    }

 */
}