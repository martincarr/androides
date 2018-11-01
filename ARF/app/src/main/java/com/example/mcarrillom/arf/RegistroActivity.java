package com.example.mcarrillom.arf;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private Button btnRegreso;
    private Button btnRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Context context = getApplicationContext();
        CharSequence datos = "Ingrese sus datos para registro";
        int time = Toast.LENGTH_SHORT;

        btnRegistro = findViewById(R.id.btn_acceso_reg);
        btnRegreso = findViewById(R.id.btn_regresar_reg);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });

        btnRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void registrarUsuario(){

    }

}
