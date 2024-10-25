package co.edu.uniquindio.proyectoestructura.listasEnlazadas.proceso;

import co.edu.uniquindio.proyectoestructura.modelo.Login;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class ListaEnlazadaProceso  {
    private NodoProceso cabeza;

    // Constructor
    public ListaEnlazadaProceso() {
        this.cabeza = null;
    }

    // Método para agregar un proceso
    public void agregarProceso(Proceso proceso) {
        NodoProceso nuevoNodo = new NodoProceso(proceso);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoProceso temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
    }

    // Método para eliminar un proceso por ID
    public void eliminarProceso(String id) {
        if (cabeza == null) return;

        if (cabeza.getProceso().getId().equals(id)) {
            cabeza = cabeza.getSiguiente();
            return;
        }

        NodoProceso actual = cabeza;
        NodoProceso anterior = null;

        while (actual != null && !actual.getProceso().getId().equals(id)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual != null) {
            anterior.setSiguiente(actual.getSiguiente());
        }
    }
    private static final String RUTA_ARCHIVO_PROCESOS = "src/main/resources/archivosTxt/Procesos.txt";

    public void guardarProcesosEnArchivo() {
        if (cabeza == null) {
            System.out.println("La lista de procesos está vacía. No hay datos para guardar.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_PROCESOS))) {
            NodoProceso temp = cabeza;
            while (temp != null) {
                Proceso proceso = temp.getProceso();
                writer.write(proceso.toString());  // Asegúrate de que la clase Proceso tenga un método toString adecuado
                writer.newLine();
                temp = temp.getSiguiente();
            }
            System.out.println("Datos guardados en " + RUTA_ARCHIVO_PROCESOS);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos en el archivo: " + e.getMessage());
        }
    }

    // Método para mostrar todos los procesos
    public void mostrarProcesos() {
        if (cabeza == null) {
            System.out.println("No hay procesos en la lista.");
            return;
        }

        NodoProceso temp = cabeza;
        while (temp != null) {
            System.out.println(temp.getProceso());
            temp = temp.getSiguiente();
        }
    }

    // Método para obtener la cabeza de la lista (opcional)
    public NodoProceso getCabeza() {
        return cabeza;
    }
}
