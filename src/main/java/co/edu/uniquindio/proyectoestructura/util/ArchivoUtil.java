package co.edu.uniquindio.proyectoestructura.util;

import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.NodoProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Login;

import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ArchivoUtil {
    private NodoProceso cabeza;

    private static final String RUTA_ARCHIVO_PROCESOS = "src/main/resources/archivosTxt/Procesos.txt";
    private static final String RUTA_ARCHIVO = "src/main/resources/archivosTxt/TareasRealizadas";

    public static Login leerArchivos() {
        ResourceBundle resourceBundle;
        resourceBundle = ResourceBundle.getBundle("usuario");
        String usuario = resourceBundle.getString("usuario");
        String contrasena = resourceBundle.getString("contrasena");

        return new Login(usuario, contrasena);
    }

    /**
     *
     *
     * LEER EN TXT
     *
     *
     *
     */



    public static ArrayList<String> leerArchivo(String ruta) throws IOException {
        ArrayList<String> contenido = new ArrayList<>();
        FileReader fr = new FileReader(ruta);
        BufferedReader bfr = new BufferedReader(fr);
        String linea;
        while ((linea = bfr.readLine()) != null) {
            contenido.add(linea.trim());
        }
        bfr.close();
        fr.close();
        return contenido;
    }

    public static boolean verificarCredenciales(String rutaArchivo, String id, String contrasenia) {
        try {
            ArrayList<String> contenido = leerArchivo(rutaArchivo);

            for (String linea : contenido) {
                String[] partes = linea.split(";");

                    String idArchivo = partes[0].trim();
                    String contraseniaArchivo = partes[3].trim();

                    if (idArchivo.equals(id) && contraseniaArchivo.equals(contrasenia)) {
                        return true;
                    }

            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }
//metodo para agregar quien hizo la tarea
    public void agregarUsuarioAlArchivo(String id, String tarea) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            writer.write(id + ";" + tarea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar el usuario en el archivo: " + e.getMessage());
        }
    }


}
