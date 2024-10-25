package co.edu.uniquindio.proyectoestructura.util;

import co.edu.uniquindio.proyectoestructura.listasEnlazadas.proceso.NodoProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Login;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;

import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ArchivoUtil {
    private NodoProceso cabeza;

    private static final String RUTA_ARCHIVO_PROCESOS = "src/main/resources/archivosTxt/Procesos.txt";

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

    //guardar proceso en txt
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



    public static boolean verificarCredenciales(String rutaArchivo, String id, String contrasenia) {
        try {
            ArrayList<String> contenido = leerArchivo(rutaArchivo);

            for (String linea : contenido) {
                String[] partes = linea.split(",");  // Separar cada línea en sus partes

                // Verificar que la línea tenga al menos dos columnas (id y contrasenia)
                if (partes.length >= 2) {
                    String idArchivo = partes[0].trim();
                    String contraseniaArchivo = partes[1].trim();

                    if (idArchivo.equals(id) && contraseniaArchivo.equals(contrasenia)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }


}
