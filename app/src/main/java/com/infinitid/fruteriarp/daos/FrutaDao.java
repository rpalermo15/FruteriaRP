package com.infinitid.fruteriarp.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.infinitid.fruteriarp.entities.Fruta;

import java.util.List;

@Dao
public interface FrutaDao {

    @Query("Select * from Fruta")
    LiveData<List<Fruta>> getAll();

    @Insert
    void insert(Fruta fruta);

    @Update
    void update(Fruta fruta);

    @Delete
    void delete(Fruta fruta);

    @Query("Select * from Fruta where nombre like :nombre")
    Fruta findbynombre(String nombre);

    @Query("Select * from Fruta where id like :id")
    Fruta findbyid(int id);
}
