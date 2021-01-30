package com.infinitid.fruteriarp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.infinitid.fruteriarp.entities.Fruta;
import com.infinitid.fruteriarp.models.FrutaViewModel;

public class MainActivity extends AppCompatActivity {

    private FrutaViewModel frutaViewModel;
    private static final int NEW_FRUTA_REQ_CODE = 1;

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
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_FRUTA_REQ_CODE && resultCode == RESULT_OK){
            Fruta fruta = new Fruta();
            fruta.setNombre(data.getStringExtra(AgregarFrutaActivity.EXTRA_MSG));
            frutaViewModel.insert(fruta);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_guardado, Toast.LENGTH_LONG).show();
        }
    }

}