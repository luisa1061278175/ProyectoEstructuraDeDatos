package co.edu.uniquindio.proyectoestructura.modelo;

import java.util.LinkedList;
import java.util.List;

public class Actividad {

    private String nombre;
    private String descripcion;
    private boolean esObligatoria;
    private List<Tarea> listaTareas;
    private Actividad actividadPrecedente;

    // Constructor
    public Actividad(String nombre, String descripcion, boolean esObligatoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatoria = esObligatoria;
        this.listaTareas = new LinkedList<>();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEsObligatoria() {
        return esObligatoria;
    }

    public void setEsObligatoria(boolean esObligatoria) {
        this.esObligatoria = esObligatoria;
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    public Actividad getActividadPrecedente() {
        return actividadPrecedente;
    }

    public void setActividadPrecedente(Actividad actividadPrecedente) {
        this.actividadPrecedente = actividadPrecedente;
    }
}
