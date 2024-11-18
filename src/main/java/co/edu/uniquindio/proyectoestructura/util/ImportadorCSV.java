package co.edu.uniquindio.proyectoestructura.util;

import javafx.scene.shape.Path;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImportadorCSV {
    public  void importarDatos(String archivoExterno, String archivoDestino) {
        try {

            Path pathExterno = (Path) Paths.get(archivoExterno);
            if (!Files.exists((java.nio.file.Path) pathExterno)) {
                System.out.println("El archivo externo no existe.");
                return;
            }

            BufferedReader lectorExterno = new BufferedReader(new FileReader(archivoExterno));

            BufferedWriter escritorDestino = new BufferedWriter(new FileWriter(archivoDestino, true));

            String linea;
            while ((linea = lectorExterno.readLine()) != null) {

                escritorDestino.write(linea);
                escritorDestino.newLine();
            }

            lectorExterno.close();
            escritorDestino.close();

            System.out.println("Datos importados correctamente al archivo destino.");
        } catch (IOException e) {
            System.err.println("Error al importar los datos: " + e.getMessage());
        }
    }

}
