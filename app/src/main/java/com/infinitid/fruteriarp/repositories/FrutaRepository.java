package com.infinitid.fruteriarp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.infinitid.fruteriarp.daos.FrutaDao;
import com.infinitid.fruteriarp.database.AppDatabase;
import com.infinitid.fruteriarp.entities.Fruta;

import java.util.List;

public class FrutaRepository {
    private FrutaDao frutaDao;

    private LiveData<List<Fruta>> frutas;

    public FrutaRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        frutaDao = db.frutaDao();
        frutas = frutaDao.getAll();
    }

    public LiveData<List<Fruta>> getFrutas(){
        return frutas;
    }

    public void insert(Fruta fruta){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            frutaDao.insert(fruta);
        });
    }

    public void update(Fruta fruta){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            frutaDao.update(fruta);
        });
    }

    public void delete(Fruta fruta) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            frutaDao.delete(fruta);
        });
    }
}
