package com.infinitid.fruteriarp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.infinitid.fruteriarp.entities.Fruta;

import java.util.List;

public class FrutaListAdapter extends ListAdapter<Fruta, FrutaViewHolder> {

    public FrutaListAdapter(@NonNull DiffUtil.ItemCallback<Fruta>diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public FrutaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return FrutaViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull FrutaViewHolder holder, int position) {
        Fruta frutaActual = getItem(position);
        holder.bind(frutaActual.getNombre());
    }

    static class frutaDiff extends DiffUtil.ItemCallback<Fruta>{
        @Override
        public boolean areItemsTheSame(@NonNull Fruta oldItem, @NonNull Fruta newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Fruta oldItem, @NonNull Fruta newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }
}
