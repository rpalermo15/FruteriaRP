package com.infinitid.fruteriarp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.infinitid.fruteriarp.models.FrutaViewModel;

public class FrutaFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;

    public FrutaFactory (@NonNull Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == FrutaViewModel.class){
            return (T) new FrutaViewModel(application);
        }
        return null;
    }
}
