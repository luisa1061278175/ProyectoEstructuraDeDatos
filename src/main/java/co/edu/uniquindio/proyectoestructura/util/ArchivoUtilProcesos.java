package co.edu.uniquindio.proyectoestructura.util;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArchivoUtilProcesos {

    private static final String RUTA_ARCHIVO_PROCESOS = "src/main/resources/archivosTxt/Procesos.txt";

    public void agregarProcesoAlArchivo(String id, String nombre) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_PROCESOS, true))) {
            writer.write(id + ";" + nombre);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar el proceso en el archivo: " + e.getMessage());
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
                        listaActividades.add(datos[i]);
                    }
                    procesos.add(new Proceso(nombre, id, listaActividades));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return procesos.toArray(new Proceso[0]);
    }

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
                }
            }
            System.out.println("Datos cargados exitosamente desde el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void eliminarProcesoDeArchivo(String idProceso) {
        File archivo = new File(RUTA_ARCHIVO_PROCESOS);
        List<String> lineasActualizadas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (!datos[0].equals(idProceso)) {
                    lineasActualizadas.add(linea);
                } else {
                    System.out.println("Proceso con ID " + idProceso + " eliminado del archivo.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
            for (String lineaActualizada : lineasActualizadas) {
                writer.write(lineaActualizada);
                writer.newLine();
            }
            System.out.println("Archivo actualizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void guardarActividadEnProcesoTxt(String idProceso, Actividad nuevaActividad) {
        List<String> lineasActualizadas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO_PROCESOS))) {
            String linea;
            boolean actividadAgregada = false;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(idProceso)) {
                    StringBuilder nuevaLinea = new StringBuilder();
                    nuevaLinea.append(datos[0]).append(";").append(datos[1]).append(";");
                    if (datos.length > 2) {
                        nuevaLinea.append(datos[2]).append(",");
                    }
                    nuevaLinea.append(nuevaActividad.getNombre());
                    lineasActualizadas.add(nuevaLinea.toString());
                    actividadAgregada = true;
                    System.out.println("Actividad agregada.");
                } else {
                    lineasActualizadas.add(linea);
                }
            }

            if (!actividadAgregada) {
                System.out.println("Proceso no encontrado con ID: " + idProceso);
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_PROCESOS, false))) {
            for (String lineaActualizada : lineasActualizadas) {
                writer.write(lineaActualizada);
                writer.newLine();
            }
            System.out.println("Archivo actualizado.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}

