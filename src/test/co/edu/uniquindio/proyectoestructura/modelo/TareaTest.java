package co.edu.uniquindio.proyectoestructura.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TareaTest {
    @Test
    public void testConstructor() {
    // Crear la tarea con valores específicos
    Tarea tarea = new Tarea("Tarea 1", "Descripción de la tarea", true, 5);

    // Verificar que los valores se asignan correctamente
    assertEquals("Tarea 1", tarea.getNombre());
    assertEquals("Descripción de la tarea", tarea.getDescripcion());
    assertTrue(tarea.isObligatoria());
    assertEquals(5, tarea.getDuracion());
}
    @Test
    public void testSettersAndGetters() {
        // Crear la tarea
        Tarea tarea = new Tarea("Tarea 1", "Descripción de la tarea", true, 5);

        // Verificar los getters iniciales
        assertEquals("Tarea 1", tarea.getNombre());
        assertEquals("Descripción de la tarea", tarea.getDescripcion());
        assertTrue(tarea.isObligatoria());
        assertEquals(5, tarea.getDuracion());

        // Cambiar los valores usando los setters
        tarea.setNombre("Tarea 2");
        tarea.setDescripcion("Descripción actualizada");
        tarea.setObligatoria(false);
        tarea.setDuracion(10);

        // Verificar que los valores se han modificado correctamente
        assertEquals("Tarea 2", tarea.getNombre());
        assertEquals("Descripción actualizada", tarea.getDescripcion());
        assertFalse(tarea.isObligatoria());
        assertEquals(10, tarea.getDuracion());
    }
    @Test
    public void testToString() {
        // Crear la tarea
        Tarea tarea = new Tarea("Tarea 1", "Descripción de la tarea", true, 5);

        // Crear el valor esperado de la cadena
        String expected = "Tarea{nombre='Tarea 1', descripcion='Descripción de la tarea', obligatoria=true, duracion=5}";

        // Verificar que el método toString devuelve la cadena correcta
        assertEquals(expected, tarea.toString());
    }

}