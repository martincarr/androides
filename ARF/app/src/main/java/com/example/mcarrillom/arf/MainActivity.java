package com.example.mcarrillom.arf;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.activity_arf,new fragment_login()).commit();
        Toast.makeText(this, "Augmented Reality Furniture", Toast.LENGTH_SHORT).show();
    }
}
