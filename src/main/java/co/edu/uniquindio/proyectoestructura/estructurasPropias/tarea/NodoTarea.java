package co.edu.uniquindio.proyectoestructura.estructurasPropias.tarea;


import co.edu.uniquindio.proyectoestructura.modelo.Tarea;

public class NodoTarea {
    private Tarea tarea;
    private NodoTarea siguiente;

    // Constructor
    public NodoTarea(Tarea tarea) {
        this.tarea = tarea;
        this.siguiente = null;
    }

    // Getters y setters
    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public NodoTarea getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoTarea siguiente) {
        this.siguiente = siguiente;
    }
}

