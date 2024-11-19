package co.edu.uniquindio.proyectoestructura.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Proceso {
    private String nombre;
    private String id;
    private Queue<Actividad> listaActividades;

    public Proceso(String nombre, String id, Queue<Actividad> listaActividades) {
        this.nombre = nombre;
        this.id = id;
        this.listaActividades = listaActividades;
    }

    public Proceso(String nombre, String id, List<String> listaActividades) {
        this.nombre = nombre;
        this.id = id;

        this.listaActividades = new LinkedList<>();
    }

    public Proceso(String nombre, String id){
        this.nombre = nombre;
        this.id = id;
    }

    // Getters y setters
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

    public Queue<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(Queue<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    @Override
    public String toString() {
        return "Proceso{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", listaActividades=" + listaActividades +
                '}';
    }

    // Método para agregar una actividad
    public void agregarActividad(Actividad actividad) {
        listaActividades.add(actividad);
        System.out.println("Actividad agregada: " + actividad.getNombre());
    }
}
