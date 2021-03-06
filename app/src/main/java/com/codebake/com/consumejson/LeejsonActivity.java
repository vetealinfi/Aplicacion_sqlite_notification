package com.codebake.com.consumejson;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class LeejsonActivity extends AppCompatActivity {

    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leejson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //INSTANCIAMOS NUESTRO LIST VIEW
        ListView listaUsuarios = (ListView) findViewById(R.id.listaUsuarios);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);


        //Google creo esto para informar de violaciones de politicas relacionadas con los hijos en ejecucion (Thread) y la maquina virtual (Dalvik)
        //Con esto se crea un alerta al violar dicha politica, se crea una traza de la pila de ejecucion (Stack Trace)
        //Siempre cuando accedemos a la red, debemos agregar estas dos lineas ...
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //LLAMAMOS UN JSON DESDE UN URL
        //String url="http://www.jorgepartal.xyz/jsontest/getUser.php";

        //String url = "http://server2.solcloud.cl/academia/colegios.json";
        String url = "http://www.jorgepartal.xyz/test3/colegios.json";
        HttpURLConnection conn = null;
        try{
            URL urlConnection = new URL(url);
            conn = (HttpURLConnection) urlConnection.openConnection();
            conn.setRequestProperty("Request Method", "GET");
            //conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            //conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            Map<String, List<String>> map = conn.getHeaderFields();
            System.out.println("Printing Response Header...\n");

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey()
                        + " ,Value : " + entry.getValue());
            }

            System.out.println("\nGet Response Header By Key ...\n");
            String server = conn.getHeaderField("Server");

            if (server == null) {
                System.out.println("Key 'Server' is not found!");
            } else {
                System.out.println("Server - " + server);
            }

            //Log.i("mensaje Message"," "+conn.getResponseMessage() );
            //Log.i("mensaje getResponseCode"," "+conn.getResponseCode() );
            //Log.i("mensaje url datos"," "+urlConnection.getPath() );
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                BufferedReader buff = new BufferedReader(new InputStreamReader(is));
                //stringbuilder se usa para concatenar objetos grandotes
                StringBuilder sb = new StringBuilder();
                String line = "-";

                while( (line = buff.readLine()) !=null ){
                    sb.append(line).append(" \n");
                }

                String jsonString = sb.toString().trim(); //Quitamos los espacion en blanco

                System.out.println(jsonString);

                JSONObject jsonObj = new JSONObject(sb.toString().trim()); //AHORA INSTANCIAMOS LA CLASE JSON OBJECT
                this.jsonArray = jsonObj.getJSONArray("data"); // OBTENER EL ARREGLO JSON

                //RECORRERMOS EL JSON ARRAY

                for(int i = 0; i<jsonArray.length(); i++){
                    JSONObject o    = jsonArray.getJSONObject(i);
                    Integer id = o.getInt("id");
                    String name   = o.getString("name");
                    String address = o.getString("address");
                    adapter.add(id +" "+name + " " + address );
                }

                //ASOCIAMOS EL LIST VIEW AL ADAPTADOR
                listaUsuarios.setAdapter(adapter);

            }







        }catch(Exception e){
            e.printStackTrace();
        }





        System.out.println("position = ");

    }

}
