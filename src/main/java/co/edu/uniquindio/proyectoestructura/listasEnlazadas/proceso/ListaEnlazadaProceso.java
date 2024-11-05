package co.edu.uniquindio.proyectoestructura.listasEnlazadas.proceso;

import co.edu.uniquindio.proyectoestructura.modelo.Login;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println("Estamos en la lista enlazada "+proceso);
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_PROCESOS, true))) { // Modo append
            NodoProceso temp = cabeza;
            while (temp != null) {
                Proceso proceso = temp.getProceso();
                writer.write(proceso.toString()); // Asumiendo que toString está en formato "id;nombre"
                writer.newLine();
                temp = temp.getSiguiente();
            }
            System.out.println("Datos guardados en " + RUTA_ARCHIVO_PROCESOS);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos en el archivo: " + e.getMessage());
        }
    }


    public static Proceso[] leerArchivo(String nombreArchivo) {
        List<Proceso> procesos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 2) {
                    String id = (datos[0]);
                    String nombre = datos[1];
                    procesos.add(new Proceso(nombre,id));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return procesos.toArray(new Proceso[0]);
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
