package co.edu.uniquindio.proyectoestructura.modelo;

import java.util.Queue;

public class Proceso {
    private String nombre;
    private String id;
    private Queue listaActividades;

    public Proceso(String nombre, String id, Queue listaActividades) {
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

    public Queue getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(Queue listaActividades) {
        this.listaActividades = listaActividades;
    }

    @Override
    public String toString() {
        return "Proceso{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void agregarActividad(Actividad actividad) {
        listaActividades.add(actividad);
        System.out.println("Actividad agregada: " + actividad.getNombre());
    }
}