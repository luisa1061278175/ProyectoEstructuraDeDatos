package co.edu.uniquindio.proyectoestructura.controller;

import co.edu.uniquindio.proyectoestructura.estructurasPropias.cola.tarea.ColaTarea;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtilTareas;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AdminTareaController {

    ColaTarea colaTarea = new ColaTarea();
    ArchivoUtilTareas archivoUtilTareas= new ArchivoUtilTareas();

    /*
    *
    * MÉTODOS PROPIOS DE LA COLA
    *
    *
    * */
    public void guardar(Tarea tarea) {
        colaTarea.guardarTarea(tarea);
    }

    public void eliminar(String nombre) {
        colaTarea.eliminarTarea(nombre);
    }

    public void modificar(String nombre, String descripcion, boolean obligatorio, int duracion) {
        colaTarea.modificarTarea(nombre, descripcion, obligatorio, duracion);
    }

    /*
     *
     * MÉTODOS al txt
     *
     *
     * */

    public void agregarTxt(List<Tarea> tareas, String archivo) {
        archivoUtilTareas.guardarListaEnArchivo(tareas, archivo);

    }

    public void modificarTxt(String nombre, String nuevaDescripcion, boolean esObligatoria, int duracion) {
        archivoUtilTareas.modificarTareasEnArchivo(nombre, nuevaDescripcion, esObligatoria, duracion);
    }

    public void eliminarTxt(String identificador) {
        archivoUtilTareas.eliminarLineaDelArchivo(identificador);
    }

    public Actividad[] colaAArreglo(Queue<Actividad> cola) {
        return archivoUtilTareas.colaAArreglo(cola);
    }

    public Tarea buscarTarea(String nombre) {
        return colaTarea.buscarTareaPorNombre(nombre);
    }

    public void cargarTareasDesdeArchivo( ) {
         colaTarea.cargarTareasDesdeArchivo();
    }
    public Tarea[]cargarTareaArchivo(String ruta){
        return archivoUtilTareas.cargarTareasArchivo(ruta);
    }

    public List<Tarea> generarTareasDesdeNombres(String nombresTareas) {
        List<Tarea> listaTareas = new ArrayList<>();
        String[] nombres = nombresTareas.split(",");
        for (String nombre : nombres) {
            Tarea tarea = this.buscarTarea(nombre.trim());
            if (tarea != null) {
                listaTareas.add(tarea);
            } else {
                System.out.println("Tarea no encontrada: " + nombre.trim());
            }
        }
        return listaTareas;
    }


    public void guardarTareaEnActividad(String nombreActividad, Tarea nuevaTarea){
        archivoUtilTareas.guardarTareaEnActividad(nombreActividad, nuevaTarea);
}
}
