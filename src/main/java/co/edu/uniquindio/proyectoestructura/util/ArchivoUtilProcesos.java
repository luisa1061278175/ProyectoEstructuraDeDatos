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

    public Proceso[] leerTxt(String nombreArchivo) {
        List<Proceso> procesos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");


                if (datos.length >= 3) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();

                    // Lista de actividades
                    Queue<Actividad> listaActividades = new LinkedList<>();

                    // Verificar que haya actividades (debemos procesar desde el tercer elemento)
                    if (datos.length > 2) {
                        String actividadesConcatenadas = datos[2].trim(); // Las actividades están después del segundo ";"

                        String[] actividadesArray = actividadesConcatenadas.split(",");


                        for (String actividad : actividadesArray) {
                            Actividad actividadObj = new Actividad(actividad.trim(), "", false, null);  // Ajustar según los atributos de Actividad
                            listaActividades.add(actividadObj);  // Añadir la actividad a la cola
                        }

                        System.out.println("Actividades cargadas: " + listaActividades);
                    }

                    Proceso proceso = new Proceso(nombre, id, listaActividades);

                    procesos.add(proceso);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // Retornar el arreglo de procesos
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
                    List<String> listaActividades = new LinkedList<>();
                    for (int i = 2; i < datos.length; i++) {
                        listaActividades.add(datos[i].trim());
                    }
                    Proceso proceso = new Proceso(nombre, id, listaActividades);
                }
            }

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

