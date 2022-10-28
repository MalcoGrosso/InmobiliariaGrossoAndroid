package com.mng.inmobiliariagrosso.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mng.inmobiliariagrosso.modelo.Contrato;
import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.mng.inmobiliariagrosso.modelo.Propietario;
import com.mng.inmobiliariagrosso.modelo.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiRetrofit {

    
  //  private static final String PATH="http://practicastuds.ulp.edu.ar/api/";
    private static final String PATH="http://192.168.1.100:5000/API/";

    private static  ServiceInmobiliaria servicioInmobiliaria;

    public static ServiceInmobiliaria getServiceInmobiliaria(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        servicioInmobiliaria=retrofit.create(ServiceInmobiliaria.class);

        return servicioInmobiliaria;
    }




    public interface ServiceInmobiliaria{
//http://practicastuds.ulp.edu.ar/api/

        @POST("Propietarios/login")
        Call<String> login(@Body Usuario user);

        @GET("Propietarios")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @PUT("Propietarios/Actualizar")
        Call<Propietario> actualizarPerfil(@Header("Authorization") String token,@Body Propietario p);

        @POST("Propietarios/emailPedido")
        Call<Propietario> emailPedido(@Body String email);


        //Inmuebles
        @GET("Inmuebles")
        Call<List<Inmueble>> listarInmuebles (@Header("Authorization") String token);

        @PUT("Inmuebles/{id}")
        Call<Inmueble> actualizarInmueble(@Header("Authorization") String token, @Path("id") int idInmueble, @Body boolean disponible);

        @GET("inmuebles/contrato")
        Call<List<Contrato>> obtenerInmueblesAlquilados(@Header("Authorization") String token);

        @POST("inmuebles/Nuevo")
        Call<Inmueble>crearInmueble(@Header("Authorization") String token, @Body Inmueble inmueble);

        //Contratos
        @GET("Contratos/{id}")
        Call<Contrato> obtenerContratos(@Header("Authorization") String token, @Path("id") int idContrato);


    }

}
