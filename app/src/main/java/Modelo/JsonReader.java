package Modelo;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by CTN Developer on 19-07-2016.
 */
public class JsonReader {

    private String url;

    private JSONArray jsonArray;

    public JsonReader(String url) {
        this.url = url;
    }

    public void regresa_usuarios(){
        ArrayList<Usuario> lista_usuarios = new ArrayList<Usuario>();





    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
