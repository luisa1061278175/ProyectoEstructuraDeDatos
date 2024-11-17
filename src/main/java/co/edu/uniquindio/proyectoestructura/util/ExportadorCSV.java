package co.edu.uniquindio.proyectoestructura.util;

import co.edu.uniquindio.proyectoestructura.alerta.Alerta;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorCSV {
    Alerta alerta = new Alerta();

    public void exportToCSV(List<Proceso> proceso, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"));

        // Mostrar el diálogo de guardar
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                // Escribir encabezados
                writer.write("ID,Nombre,Edad\n");

                // Escribir las filas de datos
                for (Proceso proceso1 : proceso) {
                    String line = proceso1.getId() + "," + proceso1.getNombre();
                    writer.write(line + "\n");
                }
                alerta.mensajeExportado();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Operación cancelada por el usuario.");
        }
    }
}
