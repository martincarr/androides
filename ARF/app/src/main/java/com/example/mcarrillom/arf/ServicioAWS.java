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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;

public class ServicioAWS extends AsyncTask<Void,Void,String>  {

    //variables thread
    private Context http;
    //ProgressDialog pDialog;
    public String resultado_api = "";
    public String respuesta_api = "";

    //constructor asyntask
    public ServicioAWS (Context context,String request_api){
        Toast.makeText(context,request_api,Toast.LENGTH_LONG).show(); //imprimimos request
        this.http = context;
        this.respuesta_api = request_api;
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
        String URLendPont= respuesta_api;
        String resultado="";
        //Void data = params[1]; //data to post
        OutputStream out = null;

        try {
            URL url = new URL(URLendPont);
            JSONObject paramsPost = new JSONObject();
            paramsPost.put("username", "martin@arf.mx");
            paramsPost.put("password", "admin");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setReadTimeout(3000);
            urlConnection.setConnectTimeout(3000);
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
                System.out.println("Conexion ARF exitosa con API GATEWAY! Código: "+codigoResp);
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String renglon = "";
                while ((renglon = in.readLine()) != null) {
                    sb.append(renglon);
                    System.out.println(sb.append(renglon).toString());
                    break;
                }
                in.close();
                resultado = sb.toString();
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
        /*String resultado="";
        String URLendPont= respuesta_api;
        URL url=null;
        try{
            url = new URL(URLendPont); //conexion con api gateway
            HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
            JSONObject paramsPost = new JSONObject();
            paramsPost.put("username","martin@arf.mx");
            paramsPost.put("password","admin");
            //inicializa conexion
            urlConnection.setReadTimeout(3000);
            urlConnection.setConnectTimeout(3000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            //req
            OutputStream outs =urlConnection.getOutputStream();
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(outs,"UTF-8"));
            wr.write(getPost(paramsPost));
            wr.flush();
            wr.close();
            outs.close();*/
            //edo conexion
            //*****
            /*if(codigoResp==HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer sb= new StringBuffer();
                String renglon="";
                while((renglon=in.readLine())!=null){
                    sb.append(renglon);
                    break;
                }
                in.close();
                resultado=sb.toString();
            }else{
                resultado=new String("Error :"+codigoResp);
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resultado);
        return resultado;*/
    }


    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        //pDialog = ProgressDialog.show(http,"Procesando Solicitud","Procesando...");
    }

    //aws request
    @Override
    protected  void onPostExecute(String cad){
        super.onPostExecute(cad);
        //pDialog.dismiss();
        resultado_api=cad;
        Toast.makeText(http,resultado_api,Toast.LENGTH_LONG).show(); //imprimimos request
    }


}
