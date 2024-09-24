package co.edu.uniquindio.proyectoestructura.modelo;

import java.util.List;

public class Proceso {

    private String nombre;
    private String id;
    private List<Actividad> listaActividades;

    public Proceso(String nombre, String id, List<Actividad> listaActividades) {
        this.nombre = nombre;
        this.id = id;
        this.listaActividades = listaActividades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }
}
