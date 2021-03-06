package com.infinitid.fruteriarp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.infinitid.fruteriarp.daos.FrutaDao;
import com.infinitid.fruteriarp.entities.Fruta;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Fruta.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FrutaDao frutaDao();

    private static volatile AppDatabase instance;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static AppDatabase getInstance(final Context context) {
        if (instance == null){
           instance= Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "fruteria")
                   .fallbackToDestructiveMigration()
                   .build();
        }
        return instance;
    }
}
