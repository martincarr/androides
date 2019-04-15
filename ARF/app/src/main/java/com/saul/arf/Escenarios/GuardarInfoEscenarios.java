package com.saul.arf.Escenarios;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.saul.arf.R;
import com.saul.arf.ScenarioCamara.LoadSessionActivity;
import com.saul.arf.SessionActivity;

import pl.droidsonroids.gif.GifImageView;
import static java.lang.Thread.sleep;

public class GuardarInfoEscenarios extends AppCompatActivity {
    private Button btn_cancelar;
    private Button btn_guardarInfo;
    private EditText et_nombreProyecto;
    private EditText et_presupuesto;
    private EditText editTextTamanio;
    private EditText editTextTipoHabitacion;
    private EditText editTextTipoMuebles;
    private GifImageView gifImageView;
    private Context context;
    private Activity activity;
    private int id_usuario;
    private int id_proyecto;
    private String escenarios;
    private String nombreEscenario;
    private String tamanioEscenario;
    private String tipoHabitacion;
    private String tipoMuebles;
    private String presupuesto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_info_escenario);
        id_usuario=getIntent().getExtras().getInt("id");
        id_proyecto=getIntent().getExtras().getInt("id_proyecto");
        escenarios=getIntent().getExtras().getString("escenarios");
        context=this;
        activity=this;

        btn_cancelar = findViewById(R.id.btn_cancelar);
        btn_guardarInfo = findViewById(R.id.btn_guardarInfo);
        et_nombreProyecto = findViewById(R.id.et_nombreProyecto);
        et_presupuesto = findViewById(R.id.ed_presupuesto);
        editTextTamanio = findViewById(R.id.spinner_tam);
        editTextTipoHabitacion = findViewById(R.id.spinner_tipoHab);
        editTextTipoMuebles = findViewById(R.id.spinner_muebles);
        gifImageView =findViewById(R.id.progressbar);
        gifImageView.setVisibility(View.GONE);

        btn_guardarInfo.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String nombreProyecto = et_nombreProyecto.getText().toString();
                String presupuesto_str =et_presupuesto.getText().toString();
                String tamanio_str= editTextTamanio.getText().toString();
                String tipoHabitacion = editTextTipoHabitacion.getText().toString();
                String tipoMuebles = editTextTipoMuebles.getText().toString();
                if(nombreProyecto.isEmpty()){
                    et_nombreProyecto.setError("¡Ingrese un nombre!");
                }
                else if(tamanio_str.isEmpty()){
                    editTextTamanio.setError("¡Ingrese el tamaño!");
                }
                else if(tipoHabitacion.isEmpty()){
                    editTextTipoHabitacion.setError("¡Ingrese el tipo de habitación!");
                }
                else if(tipoMuebles.isEmpty()){
                    editTextTipoMuebles.setError("¡Ingrese el tipo de mueble!");
                }
                else{
                    gifImageView.setVisibility(View.VISIBLE);
                    if(presupuesto_str.isEmpty())
                        presupuesto_str="";


                    Escenario escenario=new Escenario(id_proyecto,nombreProyecto,presupuesto_str,tamanio_str,tipoHabitacion,tipoMuebles);

                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(context, LoadSessionActivity.class);
                    intent.putExtra("Escenario",escenario);
                    intent.putExtra("id",id_usuario);
                    intent.putExtra("id_proyecto",id_proyecto);
                    activity.startActivity(intent);
                    finish();
                }
            }
        });


        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SessionActivity.class);
                intent.putExtra("escenarios",escenarios);
                intent.putExtra("id",id_usuario);
                intent.putExtra("id_proyecto",id_proyecto);
                finish();
            }
        });
    }


    public String getNombreEscenario() {
        return nombreEscenario;
    }

    public void setNombreEscenario(String nombreEscenario) {
        this.nombreEscenario = nombreEscenario;
    }

    public String getTamanioEscenario() {
        return tamanioEscenario;
    }

    public void setTamanioEscenario(String tamanioEscenario) {
        this.tamanioEscenario = tamanioEscenario;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getTipoMuebles() {
        return tipoMuebles;
    }

    public void setTipoMuebles(String tipoMuebles) {
        this.tipoMuebles = tipoMuebles;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }
}