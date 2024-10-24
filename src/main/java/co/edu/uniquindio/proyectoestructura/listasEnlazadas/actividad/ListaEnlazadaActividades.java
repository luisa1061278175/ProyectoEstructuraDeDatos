package co.edu.uniquindio.proyectoestructura.listasEnlazadas.actividad;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;

public class ListaEnlazadaDeActividades {
    private NodoActividad cabeza;

    // Constructor
    public ListaEnlazadaDeActividades() {
        this.cabeza = null;
    }

    // Método para agregar una actividad
    public void agregarActividad(Actividad actividad) {
        NodoActividad nuevoNodo = new NodoActividad(actividad);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoActividad temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
    }

    // Método para eliminar una actividad por nombre
    public void eliminarActividad(String nombre) {
        if (cabeza == null) return;

        if (cabeza.getActividad().getNombre().equals(nombre)) {
            cabeza = cabeza.getSiguiente();
            return;
        }

        NodoActividad actual = cabeza;
        NodoActividad anterior = null;

        while (actual != null && !actual.getActividad().getNombre().equals(nombre)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual != null) {
            anterior.setSiguiente(actual.getSiguiente());
        }
    }

    // Método para mostrar todas las actividades
    public void mostrarActividades() {
        if (cabeza == null) {
            System.out.println("No hay actividades en la lista.");
            return;
        }

        NodoActividad temp = cabeza;
        while (temp != null) {
            System.out.println(temp.getActividad());
            temp = temp.getSiguiente();
        }
    }
}

