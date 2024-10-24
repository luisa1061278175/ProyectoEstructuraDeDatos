package co.edu.uniquindio.proyectoestructura.modelo;

import co.edu.uniquindio.proyectoestructura.listasEnlazadas.tarea.ListaEnlazadaTarea;

public class Actividad {

    private String nombre;
    private String descripcion;
    private boolean esObligatoria;
    private ListaEnlazadaTarea listaTareas;
    private Actividad actividadPrecedente;

    // Constructor
    public Actividad(String nombre, String descripcion, boolean esObligatoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatoria = esObligatoria;
        this.listaTareas = new ListaEnlazadaTarea(); // Inicialización de la lista enlazada
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

    public ListaEnlazadaTarea getListaTareas() {
        return listaTareas;  // Retorna la lista enlazada de tareas
    }

    public void setListaTareas(ListaEnlazadaTarea listaTareas) {
        this.listaTareas = listaTareas;  // Permite establecer una nueva lista de tareas
    }

    public Actividad getActividadPrecedente() {
        return actividadPrecedente;
    }

    public void setActividadPrecedente(Actividad actividadPrecedente) {
        this.actividadPrecedente = actividadPrecedente;
    }

    // Método para agregar una tarea a la actividad
    public void agregarTarea(Tarea tarea) {
        this.listaTareas.agregarTarea(tarea);  // Agrega tarea a la lista enlazada
    }
}
