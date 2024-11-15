package co.edu.uniquindio.proyectoestructura.estructurasPropias.cola.tarea;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ColaTarea {
    private Queue<Tarea> tareas;
    private static final String RUTA_ARCHIVO_TAREAS = "src/main/resources/archivosTxt/Tareas.txt";
    private static final String RUTA_ARCHIVO_ACTIVIDADES = "src/main/resources/archivosTxt/Actividades.txt";
    public ColaTarea() {
        tareas = new LinkedList<>();
        cargarTareasDesdeArchivo(RUTA_ARCHIVO_TAREAS);
    }


    public void guardarTarea(Tarea tarea) {
        tareas.offer(tarea);
        System.out.println("Actividad guardada: " + tarea);
    }


    public boolean eliminarTarea(String nombre) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equals(nombre)) {
                tareas.remove(tarea);
                System.out.println("Actividad eliminada: " + tarea);
                return true;
            }
        }
        System.out.println("No se encontró la tarea con nombre: " + nombre);
        return false;
    }


    public boolean modificarTarea(String nombre, String nuevaDescripcion, boolean esObligatoria, int duracion) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equals(nombre)) {
                tarea.setDescripcion(nuevaDescripcion);
                tarea.setObligatoria(esObligatoria);
                tarea.setDuracion(duracion);
                System.out.println("Actividad modificada: " + tarea);
                return true;
            }
        }
        System.out.println("No se encontró la tarea con nombre: " + nombre);
        return false;
    }



    /*
     *
     *
     *
     *
     * METODOS PARA GUARDAR EN TXT
     *
     *
     *
     * */


    public void agregarTareaAlArchivo(Queue<Tarea> tarea) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_TAREAS, true))) {
            for (Tarea tarea1 : tareas) {

                writer.write(tarea1.getNombre() + ";" + tarea1.getDescripcion() + ";" + tarea1.isObligatoria()+";"+tarea1.getDuracion());
                writer.newLine();
            }
            System.out.println("Tareas guardadas en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al guardar las TAreas en el archivo: " + e.getMessage());
        }
    }

    public static void guardarListaEnArchivo(List<Tarea> tareas, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) { // true para abrir en modo append
            for (Tarea tarea : tareas) {
                // Escribir los atributos de la tarea en una línea, separados por ';'
                writer.write(tarea.getNombre() + ";" +
                        tarea.getDescripcion() + ";" +
                        tarea.isObligatoria() + ";" +
                        tarea.getDuracion());
                writer.newLine(); // Salto de línea después de cada tarea
            }
            System.out.println("Las tareas han sido guardadas en el archivo con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas en el archivo.");
            e.printStackTrace();
        }
    }

    public void guardarTareaEnActividad(String nombreActividad, Tarea nuevaTarea) {
        List<String> lineasActualizadas = new ArrayList<>();
        boolean actividadEncontrada = false;

        // Leer el archivo de actividades y actualizar la lista de líneas
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO_ACTIVIDADES))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");

                // Si encontramos la actividad con el nombre indicado
                if (datos[0].equalsIgnoreCase(nombreActividad)) {
                    StringBuilder nuevaLinea = new StringBuilder();
                    nuevaLinea.append(datos[0]).append(";").append(datos[1]).append(";").append(datos[2]);

                    // Añadir tarea existente y nueva tarea
                    if (datos.length > 3) {
                        nuevaLinea.append(";").append(datos[3]).append(",").append(nuevaTarea.getNombre());
                    } else {
                        nuevaLinea.append(";").append(nuevaTarea.getNombre());
                    }

                    lineasActualizadas.add(nuevaLinea.toString());
                    actividadEncontrada = true;
                    System.out.println("Se añadió la tarea '" + nuevaTarea.getNombre() + "' a la actividad: " + nombreActividad);
                } else {
                    // Añadir la línea original si no es la actividad buscada
                    lineasActualizadas.add(linea);
                }
            }

            if (!actividadEncontrada) {
                System.out.println("Actividad no encontrada con nombre: " + nombreActividad);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Escribir las líneas actualizadas de vuelta al archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_ACTIVIDADES, false))) {
            for (String lineaActualizada : lineasActualizadas) {
                writer.write(lineaActualizada);
                writer.newLine();
            }
            System.out.println("Datos guardados en " + RUTA_ARCHIVO_ACTIVIDADES);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public Tarea buscarTareaPorNombre(String nombreActividad) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equalsIgnoreCase(nombreActividad)) {
                return tarea;
            }
        }
        return null;
    }

    public Queue<Tarea> cargarTareasDesdeArchivo(String rutaArchivo) {


        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(";");

                if (partes.length == 4) {
                    String nombre = partes[0].trim();
                    String descripcion = partes[1].trim();
                    boolean isObligatoria = Boolean.parseBoolean(partes[2].trim());
                    int duracion = Integer.parseInt(partes[3].trim());

                    // Creamos una nueva actividad con los datos de la línea
                    Tarea tarea = new Tarea(nombre, descripcion, isObligatoria,duracion);

                    // Agregamos la actividad a la cola
                    tareas.add(tarea);
                } else {
                    System.out.println("Formato incorrecto en línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return tareas;
    }
    //para usar en la interfaz

    public static <T> T[] colaAArreglo(Queue<T> cola) {
        // Crear un arreglo con el tamaño de la cola y del mismo tipo que los elementos de la cola
        @SuppressWarnings("unchecked")
        T[] arreglo = (T[]) new Object[cola.size()];

        int index = 0;
        for (T elemento : cola) {
            arreglo[index++] = elemento;
        }

        return arreglo;
    }
    public boolean modificarTareasEnArchivo(String nombre, String nuevaDescripcion, boolean esObligatoria, int duracion) {
        File archivo = new File(RUTA_ARCHIVO_TAREAS);
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(nombre)) {
                    // Modificar la actividad con la nueva descripción y obligatoriedad
                    lineas.add(nombre + ";" + nuevaDescripcion + ";" + esObligatoria +";"+duracion);
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }

        // Reescribir el archivo con la actividad modificada
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Tarea modificada en el archivo.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarLineaDelArchivo(String identificador) {
        File archivo = new File(RUTA_ARCHIVO_TAREAS);
        List<String> lineas = new ArrayList<>();
        boolean eliminado = false;


        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                if (!linea.startsWith(identificador + ";")) {
                    lineas.add(linea);
                } else {
                    eliminado = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }


        if (eliminado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
                for (String linea : lineas) {
                    bw.write(linea);
                    bw.newLine();
                }
                System.out.println("Línea eliminada del archivo.");
                return true;
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("No se encontró ninguna línea con el identificador: " + identificador);
            return false;
        }
    }
}



