package com.saul.arf.ScenarioCamara;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.saul.arf.R;

import java.util.ArrayList;

public class AdaptadorMueble extends BaseAdapter {
    private ArrayList<Mueble> lista_mueble;
    private Context context;
    private Activity activity;
    private int id_usuario;
    private int id_proyecto;
    private int id_escenario;


    public AdaptadorMueble(Context context,ArrayList<Mueble> lista_mueble){
        this.context=context;
        this.lista_mueble=lista_mueble;
    }
    @Override
    public int getCount() {
        return lista_mueble.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_mueble.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.grid_escenarios_item,null);
        ImageView imagen = (ImageView) convertView.findViewById(R.id.imagen);
        TextView nombre = (TextView) convertView.findViewById(R.id.nombre);
        String nombre_str=lista_mueble.get(position).getName();
        nombre.setText(nombre_str);
        if(lista_mueble.get(position).getImage().isEmpty())
            Glide.with(context).load(R.drawable.foto_default).into(imagen);
        else{
            String foto=lista_mueble.get(position).getImage();
            Glide.with(context).load(foto).into(imagen);
        }
        return convertView;
    }
}
