package com.saul.arf.ScenarioCamara;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.saul.arf.Escenarios.Escenario;
import com.saul.arf.R;

import pl.droidsonroids.gif.GifImageView;

public class LoadSessionActivity extends AppCompatActivity {
    private GifImageView gifImageView;
    private int id_usuario;
    private int id_proyecto;
    private Escenario escenario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        gifImageView = findViewById(R.id.progressbar);
        gifImageView.setVisibility(View.VISIBLE);
        escenario=(Escenario) getIntent().getExtras().getSerializable("Escenario");
        id_usuario=getIntent().getExtras().getInt("id");
        id_proyecto=getIntent().getExtras().getInt("id_proyecto");
        CategoriasAWS categoriasAWS=new CategoriasAWS(id_usuario,id_proyecto,escenario,this,this);
        categoriasAWS.execute();
    }

}
