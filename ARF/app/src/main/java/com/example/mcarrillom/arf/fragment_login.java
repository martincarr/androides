package com.example.mcarrillom.arf;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class fragment_login extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    RequestQueue requestQueueARF;
    JsonRequest jsonRequestARF;
    EditText usrLoginARF, pwdLoginARF;
    Button btnEnviarARF, btnRegARF;

    public fragment_login() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista_arf = inflater.inflate(R.layout.fragment_login, container, false);
        usrLoginARF = vista_arf.findViewById(R.id.usrText);
        pwdLoginARF = vista_arf.findViewById(R.id.pwdText);
        btnEnviarARF = vista_arf.findViewById(R.id.btnAcceso);
        requestQueueARF = Volley.newRequestQueue(getContext());
        /*btnEnviarARF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearJsonObjReq();
            }
        });*/
        //TextView textView= vista_arf.findViewById(R.id.usrText);
        //textView.setText("hola prro");
        return vista_arf;
    }

        //Implementacion de funciones con JSON
        @Override
        public void onErrorResponse (VolleyError error){
            Toast.makeText(getContext(), "Usuario no encontrado" + error.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse (JSONObject response){
            Usuario usr = new Usuario();
            Toast.makeText(getContext(), "Usuario válido" + usrLoginARF.getText().toString(), Toast.LENGTH_SHORT).show();
            JSONArray jsonArray = response.optJSONArray("datos");
            JSONObject jsonObject = null;

            try {
                jsonObject = jsonArray.getJSONObject(0);
                usr.setNombre(jsonObject.optString("nombre"));
                usr.setCorreo(jsonObject.optString("correo"));
                usr.setPassword(jsonObject.optString("password"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        private void crearJsonObjReq () {
            String url = "https://0kg5bbzwbc.execute-api.us-west-2.amazonaws.com/dev/arf-login";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", String.valueOf(error));
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> postParam = new HashMap<>();
                    postParam.put("username", "gerardo@paquete.mx");
                    postParam.put("password", "root");
                    return postParam;
                }
            };
            requestQueueARF.add(postRequest);
        }
}
