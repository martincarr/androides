package com.saul.arf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import com.saul.arf.Proyectos.AdapterItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.saul.arf.Proyectos.Proyecto;
import java.util.ArrayList;

public class MisProyectosActivity extends AppCompatActivity {
    int id_usuario;
    private ListView lista;
    private AdapterItem adaptador;
    private ArrayList<Proyecto> listaProyectos;
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_proyectos);
        id_usuario=getIntent().getExtras().getInt("id");
        ImageButton agregar=findViewById(R.id.agregarProyecto);
        context=this;
        activity=this;
        String json=null;
        MisProyectosAWS misProyectosAWS=new MisProyectosAWS(id_usuario,1);
        misProyectosAWS.execute();
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AgregarProyectoActivity.class);
                intent.putExtra("id_usuario",id_usuario);
                context.startActivity(intent);
                activity.finish();
            }
        });

        while ((json=misProyectosAWS.getJson())==null);
        lista=(ListView) findViewById(R.id.list);

        try {
            adaptador=new AdapterItem(this,getArrayList(json),this,id_usuario);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lista.setAdapter(adaptador);
    }
    private ArrayList<Proyecto> getArrayList(String json) throws JSONException {
        ArrayList<Proyecto> listaItems=new ArrayList<Proyecto>();
        JSONObject respuestaProyecto = new JSONObject(json);
        JSONArray proyectos = (JSONArray) respuestaProyecto.get("projects");

        int id;
        String client_firstname;
        String client_lastname;
        String client_phone;
        String client_email;

        for(int x=0;x<proyectos.length();x++){
            JSONObject aux=proyectos.getJSONObject(x);
            id= (int) aux.get("id");
            client_firstname=(String)aux.get("client_firstname");
            client_lastname=(String)aux.get("client_lastname");
            client_phone=(String)aux.get("client_phone");
            client_email=(String)aux.get("client_email");
            listaItems.add(new Proyecto(id,client_firstname,client_lastname,client_phone,client_email));
        }
        listaProyectos=listaItems;
        return listaItems;
    }


}


