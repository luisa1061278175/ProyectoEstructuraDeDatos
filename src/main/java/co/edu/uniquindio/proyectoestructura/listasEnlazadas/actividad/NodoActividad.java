package co.edu.uniquindio.proyectoestructura.listasEnlazadas.actividad;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;

public class NodoActividad {
    private Actividad actividad;
    private NodoActividad siguiente;

    // Constructor
    public NodoActividad(Actividad actividad) {
        this.actividad = actividad;
        this.siguiente = null;
    }

    // Getters y setters
    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public NodoActividad getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoActividad siguiente) {
        this.siguiente = siguiente;
    }
}

