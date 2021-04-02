package com.infinitid.fruteriarp.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.infinitid.fruteriarp.domain.FrutaDto;
import com.infinitid.fruteriarp.entities.Fruta;
import com.infinitid.fruteriarp.repositories.FrutaRepository;
import com.infinitid.fruteriarp.repositories.FrutaRestRepository;

import java.util.List;

public class FrutaViewModel extends AndroidViewModel {

    private FrutaRestRepository frutaRepository;
    private final LiveData<List<FrutaDto>> frutas;

    public FrutaViewModel(Application application){
        super(application);
        frutaRepository = new FrutaRestRepository(application);
        frutas = frutaRepository.getFrutas();
    }

    public LiveData<List<FrutaDto>> getFrutas(){
        return frutas;
    }

    public void insert(FrutaDto fruta){
        frutaRepository.insert(fruta);
    }

    public void update(FrutaDto fruta) {
        frutaRepository.update(fruta);
    }

    public void delete(FrutaDto fruta) {
        frutaRepository.delete(fruta);
    }
}
