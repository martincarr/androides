package com.example.mcarrillom.arf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static android.os.SystemClock.sleep;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogout;
    private Button btnAcceso;
    private EditText correoUsr;
    private EditText passUsr;
    private ServicioAWS servicioAWS;
    private String url_aws;
    private static int codigo_aws;
    private static String usuario_aws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnAcceso = findViewById(R.id.btn_acceso_login);
        btnLogout = findViewById(R.id.btn_salir_login);
        correoUsr = findViewById(R.id.correo_usr_login);
        passUsr = findViewById(R.id.pwd_usr_login);
        this.setCodigo_aws(-1);
        this.setUsuario_aws("");

        btnAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumirServicioAWS();
                if (LoginActivity.getCodigo_aws() == 1004){
                    iniciarSessionAWS();
                    imprimirMensaje("Bienvenido "+LoginActivity.getUsuario_aws()+" !");
                }
                else{
                    imprimirMensaje("Usuario no encontrado, verifique sus datos!");
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void imprimirMensaje(String mensaje){
        Context context = getApplicationContext();
        CharSequence text = mensaje;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void consumirServicioAWS(){
        this.setUrl_aws("https://0kg5bbzwbc.execute-api.us-west-2.amazonaws.com/dev/arf-login");
        this.setServicioAWS(new ServicioAWS(this, this.getUrl_aws(),correoUsr.getText().toString(),passUsr.getText().toString()));
        this.getServicioAWS().execute();
        sleep(4000);
    }

    public void iniciarSessionAWS(){
            Intent intent = new Intent(this,SessionActivity.class);
            startActivity(intent);
    }

    public ServicioAWS getServicioAWS() {
        return servicioAWS;
    }

    public void setServicioAWS(ServicioAWS servicioAWS) {
        this.servicioAWS = servicioAWS;
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
        LoginActivity.codigo_aws = codigo_aws;
    }

    public static String getUsuario_aws() {
        return usuario_aws;
    }

    public static void setUsuario_aws(String usuario_aws ) {
        LoginActivity.usuario_aws = usuario_aws;
    }
}
