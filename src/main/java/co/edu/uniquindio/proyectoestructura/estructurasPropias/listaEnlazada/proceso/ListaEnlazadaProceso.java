package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;

public class ListaEnlazadaProceso {
    private NodoProceso cabeza;

    public ListaEnlazadaProceso() {
        this.cabeza = null;
    }

    // Métodos CRUD para la lista enlazada
    public void agregarProceso(Proceso proceso) {
        NodoProceso nuevoNodo = new NodoProceso(proceso);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoProceso temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
    }

    public void eliminarProceso(String id) {
        if (cabeza == null) return;

        if (cabeza.getProceso().getId().equals(id)) {
            cabeza = cabeza.getSiguiente();
            return;
        }

        NodoProceso actual = cabeza;
        NodoProceso anterior = null;

        while (actual != null && !actual.getProceso().getId().equals(id)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual != null) {
            anterior.setSiguiente(actual.getSiguiente());
        }
    }

    public boolean modificarProceso(String id, String nuevoNombre) {
        NodoProceso actual = cabeza;
        while (actual != null) {
            if (actual.getProceso().getId().equals(id)) {
                actual.getProceso().setNombre(nuevoNombre);
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public Proceso buscarProceso(String id) {
        NodoProceso actual = cabeza;
        while (actual != null) {
            if (actual.getProceso().getId().equals(id)) {
                return actual.getProceso();
            }
            actual = actual.getSiguiente();
        }
        System.out.println("Proceso no encontrado con ID: " + id);
        return null;
    }

    public void agregarActividadAProceso(String idProceso, Actividad actividad) {
        Proceso proceso = buscarProceso(idProceso);
        if (proceso != null) {
            proceso.agregarActividad(actividad);
        } else {
            System.out.println("Proceso no encontrado para agregar la actividad.");
        }
    }

    public NodoProceso getCabeza() {
        return cabeza;
    }
}
