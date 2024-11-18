package co.edu.uniquindio.proyectoestructura.estructurasPropias.colas.actividad;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ColaActividadTest {

    private static ColaActividad colaActividad;

    @BeforeAll
    static void setUp() {
        // Inicializar la instancia de ColaActividad
        colaActividad = new ColaActividad();

        // Limpiar o crear el archivo antes de comenzar las pruebas
        File archivo = new File("src/main/resources/archivosTxt/Actividades.txt");
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    @Test
    @Order(1)
    void testGuardarActividad() {
        Actividad actividad = new Actividad("Actividad 1", "Descripción de la actividad 1", true, null);
        colaActividad.guardarActividad(actividad);

        Actividad actividadBuscada = colaActividad.buscarActividadPorNombre("Actividad 1");
        assertNotNull(actividadBuscada, "La actividad debería estar en la cola.");
        assertEquals("Descripción de la actividad 1", actividadBuscada.getDescripcion());
    }

    @Test
    @Order(2)
    void testEliminarActividad() {
        Actividad actividad = new Actividad("Actividad 2", "Descripción de la actividad 2", false, null);
        colaActividad.guardarActividad(actividad);

        boolean eliminado = colaActividad.eliminarActividad("Actividad 2");
        assertTrue(eliminado, "La actividad debería haberse eliminado correctamente.");

        Actividad actividadBuscada = colaActividad.buscarActividadPorNombre("Actividad 2");
        assertNull(actividadBuscada, "La actividad no debería estar en la cola.");
    }

    @Test
    @Order(3)
    void testModificarActividad() {
        Actividad actividad = new Actividad("Actividad 3", "Descripción de la actividad 3", true, null);
        colaActividad.guardarActividad(actividad);

        boolean modificada = colaActividad.modificarActividad("Actividad 3", "Nueva descripción", false);
        assertTrue(modificada, "La actividad debería haberse modificado correctamente.");

        Actividad actividadBuscada = colaActividad.buscarActividadPorNombre("Actividad 3");
        assertNotNull(actividadBuscada, "La actividad debería estar en la cola.");
        assertEquals("Nueva descripción", actividadBuscada.getDescripcion());
        assertFalse(actividadBuscada.isEsObligatoria());
    }

    @Test
    @Order(4)
    void testEliminarLineaDelArchivo() {
        Actividad actividad = new Actividad("Actividad 4", "Descripción de la actividad 4", true, null);
        colaActividad.guardarActividad(actividad);

        boolean eliminado = colaActividad.eliminarLineaDelArchivo("Actividad 4");
        assertTrue(eliminado, "La actividad debería haberse eliminado del archivo.");

        Actividad actividadBuscada = colaActividad.buscarActividadPorNombre("Actividad 4");
        assertNull(actividadBuscada, "La actividad no debería estar en la cola después de eliminarla.");
    }

    @Test
    @Order(5)
        void testCargarActividadesDesdeArchivo() {
            Actividad actividad1 = new Actividad("Actividad 5", "Descripción de la actividad 5", true, null);
            Actividad actividad2 = new Actividad("Actividad 6", "Descripción de la actividad 6", false, null);
            Queue<Actividad> actividades = new LinkedList<>();
            actividades.add(actividad1);
            actividades.add(actividad2);

            colaActividad.agregarActividadAlArchivo(actividades);

            Queue<Actividad> actividadesCargadas = colaActividad.cargarActividadesDesdeArchivo("src/main/resources/archivosTxt/Actividades.txt");
            assertNotNull(actividadesCargadas, "Las actividades deberían haberse cargado desde el archivo.");
            assertEquals(2, actividadesCargadas.size(), "Deberían haberse cargado 2 actividades.");
        }
    }