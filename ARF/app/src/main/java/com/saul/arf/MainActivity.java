/*
 * @Autor: Martin Alejandro Carrillo Mendoza
 * @Fecha: Enero 2019
 * @Proyecto : TT 2018-A002
 */
package com.saul.arf;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.saul.arf.Login.LoginActivity;
import com.saul.arf.Menu.MenuActivity;
import com.saul.arf.Registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnAcceder;
    private Button btnRegistro;
    private Button btnPrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcceder = findViewById(R.id.btnAcceso);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnPrueba = findViewById(R.id.btnTrialArf);

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

        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPrueba();
            }
        });
    }

    public void getAcceso(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void getRegistro(){
        Intent intent = new Intent( this, RegistroActivity.class);
        startActivity(intent);
    }

    public void getPrueba(){
        //Intent intent = new Intent ( this,SessionActivity.class);
        //startActivity(intent);
        //Intent intent=new Intent(this, MenuActivity.class);
        //startActivity(intent);
    }
}

