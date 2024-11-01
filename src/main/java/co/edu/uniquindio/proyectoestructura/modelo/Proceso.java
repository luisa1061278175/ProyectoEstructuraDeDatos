package co.edu.uniquindio.proyectoestructura.modelo;

import co.edu.uniquindio.proyectoestructura.listasEnlazadas.actividad.ListaEnlazadaActividades;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;

public class Proceso {
    private String nombre;
    private String id;
    private ListaEnlazadaActividades listaActividades;

    public Proceso(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.listaActividades = new ListaEnlazadaActividades();
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

    @Override
    public String toString() {
        return nombre + ";" + id +";" ;
    }


    public ListaEnlazadaActividades getListaActividades() {
        return listaActividades;
    }

    public void agregarActividad(Actividad actividad) {
        listaActividades.agregarActividad(actividad);
    }

    public void mostrarActividades() {
        listaActividades.mostrarActividades();
    }
}
