package co.edu.uniquindio.proyectoestructura.modelo;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Queue;

public class ActividadTest {

    @Test
    public void testConstructor() {
        // Crear una cola de tareas (para este ejemplo, usaremos LinkedList como implementación de Queue)
        Queue<Tarea> tareas = new LinkedList<>();
        tareas.add(new Tarea("Tarea 1", "Descripción tarea 1", true, 5));

        // Crear la actividad con valores específicos
        Actividad actividad = new Actividad("Actividad 1", "Descripción de la actividad", true, tareas);

        // Verificar que los valores se asignan correctamente
        assertEquals("Actividad 1", actividad.getNombre());
        assertEquals("Descripción de la actividad", actividad.getDescripcion());
        assertTrue(actividad.isEsObligatoria());
        assertEquals(tareas, actividad.getTareas());
    }
    @Test
    public void testSettersAndGetters() {
        // Crear una cola de tareas
        Queue<Tarea> tareas = new LinkedList<>();
        tareas.add(new Tarea("Tarea 1", "Descripción tarea 1", true, 5));

        // Crear la actividad
        Actividad actividad = new Actividad("Actividad 1", "Descripción de la actividad", true, tareas);

        // Verificar los valores iniciales
        assertEquals("Actividad 1", actividad.getNombre());
        assertEquals("Descripción de la actividad", actividad.getDescripcion());
        assertTrue(actividad.isEsObligatoria());
        assertEquals(tareas, actividad.getTareas());

        // Modificar los valores usando los setters
        actividad.setNombre("Actividad 2");
        actividad.setDescripcion("Descripción actualizada");
        actividad.setEsObligatoria(false);

        // Crear una nueva cola de tareas y actualizarla
        Queue<Tarea> nuevasTareas = new LinkedList<>();
        nuevasTareas.add(new Tarea("Tarea 2", "Descripción tarea 2", false, 10));
        actividad.setTareas(nuevasTareas);

        // Verificar que los valores se han modificado correctamente
        assertEquals("Actividad 2", actividad.getNombre());
        assertEquals("Descripción actualizada", actividad.getDescripcion());
        assertFalse(actividad.isEsObligatoria());
        assertEquals(nuevasTareas, actividad.getTareas());
    }
    @Test
    public void testToString() {
        // Crear una cola de tareas
        Queue<Tarea> tareas = new LinkedList<>();
        tareas.add(new Tarea("Tarea 1", "Descripción tarea 1", true, 5));

        // Crear la actividad
        Actividad actividad = new Actividad("Actividad 1", "Descripción de la actividad", true, tareas);

        // Crear el valor esperado de la cadena
        String expected = "Actividad{nombre='Actividad 1', descripcion='Descripción de la actividad', esObligatoria=true, tareas=" + tareas + "}";

        // Verificar que el método toString devuelve la cadena correcta
        assertEquals(expected, actividad.toString());
    }

}
