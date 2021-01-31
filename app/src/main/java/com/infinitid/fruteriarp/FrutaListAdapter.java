package com.infinitid.fruteriarp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.infinitid.fruteriarp.entities.Fruta;

import java.util.List;

public class FrutaListAdapter extends ListAdapter<Fruta, FrutaViewHolder> {

    private OnItemClickListener listener;
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
        holder.bind(frutaActual.getNombre(), frutaActual.getDescripcion());

        ImageButton deleteButton = holder.itemView.findViewById(R.id.imageButtonDelete);
        deleteButton.setOnClickListener(view -> {
            if(listener!=null){
                listener.onItemDelete(frutaActual);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(frutaActual);
                }
            }
        });
    }

    static class frutaDiff extends DiffUtil.ItemCallback<Fruta>{
        @Override
        public boolean areItemsTheSame(@NonNull Fruta oldItem, @NonNull Fruta newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Fruta oldItem, @NonNull Fruta newItem) {
            return oldItem.getNombre().equals(newItem.getNombre()) &&  oldItem.getDescripcion().equals(newItem.getDescripcion());
        }
    }

    public interface OnItemClickListener {
        void onItemDelete(Fruta fruta);
        void onItemClick(Fruta fruta);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
