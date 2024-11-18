package co.edu.uniquindio.proyectoestructura.estructurasPropias.colas.actividad;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtilActividades;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static co.edu.uniquindio.proyectoestructura.util.ArchivoUtilActividades.cargarActividadesDesdeArchivo;
import static co.edu.uniquindio.proyectoestructura.util.ArchivoUtilTareas.RUTA_ARCHIVO_ACTIVIDADES;

public class ColaActividad {
    private Queue<Actividad> actividades;
    ArchivoUtilActividades archivoUtilActividades= new ArchivoUtilActividades();
    public ColaActividad() {
        actividades = new LinkedList<>();
    }
    public void agregarActividad(Actividad actividad) {
        actividades.offer(actividad);
        System.out.println("Actividad guardada: " + actividad);
    }

    public Actividad buscarActividadPorNombre(String nombreActividad) {
        System.out.println("listaActividaes: " + actividades);
        archivoUtilActividades.cargarActividadesDesdeArchivo();

        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equalsIgnoreCase(nombreActividad)) {
                return actividad;
            }
        }
        return null;
    }

    public boolean eliminarActividad(String nombre) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombre)) {
                actividades.remove(actividad);
                System.out.println("Actividad eliminada: " + actividad);
                return true;
            }
        }
        System.out.println("No se encontró la actividad con nombre: " + nombre);
        return false;
    }

    public boolean modificarActividad(String nombre, String nuevaDescripcion, boolean esObligatoria) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombre)) {
                actividad.setDescripcion(nuevaDescripcion);
                actividad.setEsObligatoria(esObligatoria);
                System.out.println("Actividad modificada: " + actividad);
                return true;
            }
        }
        System.out.println("No se encontró la actividad con nombre: " + nombre);
        return false;
    }

    public Queue<Actividad> getActividades() {
        return actividades;
    }
}