package co.edu.uniquindio.proyectoestructura.listasEnlazadas.proceso;

import co.edu.uniquindio.proyectoestructura.modelo.Proceso;

public class ListaEnlazadaProceso  {
    private NodoProceso cabeza;

    // Constructor
    public ListaEnlazadaProceso() {
        this.cabeza = null;
    }

    // Método para agregar un proceso
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

    // Método para eliminar un proceso por ID
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

    // Método para mostrar todos los procesos
    public void mostrarProcesos() {
        if (cabeza == null) {
            System.out.println("No hay procesos en la lista.");
            return;
        }

        NodoProceso temp = cabeza;
        while (temp != null) {
            System.out.println(temp.getProceso());
            temp = temp.getSiguiente();
        }
    }

    // Método para obtener la cabeza de la lista (opcional)
    public NodoProceso getCabeza() {
        return cabeza;
    }
}
