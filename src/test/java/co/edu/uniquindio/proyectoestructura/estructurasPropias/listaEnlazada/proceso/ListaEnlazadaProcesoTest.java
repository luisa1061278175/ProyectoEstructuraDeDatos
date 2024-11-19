package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class ListaEnlazadaProcesoTest {
    private ListaEnlazadaProceso lista;

    @BeforeEach
    public void setUp() {
        // Inicializa la lista antes de cada prueba
        lista = new ListaEnlazadaProceso();
    }

    @Test
    public void testAgregarProceso() {

        Proceso proceso = new Proceso("1", "Proceso 1", (Queue<Actividad>) null);
        lista.agregarProceso(proceso);

        Proceso procesoEncontrado = lista.buscarProceso("1");
        assertNotNull(procesoEncontrado, "El proceso debería existir.");
        assertEquals("Proceso 1", procesoEncontrado.getNombre(), "El nombre del proceso debería ser 'Proceso 1'.");
    }

    @Test
    public void testEliminarProceso() {
        Proceso proceso = new Proceso("1", "Proceso 1", (Queue<Actividad>) null);
        lista.agregarProceso(proceso);

        lista.eliminarProceso("1");

        Proceso procesoEliminado = lista.buscarProceso("1");
        assertNull(procesoEliminado, "El proceso debería haber sido eliminado.");
    }

    @Test
    public void testModificarProceso() {

        Proceso proceso = new Proceso("1", "Proceso 1", (Queue<Actividad>) null);
        lista.agregarProceso(proceso);

        boolean modificado = lista.modificarProceso("1", "Nuevo Proceso");

        assertTrue(modificado, "El proceso debería haber sido modificado.");
        Proceso procesoModificado = lista.buscarProceso("1");
        assertEquals("Nuevo Proceso", procesoModificado.getNombre(), "El nombre del proceso debería ser 'Nuevo Proceso'.");
    }

    @Test
    public void testAgregarActividadAProceso() {

        Proceso proceso = new Proceso("1", "Proceso 1", (Queue<Actividad>) null);
        lista.agregarProceso(proceso);

        // Crear una actividad y agregarla al proceso
        Actividad actividad = new Actividad("1", "Actividad 1",true,null);
        lista.agregarActividadAProceso("1", actividad);

        // Verificar que la actividad ha sido agregada
        Proceso procesoConActividad = lista.buscarProceso("1");
        assertNotNull(procesoConActividad, "El proceso debería existir.");
        assertEquals(1, procesoConActividad.getListaActividades().size(), "El proceso debería tener una actividad.");
        assertEquals("Actividad 1", procesoConActividad.getListaActividades().peek().getNombre(), "La actividad debería llamarse 'Actividad 1'.");

    }

    @Test
    public void testBuscarProcesoNoExistente() {

        Proceso proceso = lista.buscarProceso("999");
        assertNull(proceso, "El proceso no debería existir.");
    }
}