package com.codebake.com.consumejson;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

        //String url = "http://server2.solcloud.cl/academia/retorno.json";
        String url = "http://www.jorgepartal.xyz/test3/colegios.json";
        HttpURLConnection conn = null;
        try{
            conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
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
                String name   = o.getString("nombre");
                String address = o.getString("apellido");
                adapter.add(id +" "+name + " " + address );
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        //ASOCIAMOS EL LIST VIEW AL ADAPTADOR
        listaUsuarios.setAdapter(adapter);


        System.out.println("position = ");

    }

}
