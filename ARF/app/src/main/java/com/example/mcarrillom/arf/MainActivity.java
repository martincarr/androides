package com.example.mcarrillom.arf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnAcceder;
    private Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcceder = findViewById(R.id.btnAcceso);
        btnRegistro = findViewById(R.id.btnRegistro);

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAcceso();
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRegistro();
            }
        });
    }

    public void getAcceso(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void getRegistro(){
        Intent intent = new Intent( this,RegistroActivity.class);
        startActivity(intent);
    }
}
