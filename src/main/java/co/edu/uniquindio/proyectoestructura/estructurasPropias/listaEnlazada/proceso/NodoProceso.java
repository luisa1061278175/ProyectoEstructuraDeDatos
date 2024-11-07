package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso;

import co.edu.uniquindio.proyectoestructura.modelo.Proceso;

public class NodoProceso {
    private Proceso proceso;
    private NodoProceso siguiente;

    // Constructor
    public NodoProceso(Proceso proceso) {
        this.proceso = proceso;
        this.siguiente = null;
    }

    // Getters y setters
    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public NodoProceso getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoProceso siguiente) {
        this.siguiente = siguiente;
    }
}

