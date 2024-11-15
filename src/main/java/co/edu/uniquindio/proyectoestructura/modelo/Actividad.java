package co.edu.uniquindio.proyectoestructura.modelo;

import java.util.Queue;

public class Actividad {

    private String nombre;
    private String descripcion;
    private boolean esObligatoria;

    public Queue tareas;

    public Actividad(String nombre, String descripcion, boolean esObligatoria, Queue<Tarea> tareas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatoria = esObligatoria;
        this.tareas = tareas;
    }

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

    public Queue getTareas() {
        return tareas;
    }

    public void setTareas(Queue tareas) {
        this.tareas = tareas;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", esObligatoria=" + esObligatoria +
                '}';
    }
}


