package com.infinitid.fruteriarp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.infinitid.fruteriarp.domain.FrutaDto;
import com.infinitid.fruteriarp.entities.Fruta;
import com.infinitid.fruteriarp.models.FrutaViewModel;

public class MainActivity extends AppCompatActivity {

    private FrutaViewModel frutaViewModel;
    private static final int NEW_FRUTA_REQ_CODE = 1;
    private static final int UPDATE_FRUTA_REQ_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFrutas);
        final FrutaListAdapter adapter = new FrutaListAdapter(new FrutaListAdapter.frutaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        frutaViewModel = new ViewModelProvider(this, new FrutaFactory(getApplication())).get(FrutaViewModel.class);

        frutaViewModel.getFrutas().observe(this, frutas -> {
            adapter.submitList(frutas);
        });

        FloatingActionButton fab = findViewById(R.id.btnAgregar);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, AgregarFrutaActivity.class);

            startActivityForResult(intent,NEW_FRUTA_REQ_CODE);
        });

        adapter.setOnItemClickListener(new FrutaListAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(FrutaDto fruta) {
                frutaViewModel.delete(fruta);
                Toast.makeText(getApplicationContext(), R.string.borrado, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(FrutaDto fruta) {
                Intent intent = new Intent(MainActivity.this, AgregarFrutaActivity.class);
                intent.putExtra(AgregarFrutaActivity.EXTRA_MSG_ID, fruta.getId());
                intent.putExtra(AgregarFrutaActivity.EXTRA_MSG_NOMBRE, fruta.getNombre());
                intent.putExtra(AgregarFrutaActivity.EXTRA_MSG_DESCRIPCION, fruta.getDescripcion());
                startActivityForResult( intent, UPDATE_FRUTA_REQ_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_FRUTA_REQ_CODE && resultCode == RESULT_OK){
            FrutaDto fruta = new FrutaDto();
            fruta.setNombre(data.getStringExtra(AgregarFrutaActivity.EXTRA_MSG_NOMBRE));
            fruta.setDescripcion(data.getStringExtra(AgregarFrutaActivity.EXTRA_MSG_DESCRIPCION));
            frutaViewModel.insert(fruta);
        } else if (requestCode == UPDATE_FRUTA_REQ_CODE && resultCode == RESULT_OK) {
          int id = data.getIntExtra(AgregarFrutaActivity.EXTRA_MSG_ID, -1);
            if (id == -1){
                Toast.makeText(getApplicationContext(), R.string.no_guardado, Toast.LENGTH_LONG).show();
            }
            String nombre = data.getStringExtra(AgregarFrutaActivity.EXTRA_MSG_NOMBRE);
            String descripcion = data.getStringExtra(AgregarFrutaActivity.EXTRA_MSG_DESCRIPCION);

            FrutaDto fruta = new FrutaDto(id, nombre, descripcion);
            frutaViewModel.update(fruta);
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.no_guardado, Toast.LENGTH_LONG).show();
        }
    }
}