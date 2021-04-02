package com.infinitid.fruteriarp.rest;

import com.infinitid.fruteriarp.domain.FrutaDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RestApiInterface {

    @GET("/frutas")
    Call<List<FrutaDto>> getFrutas();

    @GET("/frutas/{id}")
    Call<FrutaDto> getFrutas(@Part("id") int id);

    @POST("/frutas")
    Call<ResponseBody> addFruta(@Query("nombre") String nombre, @Query("descripcion") String descripcion);

    @PUT("/frutas/{id}")
    Call<ResponseBody> updateFruta(@Part("id") int id, @Query("nombre") String nombre, @Query("descripcion") String descripcion);

    @DELETE("/frutas/{id}")
    Call<ResponseBody> deleteFruta(@Part("id") int id);

}
