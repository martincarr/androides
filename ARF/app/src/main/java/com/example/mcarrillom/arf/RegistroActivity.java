package com.example.mcarrillom.arf;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static android.os.SystemClock.sleep;

public class RegistroActivity extends AppCompatActivity {
    private Button btnRegresar;
    private Button btnRegistrarUsr;
    private EditText editTextNombre;
    private EditText editTextCorreo;
    private EditText editTextPass;
    private RegistroAWS registroAWS;
    private String url_aws;
    private static int codigo_aws;
    private static String usuario_aws;
    private static String mensajeRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnRegistrarUsr = findViewById(R.id.btn_acceso_reg);
        btnRegresar = findViewById(R.id.btn_regresar_reg);
        editTextNombre = findViewById(R.id.nombre_usr_reg);
        editTextCorreo = findViewById(R.id.correo_usr_reg);
        editTextPass = findViewById(R.id.pwd_usr_reg);
        this.setCodigo_aws(-1);
        this.setUsuario_aws("");

        btnRegistrarUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumirServicioAWS();
                if (RegistroActivity.getCodigo_aws() == 1003){
                    imprimirMensaje(RegistroActivity.getMensajeRegistro());
                }
                else{
                    imprimirMensaje(RegistroActivity.getMensajeRegistro());
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void consumirServicioAWS(){
        if( (editTextNombre.getText().toString() != "") || (editTextCorreo.getText().toString() != "") || (editTextPass.getText().toString() != "") ){
            this.setUrl_aws("https://0kg5bbzwbc.execute-api.us-west-2.amazonaws.com/dev/arf-register");
            this.setRegistroAWS(new RegistroAWS(this, this.getUrl_aws(),editTextNombre.getText().toString(),editTextCorreo.getText().toString(),editTextPass.getText().toString()));
            this.getRegistroAWS().execute();
            sleep(4000);
        }else{
            imprimirMensaje("Datos incompletos!");
        }
    }

    public void imprimirMensaje(String mensaje){
        Context context = getApplicationContext();
        CharSequence text = mensaje;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public String getUrl_aws() {
        return url_aws;
    }

    public void setUrl_aws(String url_aws) {
        this.url_aws = url_aws;
    }

    public static int getCodigo_aws() {
        return codigo_aws;
    }

    public static void setCodigo_aws(int codigo_aws) {
        RegistroActivity.codigo_aws = codigo_aws;
    }

    public static String getUsuario_aws() {
        return usuario_aws;
    }

    public static void setUsuario_aws(String usuario_aws) {
        RegistroActivity.usuario_aws = usuario_aws;
    }

    public static String getMensajeRegistro() {
        return mensajeRegistro;
    }

    public static void setMensajeRegistro(String mensajeRegistro) {
        RegistroActivity.mensajeRegistro = mensajeRegistro;
    }

    public RegistroAWS getRegistroAWS() {
        return registroAWS;
    }

    public void setRegistroAWS(RegistroAWS registroAWS) {
        this.registroAWS = registroAWS;
    }
}
