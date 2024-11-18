package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.Usuarios;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.uniquindio.proyectoestructura.modelo.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodoTest {

    @Test
    void testConstructorYGetters() {
        // Crear un usuario de prueba
        Usuario usuario = new Usuario("Juan", "Perez", "juan.perez@example.com", "1234");

        // Crear un nodo con el usuario
        Nodo nodo = new Nodo(usuario);

        // Verificar que el nodo contiene el usuario correcto
        assertEquals(usuario, nodo.getUsuario(), "El usuario del nodo no coincide con el esperado.");

        // Verificar que el siguiente nodo es inicialmente null
        assertNull(nodo.getSiguiente(), "El siguiente nodo debería ser null por defecto.");
    }

    @Test
    void testSetUsuario() {
        // Crear un usuario inicial y un usuario nuevo
        Usuario usuarioInicial = new Usuario("Maria", "Lopez", "maria.lopez@example.com", "abcd");
        Usuario usuarioNuevo = new Usuario("Carlos", "Gomez", "carlos.gomez@example.com", "5678");

        // Crear un nodo con el usuario inicial
        Nodo nodo = new Nodo(usuarioInicial);

        // Cambiar el usuario del nodo
        nodo.setUsuario(usuarioNuevo);

        // Verificar que el usuario del nodo se actualizó correctamente
        assertEquals(usuarioNuevo, nodo.getUsuario(), "El usuario del nodo no se actualizó correctamente.");
    }

    @Test
    void testSetSiguiente() {
        // Crear dos nodos
        Usuario usuario1 = new Usuario("Pedro", "Martinez", "pedro.martinez@example.com", "0000");
        Usuario usuario2 = new Usuario("Ana", "Ramirez", "ana.ramirez@example.com", "1111");

        Nodo nodo1 = new Nodo(usuario1);
        Nodo nodo2 = new Nodo(usuario2);

        // Enlazar nodo1 con nodo2
        nodo1.setSiguiente(nodo2);

        // Verificar que el siguiente nodo de nodo1 es nodo2
        assertEquals(nodo2, nodo1.getSiguiente(), "El siguiente nodo no es el esperado.");
    }
}