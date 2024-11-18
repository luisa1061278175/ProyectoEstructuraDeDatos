package co.edu.uniquindio.proyectoestructura.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Queue;

public class ProcesoTest {

    @Test
    public void testConstructor() {
        // Crear una cola de actividades
        Queue<Actividad> listaActividades = new LinkedList<>();
        listaActividades.add(new Actividad("Actividad 1", "Descripción de la actividad", true, new LinkedList<>()));

        // Crear el proceso con valores específicos
        Proceso proceso = new Proceso("Proceso 1", "ID123", listaActividades);

        // Verificar que los valores se asignan correctamente
        assertEquals("Proceso 1", proceso.getNombre());
        assertEquals("ID123", proceso.getId());
        assertEquals(listaActividades, proceso.getListaActividades());
    }
    @Test
    public void testSettersAndGetters() {
        // Crear una cola de actividades
        Queue<Actividad> listaActividades = new LinkedList<>();
        listaActividades.add(new Actividad("Actividad 1", "Descripción de la actividad", true, new LinkedList<>()));

        // Crear el proceso
        Proceso proceso = new Proceso("Proceso 1", "ID123", listaActividades);

        // Verificar los valores iniciales
        assertEquals("Proceso 1", proceso.getNombre());
        assertEquals("ID123", proceso.getId());
        assertEquals(listaActividades, proceso.getListaActividades());

        // Modificar los valores usando los setters
        proceso.setNombre("Proceso 2");
        proceso.setId("ID456");

        // Crear una nueva lista de actividades y actualizarla
        Queue<Actividad> nuevasActividades = new LinkedList<>();
        nuevasActividades.add(new Actividad("Actividad 2", "Descripción actividad 2", false, new LinkedList<>()));
        proceso.setListaActividades(nuevasActividades);

        // Verificar que los valores se han modificado correctamente
        assertEquals("Proceso 2", proceso.getNombre());
        assertEquals("ID456", proceso.getId());
        assertEquals(nuevasActividades, proceso.getListaActividades());
    }
    @Test
    public void testToString() {
        // Crear una cola de actividades
        Queue<Actividad> listaActividades = new LinkedList<>();
        listaActividades.add(new Actividad("Actividad 1", "Descripción de la actividad", true, new LinkedList<>()));

        // Crear el proceso
        Proceso proceso = new Proceso("Proceso 1", "ID123", listaActividades);

        // Crear el valor esperado de la cadena
        String expected = "Proceso{nombre='Proceso 1', id='ID123'}";

        // Verificar que el método toString devuelve la cadena correcta
        assertEquals(expected, proceso.toString());
    }

}
