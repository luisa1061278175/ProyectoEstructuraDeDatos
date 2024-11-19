package co.edu.uniquindio.proyectoestructura.estructurasPropias.cola.tarea;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class ColaTareaTest {

    @Test
    public void testGuardarTarea() {
        ColaTarea colaTarea = new ColaTarea();
        Tarea tarea = new Tarea("Tarea 1", "Descripción de la tarea", true, 5);

        colaTarea.guardarTarea(tarea);

        // Verificamos si la tarea ha sido añadida a la cola
        assertTrue(colaTarea.getTareas().contains(tarea), "La tarea no fue añadida correctamente.");
    }

    @Test
    public void testEliminarTarea() {
        ColaTarea colaTarea = new ColaTarea();
        Tarea tarea = new Tarea("Tarea 1", "Descripción de la tarea", true, 5);
        colaTarea.guardarTarea(tarea);

        // Intentamos eliminar la tarea
        boolean eliminada = colaTarea.eliminarTarea("Tarea 1");

        // Verificamos que la tarea haya sido eliminada
        assertTrue(eliminada, "La tarea no fue eliminada.");
        assertFalse(colaTarea.getTareas().contains(tarea), "La tarea no fue eliminada correctamente.");
    }
    @Test
    public void testModificarTarea() {
        ColaTarea colaTarea = new ColaTarea();
        Tarea tarea = new Tarea("Tarea 1", "Descripción de la tarea", true, 5);
        colaTarea.guardarTarea(tarea);

        // Modificamos la tarea
        boolean modificada = colaTarea.modificarTarea("Tarea 1", "Nueva descripción", false, 10);

        // Verificamos que la tarea haya sido modificada
        assertTrue(modificada, "La tarea no fue modificada.");
        assertEquals("Nueva descripción", tarea.getDescripcion(), "La descripción no fue actualizada.");
        assertFalse(tarea.isObligatoria(), "La obligatoriedad no fue actualizada.");
        assertEquals(10, tarea.getDuracion(), "La duración no fue actualizada.");
    }

    @Test
    public void testAgregarTareaAlArchivo() {
        ColaTarea colaTarea = new ColaTarea();
        Tarea tarea = new Tarea("Tarea 1", "Descripción de la tarea", true, 5);
        colaTarea.guardarTarea(tarea);

        // Guardamos las tareas en el archivo


        // Verificamos que el archivo se haya actualizado correctamente (Esto dependerá de las expectativas sobre el archivo)
        File archivo = new File("src/main/resources/archivosTxt/Tareas.txt");
        assertTrue(archivo.exists(), "El archivo no fue creado o actualizado.");
    }
    @Test
    public void testBuscarTareaPorNombre() {
        ColaTarea colaTarea = new ColaTarea();
        Tarea tarea = new Tarea("Tarea 1", "Descripción de la tarea", true, 5);
        colaTarea.guardarTarea(tarea);

        // Buscamos la tarea por su nombre
        Tarea tareaEncontrada = colaTarea.buscarTareaPorNombre("Tarea 1");

        // Verificamos que la tarea sea la correcta
        assertNotNull(tareaEncontrada, "La tarea no fue encontrada.");
        assertEquals("Tarea 1", tareaEncontrada.getNombre(), "El nombre de la tarea encontrada no es correcto.");
    }

}
