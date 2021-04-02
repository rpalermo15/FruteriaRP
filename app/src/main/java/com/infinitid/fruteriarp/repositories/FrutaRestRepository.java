package com.infinitid.fruteriarp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.infinitid.fruteriarp.daos.FrutaDao;
import com.infinitid.fruteriarp.database.AppDatabase;
import com.infinitid.fruteriarp.domain.FrutaDto;
import com.infinitid.fruteriarp.entities.Fruta;
import com.infinitid.fruteriarp.rest.RestApiClient;
import com.infinitid.fruteriarp.rest.RestApiInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrutaRestRepository {
    private RestApiInterface apiService = RestApiClient.getClient().create(RestApiInterface.class);

    private MutableLiveData<List<FrutaDto>> frutas = new MutableLiveData<>();

    public FrutaRestRepository(Application application){
        loadFrutas();
    }

    public void loadFrutas(){
        frutas.setValue(new ArrayList<>());
        Call<List<FrutaDto>> call = apiService.getFrutas();
        call.enqueue(new Callback<List<FrutaDto>>() {
            @Override
            public void onResponse(Call<List<FrutaDto>> call, Response<List<FrutaDto>> response) {
                List<FrutaDto> misFrutas = response.body();
                if(misFrutas != null){
                    frutas.setValue(misFrutas);
                }
            }

            @Override
            public void onFailure(Call<List<FrutaDto>> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<List<FrutaDto>> getFrutas(){
        return frutas;
    }

    public void insert(FrutaDto fruta){
        apiService.addFruta(fruta.getNombre(), fruta.getDescripcion()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loadFrutas();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public void update(FrutaDto fruta){
        apiService.updateFruta(fruta.getId(), fruta.getNombre(), fruta.getDescripcion()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loadFrutas();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public void delete(FrutaDto fruta) {
        apiService.deleteFruta(fruta.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loadFrutas();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
}
