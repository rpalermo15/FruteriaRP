package com.infinitid.fruteriarp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class AgregarFrutaActivity extends AppCompatActivity {
    public static final String EXTRA_MSG = "com.infinitid.fruteriarp.MSG_GUARDAR";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_fruta);

        editText = findViewById(R.id.editIngresarNombre);

        final Button btnAgregar = findViewById(R.id.btnGuardar);
        btnAgregar.setOnClickListener(View -> {
            Intent respuesta = new Intent();
            if(TextUtils.isEmpty(editText.getText())){
                setResult(RESULT_CANCELED, respuesta);
            } else {
                String fruta = editText.getText().toString();
                respuesta.putExtra(EXTRA_MSG, fruta);
                setResult(RESULT_OK, respuesta);
            }
            finish();
        });
    }
}