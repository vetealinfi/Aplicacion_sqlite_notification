package com.codebake.com.consumejson;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Modelo.Usuario;
import util.AndroidUtils;

public class LeeDbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lee_db);

        //AndroidUtils.enviarNotificacion(this,"guardo las cosas",R.id.nav_camera,MainActivity.class);

        DataBaseHelper db = new DataBaseHelper(this);

        //Usuario usuarioNuevo = new Usuario("Jorge", "Partal");
        //db.addUsuario(usuarioNuevo);
        //Usuario usuarioNuevo2 = new Usuario("Jorge 2", "Partal");
        //db.addUsuario(usuarioNuevo2);
        //Usuario usuarioNuevo3 = new Usuario("Jorge 2", "Partal");
        //db.addUsuario(usuarioNuevo3);


        List<Usuario> usuariosList = new ArrayList<>();
        usuariosList = db.getAllUsuarios();

        ListView listaUsuarios = (ListView) findViewById(R.id.listaUsuariosDb);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);



        for(Usuario usuario: usuariosList){
            adapter.add(usuario.getId()+" "+usuario.getNombre()+" "+usuario.getApellido());
        }


        listaUsuarios.setAdapter(adapter);





    }
}
