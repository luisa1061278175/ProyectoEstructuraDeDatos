package co.edu.uniquindio.proyectoestructura.estructurasPropias.cola.tarea;

import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtilTareas;

import java.util.LinkedList;
import java.util.Queue;

public class ColaTarea {
    private Queue<Tarea> tareas;

    public ColaTarea() {
        tareas = new LinkedList<>();
        cargarTareasDesdeArchivo();
    }

    public void guardarTarea(Tarea tarea) {
        tareas.offer(tarea);
        System.out.println("Tarea guardada: " + tarea);
    }

    public boolean eliminarTarea(String nombre) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equals(nombre)) {
                tareas.remove(tarea);
                System.out.println("Tarea eliminada: " + tarea);
                return true;
            }
        }
        System.out.println("No se encontró la tarea con nombre: " + nombre);
        return false;
    }

    public Queue<Tarea> getTareas() {
        return tareas;
    }

    public boolean modificarTarea(String nombre, String nuevaDescripcion, boolean esObligatoria, int duracion) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equals(nombre)) {
                tarea.setDescripcion(nuevaDescripcion);
                tarea.setObligatoria(esObligatoria);
                tarea.setDuracion(duracion);
                System.out.println("Tarea modificada: " + tarea);
                return true;
            }
        }
        System.out.println("No se encontró la tarea con nombre: " + nombre);
        return false;
    }

    public Tarea buscarTareaPorNombre(String nombreActividad) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equalsIgnoreCase(nombreActividad)) {
                return tarea;
            }
        }
        return null;
    }

    public void cargarTareasDesdeArchivo() {
        Tarea[] tareasCargadas = ArchivoUtilTareas.cargarTareasDesdeArchivo(ArchivoUtilTareas.RUTA_ARCHIVO_TAREAS);
        for (Tarea tarea : tareasCargadas) {
            guardarTarea(tarea);
        }
    }
}