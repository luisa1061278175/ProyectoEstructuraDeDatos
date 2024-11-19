package co.edu.uniquindio.proyectoestructura.estructurasPropias.colas.actividad;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtilActividades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    public boolean intercambiarActividades(Queue<Actividad> actividades, int indice1, int indice2) {
        if (indice1 == indice2) {
            System.out.println("Los índices son iguales, no hay nada que intercambiar.");
            return false;
        }
        if (indice1 < 0 || indice2 < 0 || indice1 >= actividades.size() || indice2 >= actividades.size()) {
            System.out.println("Índices fuera de rango.");
            return false;
        }
        List<Actividad> listaActividades = new ArrayList<>(actividades);
        Actividad temp = listaActividades.get(indice1);
        listaActividades.set(indice1, listaActividades.get(indice2));
        listaActividades.set(indice2, temp);
        actividades.clear();
        actividades.addAll(listaActividades);
        System.out.println("Actividades intercamabiads con éxito.");
        return true;
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