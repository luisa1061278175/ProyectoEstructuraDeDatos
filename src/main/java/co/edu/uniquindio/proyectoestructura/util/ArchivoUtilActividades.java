package co.edu.uniquindio.proyectoestructura.util;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArchivoUtilActividades {
    public static final String RUTA_ARCHIVO_ACTIVIDADES = "src/main/resources/archivosTxt/Actividades.txt";

    public boolean eliminarLineaDelArchivo(String identificador) {
        File archivo = new File(RUTA_ARCHIVO_ACTIVIDADES);
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


    public void agregarActividadAlArchivo(Queue<Actividad> actividades) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_ACTIVIDADES, true))) {
            for (Actividad actividad : actividades) {

                writer.write(actividad.getNombre() + ";" + actividad.getDescripcion() + ";" + actividad.isEsObligatoria());
                writer.newLine();
            }
            System.out.println("Actividades guardadas en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al guardar las actividades en el archivo: " + e.getMessage());
        }
    }
    public Actividad[] leerArchivo(String rutaArchivo) {
        List<Actividad> actividades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos.length >= 3) {
                    String nombre = datos[0].trim();
                    String descripcion = datos[1].trim();
                    boolean obligatoria = Boolean.parseBoolean(datos[2].trim());

                    Queue<Tarea> tareas = new LinkedList<>();
                    if (datos.length > 3) {
                        String[] nombresTareas = datos[3].split(",");
                        for (String nombreTarea : nombresTareas) {
                            tareas.add(new Tarea(nombreTarea.trim(), "Descripción pendiente", false, 0));
                        }
                    }

                    Actividad actividad = new Actividad(nombre, descripcion, obligatoria, tareas);
                    actividades.add(actividad);

                } else {
                    System.err.println("Formato incorrecto en línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return actividades.toArray(new Actividad[0]);
    }

    public static Queue<Actividad> cargarActividadesDesdeArchivo() {
        Queue<Actividad> actividades = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO_ACTIVIDADES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";", 4);
                String nombre = partes[0].trim();
                String descripcion = partes[1].trim();
                boolean isObligatoria = Boolean.parseBoolean(partes[2].trim());

                Queue<Tarea> tareas = null;
                if (partes.length == 4) {
                    String[] tareasArray = partes[3].split(",");
                    tareas = new LinkedList<>();
                    for (String tarea : tareasArray) {
                        tareas.add(new Tarea(tarea.trim(), null, false, 0));
                    }
                }
                Actividad actividad = new Actividad(nombre, descripcion, isObligatoria, tareas);
                actividades.add(actividad);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return actividades;
    }

    public static boolean modificarActividadEnArchivo(String nombre, String nuevaDescripcion, boolean esObligatoria) {
        File archivo = new File(RUTA_ARCHIVO_ACTIVIDADES);
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(nombre)) {
                    lineas.add(nombre + ";" + nuevaDescripcion + ";" + esObligatoria);
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
            System.out.println("Actividad modificada en el archivo.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }
}
