package Modelo;

/**
 * Created by CTN Developer on 19-07-2016.
 */
public class Usuario {

    private String nombre;
    private String apellido;
    private Integer id;

    public Usuario(Integer id,String nombre, String apellido) {
        this.nombre=nombre;
        this.apellido=apellido;
        this.id=id;
    }

    public Usuario(String nombre, String apellido) {
        this.nombre=nombre;
        this.apellido=apellido;
        this.id=0;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
