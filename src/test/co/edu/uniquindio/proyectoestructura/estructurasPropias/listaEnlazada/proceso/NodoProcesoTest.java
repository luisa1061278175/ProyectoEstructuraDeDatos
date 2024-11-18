package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso;

import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodoProcesoTest {

    @Test
    void testConstructorYGetters() {
        // Crear un objeto Proceso simulado
        Proceso proceso = new Proceso("P001", "Corte", null);

        // Crear un nodo con el proceso
        NodoProceso nodo = new NodoProceso(proceso);

        // Verificar que el nodo tiene el proceso correcto
        assertNotNull(nodo.getProceso(), "El proceso no debería ser null.");
        assertEquals(proceso, nodo.getProceso(), "El proceso no coincide con el asignado en el constructor.");
        assertNull(nodo.getSiguiente(), "El siguiente nodo debería ser null.");
    }

    @Test
    void testSetters() {
        // Crear objetos Proceso simulados
        Proceso proceso1 = new Proceso("P001", "Corte", null);
        Proceso proceso2 = new Proceso("P002", "Ensamble", null);

        // Crear un nodo y establecer el proceso inicial
        NodoProceso nodo = new NodoProceso(proceso1);

        // Cambiar el proceso del nodo
        nodo.setProceso(proceso2);

        // Verificar que el proceso fue actualizado
        assertEquals(proceso2, nodo.getProceso(), "El proceso no fue actualizado correctamente.");

        // Crear un nodo siguiente y asignarlo
        NodoProceso nodoSiguiente = new NodoProceso(proceso1);
        nodo.setSiguiente(nodoSiguiente);

        // Verificar que el siguiente nodo fue asignado correctamente
        assertNotNull(nodo.getSiguiente(), "El siguiente nodo no debería ser null.");
        assertEquals(nodoSiguiente, nodo.getSiguiente(), "El siguiente nodo no coincide con el asignado.");
    }
}
