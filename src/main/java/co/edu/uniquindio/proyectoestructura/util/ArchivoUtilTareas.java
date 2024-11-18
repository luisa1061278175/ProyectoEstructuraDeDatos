package co.edu.uniquindio.proyectoestructura.util;

import co.edu.uniquindio.proyectoestructura.modelo.Tarea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ArchivoUtilTareas {

    public static final String RUTA_ARCHIVO_TAREAS = "src/main/resources/archivosTxt/Tareas.txt";
    public static final String RUTA_ARCHIVO_ACTIVIDADES = "src/main/resources/archivosTxt/Actividades.txt";

    public static void guardarListaEnArchivo(List<Tarea> tareas, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            for (Tarea tarea : tareas) {
                writer.write(tarea.getNombre() + ";" +
                        tarea.getDescripcion() + ";" +
                        tarea.isObligatoria() + ";" +
                        tarea.getDuracion());
                writer.newLine();
            }
            System.out.println("Las tareas han sido guardadas en el archivo con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas en el archivo.");
            e.printStackTrace();
        }
    }

    public static void guardarTareaEnActividad(String nombreActividad, Tarea nuevaTarea) {
        List<String> lineasActualizadas = new ArrayList<>();
        boolean actividadEncontrada = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO_ACTIVIDADES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos[0].equalsIgnoreCase(nombreActividad)) {
                    StringBuilder nuevaLinea = new StringBuilder();
                    nuevaLinea.append(datos[0]).append(";").append(datos[1]).append(";").append(datos[2]);

                    if (datos.length > 3) {
                        nuevaLinea.append(";").append(datos[3]).append(",").append(nuevaTarea.getNombre());
                    } else {
                        nuevaLinea.append(";").append(nuevaTarea.getNombre());
                    }

                    lineasActualizadas.add(nuevaLinea.toString());
                    actividadEncontrada = true;
                    System.out.println("Se añadió la tarea '" + nuevaTarea.getNombre() + "' a la actividad: " + nombreActividad);
                } else {
                    lineasActualizadas.add(linea);
                }
            }

            if (!actividadEncontrada) {
                System.out.println("Actividad no encontrada con nombre: " + nombreActividad);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

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

    public static Tarea[] cargarTareasDesdeArchivo(String rutaArchivo) {
        List<Tarea> listaTareas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length == 4) {
                    String nombre = partes[0].trim();
                    String descripcion = partes[1].trim();
                    boolean isObligatoria = Boolean.parseBoolean(partes[2].trim());
                    int duracion = Integer.parseInt(partes[3].trim());

                    Tarea tarea = new Tarea(nombre, descripcion, isObligatoria, duracion);
                    listaTareas.add(tarea);
                } else {
                    System.out.println("Formato incorrecto en línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return listaTareas.toArray(new Tarea[0]);
    }
    public Tarea[] cargarTareasArchivo(String rutaArchivo) {
        List<Tarea> listaTareas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String nombre = partes[0].trim();
                    String descripcion = partes[1].trim();
                    boolean isObligatoria = Boolean.parseBoolean(partes[2].trim());
                    int duracion = Integer.parseInt(partes[3].trim());
                    Tarea tarea = new Tarea(nombre, descripcion, isObligatoria, duracion);
                    listaTareas.add(tarea);
                } else {
                    System.out.println("Formato incorrecto en línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return listaTareas.toArray(new Tarea[0]);
    }

    public static <T> T[] colaAArreglo(Queue<T> cola) {

        T[] arreglo = (T[]) new Object[cola.size()];
        int index = 0;
        for (T elemento : cola) {
            arreglo[index++] = elemento;
        }

        return arreglo;
    }

    public static boolean modificarTareasEnArchivo(String nombre, String nuevaDescripcion, boolean esObligatoria, int duracion) {
        File archivo = new File(RUTA_ARCHIVO_TAREAS);
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(nombre)) {
                    lineas.add(nombre + ";" + nuevaDescripcion + ";" + esObligatoria +";"+duracion);
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }
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

    public static boolean eliminarLineaDelArchivo(String identificador) {
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
