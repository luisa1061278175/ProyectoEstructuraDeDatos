package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.Usuarios;

import co.edu.uniquindio.proyectoestructura.modelo.Usuario;

public class Nodo {
    private Usuario usuario;
    private Nodo siguiente;

    public Nodo(Usuario usuario) {
        this.usuario = usuario;
        this.siguiente = siguiente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
