package com.example.mcarrillom.arf;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;

public class ServicioAWS extends AsyncTask<Void,Void,String>  {

    private Context http;
    private String respuesta_aws = "";
    private String url_aws = "";
    private String usuario = "";
    private String password = "";
    private int codigo_aws;
    private JSONObject json_aws;

    //constructor asyntask
    public ServicioAWS (Context context,String request_api, String usuario, String password){
        this.setHttp(context);
        this.setUrl_aws(request_api);
        this.setUsuario(usuario);
        this.setPassword(password);
        this.setCodigo_aws(0);
        this.setRespuesta_aws("");
    }

    //json->string
    public String getPost(JSONObject params)throws Exception{
        StringBuilder resultado = new StringBuilder();
        boolean first=true;
        Iterator<String> bandera =params.keys();
        while(bandera.hasNext()){
            String index=bandera.next();
            Object value=params.get(index);
            if(first)first=false;
            else resultado .append("&");
            resultado.append(URLEncoder.encode(index,"UTF-8"));
            resultado.append("=");
            resultado.append(URLEncoder.encode(value.toString(),"UTF-8"));
        }
        System.out.println(resultado.toString());
        return resultado.toString();
    }

    @Override
    protected String doInBackground(Void... params) {
        String URLendPont= this.getUrl_aws();
        String resultado="";
        OutputStream out = null;

        try {
            URL url = new URL(URLendPont);
            JSONObject paramsPost = new JSONObject();
            System.out.println("post :"+ this.getUsuario() +" "+ this.getPassword());
            paramsPost.put("username", this.getUsuario());
            paramsPost.put("password", this.getPassword());
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(String.valueOf(paramsPost));
            writer.flush();
            writer.close();
            out.close();
            int codigoResp = urlConnection.getResponseCode();
            urlConnection.connect();
            if (codigoResp == HttpsURLConnection.HTTP_OK) {
                //System.out.println("Conexion ARF exitosa con API GATEWAY! Código: "+codigoResp+"OK");
                //this.setCodigo_aws(codigoResp);
                //System.out.println(this.getCodigo_aws());

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String renglon = "";
                while ((renglon = in.readLine()) != null) {
                    sb.append(renglon);
                    this.json_aws = new JSONObject(sb.toString());
                    int code = this.json_aws.getInt("code");
                    LoginActivity.setCodigo_aws(code);
                    this.setCodigo_aws(code);
                    //System.out.println("CODE: "+this.getCodigo_aws());
                    JSONObject user_aws = this.json_aws.getJSONObject("user");
                    String user_aws_name = user_aws.getString("name");
                    LoginActivity.setUsurario_aws(user_aws_name);
                    this.setUsuario(user_aws_name);
                    //System.out.println(" USER_NAME: "+user_aws_name);
                    //System.out.println(sb.append(renglon).toString());
                    break;
                }
                in.close();
                resultado = sb.toString();
                return resultado;
            } else
                resultado = new String("Error :" + codigoResp);

        }catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return resultado;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    //aws request
    @Override
    protected  void onPostExecute(String cad){
        super.onPostExecute(cad);
        this.setRespuesta_aws(cad);
        //System.out.println("onPOST: "+this.getRespuesta_aws()+"CODE: "+this.getCodigo_aws());
        Toast.makeText(getHttp(), this.getRespuesta_aws(),Toast.LENGTH_LONG).show(); //imprimimos request
    }

    public Context getHttp() {
        return http;
    }

    public void setHttp(Context http) {
        this.http = http;
    }

    public String getRespuesta_aws() {
        return respuesta_aws;
    }

    public void setRespuesta_aws(String respuesta_aws) {
        this.respuesta_aws = respuesta_aws;
    }

    public String getUrl_aws() {
        return url_aws;
    }

    public void setUrl_aws(String url_aws) {
        this.url_aws = url_aws;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCodigo_aws() {
        return codigo_aws;
    }

    public void setCodigo_aws(int codigo_aws) {
        this.codigo_aws = codigo_aws;
    }
}
