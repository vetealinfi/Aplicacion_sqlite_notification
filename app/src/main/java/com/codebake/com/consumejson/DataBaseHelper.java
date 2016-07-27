package com.codebake.com.consumejson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Modelo.Usuario;

/**
 * Created by CTN Developer on 21-07-2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private final static  int VERSION_BD = 1;
    private final static  String NOMBRE_BASE = "BASE02";
    private final static  String TABLA_USUARIOS = "usuarios";

    private final static  String CAMPO_PK = "id";
    private final static  String CAMPO_NOMBRE = "nombre";
    private final static  String CAMPO_APELLIDO = "apellido";

    public DataBaseHelper(Context context) {
        super(context, NOMBRE_BASE, null, VERSION_BD);
    }



    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        //Log.d("Base Creada","entro al on open");
        System.out.println("Entro al on open");
    }


    /*
        * Aca se ponen las estructuras iniciales
        * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //PATH /data/data/com/lopez/myaplication/BASE01.db
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE "+TABLA_USUARIOS);
        sb.append("("+CAMPO_PK+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_NOMBRE+" TEXT, "+CAMPO_APELLIDO+" TEXT)");

        sqLiteDatabase.execSQL(sb.toString());


        System.out.println("Entro al on create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("Entro al on upgrade");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }


    public void addUsuario(Usuario usuarioNuevo){
        SQLiteDatabase db = getWritableDatabase();

        if(db == null){
            onCreate(db);
            db = this.getWritableDatabase();
        }

        ContentValues values = new ContentValues();

        values.put(CAMPO_NOMBRE,usuarioNuevo.getNombre());
        values.put(CAMPO_APELLIDO,usuarioNuevo.getApellido());

        db.insert(TABLA_USUARIOS,null,values);
        db.close();
    }

    public List<Usuario> getAllUsuarios( ){
        List<Usuario> usuariosList = new ArrayList<>();
        String sql = "SELECT "+CAMPO_PK+","+CAMPO_NOMBRE+","+CAMPO_APELLIDO+" FROM "+TABLA_USUARIOS;

        SQLiteDatabase db = getWritableDatabase();

        if(db == null){
            onCreate(db);
            db = this.getWritableDatabase();
        }


        Cursor cursor = db.rawQuery(sql,null);

        if(cursor!=null && cursor.moveToFirst()){
            do{
                usuariosList.add(new Usuario(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while(cursor.moveToNext());

        }

        db.close();
        return usuariosList;

    }
}
