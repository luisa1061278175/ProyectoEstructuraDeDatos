package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import org.junit.jupiter.api.*;
import java.util.Queue;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class ListaEnlazadaProcesoTest {

    private ListaEnlazadaProceso lista;

    @BeforeEach
    void setUp() {
        lista = new ListaEnlazadaProceso();
    }

    @Test
    void testAgregarProceso() {
        Proceso proceso = new Proceso("P001", "Corte", new LinkedList<>());
        lista.agregarProceso(proceso);
        Proceso resultado = lista.buscarProceso("P001");

        assertNotNull(resultado, "El proceso debería estar en la lista.");
        assertEquals("Corte", resultado.getNombre(), "El nombre del proceso no coincide.");
    }

    @Test
    void testEliminarProceso() {
        Proceso proceso = new Proceso("P001", "Corte", new LinkedList<>());
        lista.agregarProceso(proceso);

        lista.eliminarProceso("P001");
        Proceso resultado = lista.buscarProceso("P001");

        assertNull(resultado, "El proceso debería haber sido eliminado.");
    }

    @Test
    void testModificarProceso() {
        Proceso proceso = new Proceso("P001", "Corte", new LinkedList<>());
        lista.agregarProceso(proceso);

        boolean modificado = lista.modificarProceso("P001", "Ensamble");
        Proceso resultado = lista.buscarProceso("P001");

        assertTrue(modificado, "La modificación debería haber sido exitosa.");
        assertNotNull(resultado, "El proceso modificado no debería ser null.");
        assertEquals("Ensamble", resultado.getNombre(), "El nombre del proceso no fue actualizado correctamente.");
    }

    @Test
    void testCargarDesdeArchivo() {
        lista.cargarDesdeArchivo("src/main/resources/archivosTxt/Procesos.txt");
        assertNotNull(lista.getCabeza(), "La lista no debería estar vacía después de cargar desde el archivo.");
    }

    @Test
    void testGuardarActividadEnProcesoTxt() {
        Proceso proceso = new Proceso("P002", "Pulido", new LinkedList<>());
        lista.agregarProceso(proceso);

        Actividad actividad = new Actividad("A001", "Lijar superficie", true,null);
        lista.guardarActividadEnProcesoTxt("P002", actividad);

        Proceso resultado = lista.buscarProceso("P002");
        assertNotNull(resultado, "El proceso no debería ser null.");
        assertEquals("Pulido", resultado.getNombre(), "El proceso no coincide.");
    }

    @Test
    void testMostrarProcesos() {
        lista.agregarProceso(new Proceso("P001", "Corte", new LinkedList<>()));
        lista.agregarProceso(new Proceso("P002", "Ensamble", new LinkedList<>()));

        // Redirigir salida estándar si necesitas verificar la impresión
        lista.mostrarProcesos();
    }
}
