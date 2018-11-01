package com.example.mcarrillom.arf;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        Context context = getApplicationContext();
        CharSequence textBienvenida = "Bienvenido!";
        int time = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, textBienvenida, time);
        toast.show();
    }
}
