package com.infinitid.fruteriarp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class AgregarFrutaActivity extends AppCompatActivity {
    public static final String EXTRA_MSG_ID = "com.infinitid.fruteriarp.MSG_GUARDAR_ID";
    public static final String EXTRA_MSG_NOMBRE = "com.infinitid.fruteriarp.MSG_GUARDAR_NOMBRE";
    public static final String EXTRA_MSG_DESCRIPCION = "com.infinitid.fruteriarp.MSG_GUARDAR_DESCRIPCION";

    private EditText editTextNombre;
    private EditText editTextDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_fruta);

        editTextNombre = findViewById(R.id.editIngresarNombre);
        editTextDescripcion = findViewById(R.id.editIngresarDescripcion);

        final Button btnAgregar = findViewById(R.id.btnGuardar);
        btnAgregar.setOnClickListener(View -> {
            Intent respuesta = new Intent();
            if(TextUtils.isEmpty(editTextNombre.getText())){
                setResult(RESULT_CANCELED, respuesta);
            } else {
                String fruta = editTextNombre.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                respuesta.putExtra(EXTRA_MSG_NOMBRE, fruta);
                respuesta.putExtra(EXTRA_MSG_DESCRIPCION, descripcion);

                int id = getIntent().getIntExtra(EXTRA_MSG_ID, -1);
                if(id != -1){
                    respuesta.putExtra(EXTRA_MSG_ID, id);
                }
                setResult(RESULT_OK, respuesta);
            }
            finish();
        });

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_MSG_ID)){
            editTextNombre.setText(intent.getStringExtra(EXTRA_MSG_NOMBRE));
            editTextDescripcion.setText(intent.getStringExtra(EXTRA_MSG_DESCRIPCION));
        }
    }
}