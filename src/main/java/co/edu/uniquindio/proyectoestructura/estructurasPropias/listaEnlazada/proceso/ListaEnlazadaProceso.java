package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso;

import co.edu.uniquindio.proyectoestructura.controller.AdminActividadController;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;

import java.io.*;
import java.util.*;

public class ListaEnlazadaProceso {
    private NodoProceso cabeza;
    private static final String RUTA_ARCHIVO_PROCESOS = "src/main/resources/archivosTxt/Procesos.txt";

    AdminActividadController adminActividadController = new AdminActividadController();

    public ListaEnlazadaProceso() {
        this.cabeza = null;
        cargarDesdeArchivo(RUTA_ARCHIVO_PROCESOS);
    }
    /*
     *
     *
     * MÉTODOS PROPIOS DE LA LISTA PARA HACER CRUD
     *
     *
     * */

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
        System.out.println("Estamos en la lista enlazada " + proceso);
    }


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

    public boolean modificarProceso(String id, String nuevoNombre) {
        NodoProceso actual = cabeza;
        while (actual != null) {

            if (actual.getProceso().getId().equals(id)) {
                actual.getProceso().setNombre(nuevoNombre);
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    public Proceso buscarProceso(String id) {

        NodoProceso actual = cabeza;
        while (actual != null) {
            if (actual.getProceso().getId().equals(id)) {
                return actual.getProceso();
            }
            actual = actual.getSiguiente();
        }
        System.out.println("Proceso no encontrado con ID: " + id);
        return null;
    }

    // Método para agregar una actividad a un proceso específico
    public boolean agregarActividadAProceso(String idProceso, Actividad actividad) {
        Proceso proceso = buscarProceso(idProceso);
        if (proceso != null) {
            adminActividadController.guardar(actividad);
            return true;
        }
        System.out.println("No se pudo agregar la actividad. Proceso no encontrado.");
        return false;
    }


    /*
     *
     *
     *
     *
     * METODOS PARA GUARDAR PROCESOS EN TXT
     *
     *
     *
     *
     *
     * */
    public void guardarTxt() {
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

    public void modificarTxt(String rutaArchivo, String id, String nuevaLinea) {
        File archivo = new File(rutaArchivo);
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(id + ";")) {
                    lineas.add(nuevaLinea);
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }


    public static Proceso[] leerTxt(String nombreArchivo) {
        List<Proceso> procesos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 2) {
                    String id = datos[0];
                    String nombre = datos[1];
                    Queue<String> listaActividades = new LinkedList<>();
                    for (int i = 2; i < datos.length; i++) {
                        listaActividades.add(datos[i]); // Asumiendo constructor de Tarea que recibe un String
                    }
                    procesos.add(new Proceso(nombre, id, listaActividades));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return procesos.toArray(new Proceso[0]);
    }

    //ESTE METODO CARGA LOS DATOS DE UN TXT Y LOS PONE EN UNA LISTA ENLAZADA, PARA
    //INICIAR EL PROGRAMA Y PODER TRABAJAR CON LOS DATOS EN LA LISTA ENLAZADA A LO
    //LARGO DE LA EJECUCION
    public void cargarDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 2) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    Queue<String> listaActividades = new LinkedList<>();
                    for (int i = 2; i < datos.length; i++) {
                        listaActividades.add(datos[i].trim());
                    }
                    Proceso proceso = new Proceso(nombre, id, listaActividades);
                    agregarProceso(proceso);
                }
            }
            System.out.println("Datos cargados exitosamente desde el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

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

    //metodo para guardar las actividades en cada proceso:
    public void guardarActividadEnProcesoTxt(String idProceso, Actividad nuevaActividad) {
        List<String> lineasActualizadas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO_PROCESOS))) {
            String linea;
            boolean actividadAgregada = false;

            // Leer cada línea del archivo actual
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");

                // Si encontramos el proceso con el ID deseado, agregamos la nueva actividad
                if (datos[0].equals(idProceso)) {
                    StringBuilder nuevaLinea = new StringBuilder();
                    nuevaLinea.append(datos[0]).append(";").append(datos[1]).append(";");

                    // Si ya hay actividades, las añadimos a la nueva línea
                    if (datos.length > 2) {
                        nuevaLinea.append(datos[2]).append(",");
                    }
                    // Añadir la nueva actividad al final de la línea
                    nuevaLinea.append(nuevaActividad.getNombre());

                    lineasActualizadas.add(nuevaLinea.toString());
                    actividadAgregada = true;
                    System.out.println("Se añadió la actividad '" + nuevaActividad.getNombre() + "' al proceso: " + idProceso);
                } else {
                    // Si no es el proceso buscado, añadir la línea sin modificaciones
                    lineasActualizadas.add(linea);
                }
            }

            // Si no se encontró el proceso en el archivo, mostramos un mensaje
            if (!actividadAgregada) {
                System.out.println("Proceso no encontrado con ID: " + idProceso);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Escribir todas las líneas actualizadas de vuelta al archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_PROCESOS, false))) {
            for (String lineaActualizada : lineasActualizadas) {
                writer.write(lineaActualizada);
                writer.newLine();
            }
            System.out.println("Datos guardados en " + RUTA_ARCHIVO_PROCESOS);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }




    public NodoProceso getCabeza() {
        return cabeza;
    }
}
