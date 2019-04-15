package com.saul.arf.Escenarios;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

public class FotosClickListener implements AdapterView.OnItemClickListener{
    private Activity activity;
    private Context context;
    private int id_usuario;
    private int id_proyecto;
    private Escenario escenario;

    public FotosClickListener(Activity activity,Context context,int id_usuario,int id_proyecto,Escenario escenario){
        this.activity=activity;
        this.context=context;
        this.id_usuario=id_usuario;
        this.id_proyecto=id_proyecto;
        this.escenario=escenario;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) parent.getItemAtPosition(position);
        Intent intent = new Intent(context, FotoActivity.class);
        intent.putExtra("foto",item);
        intent.putExtra("escenario",escenario);
        intent.putExtra("id",id_usuario);
        intent.putExtra("id_proyecto",id_proyecto);
        activity.startActivity(intent);
        activity.finish();
    }
}
