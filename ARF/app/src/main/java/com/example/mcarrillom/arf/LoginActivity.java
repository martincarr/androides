package com.example.mcarrillom.arf;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogout;
    private Button btnAcceso;
    private EditText correoUsr;
    private EditText passUsr;
    private boolean statusLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnAcceso = findViewById(R.id.btn_acceso_login);
        btnLogout = findViewById(R.id.btn_salir_login);
        correoUsr = findViewById(R.id.correo_usr_login);
        passUsr = findViewById(R.id.pwd_usr_login);

        btnAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumirServicioAWS();
                /*statusLogin = enviarDatos(correoUsr.getText().toString(),passUsr.getText().toString());
                if(statusLogin){
                    iniciarSession(correoUsr.getText().toString());
                }else
                    imprimirMensaje("Error, intente nuevamente!");*/
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public boolean enviarDatos( String usr, String pwd ) {

        //Crear Json


        //String mensaje = new String("procesando : " + usr + " : " + pwd);
        //imprimirMensaje(mensaje);
        if(new String("gerardo@paquete.mx").equals(usr) && new String("root").equals(pwd))
            return true;
        else
            return false;
    }

    public void imprimirMensaje(String mensaje){
        Context context = getApplicationContext();
        CharSequence text = mensaje;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void iniciarSession(String nombreUsr){
        Intent intent = new Intent(this,SessionActivity.class);
        startActivity(intent);
    }

    public void consumirServicioAWS(){
        String url = "https://0kg5bbzwbc.execute-api.us-west-2.amazonaws.com/dev/arf-login";
        ServicioAWS servicioAWS = new ServicioAWS(this,url);
        servicioAWS.execute();
        //iniciarSession(correoUsr.getText().toString());
    }

}
