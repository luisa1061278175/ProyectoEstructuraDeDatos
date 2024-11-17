package co.edu.uniquindio.proyectoestructura.alerta;

import javafx.scene.control.Alert;

public class Alerta {

    //CREADO
    public void mensajeCreado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Creado Correctamente");
        alert.showAndWait();
    }

    //PROCESO ENCONTRADO
    public void procesoEncontrado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Proceso encontrado");
        alert.showAndWait();
    }

    //PROCESO NO ENCONTRADO
    public void procesoNoEncontrado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Proceso no encontrado");
        alert.showAndWait();
    }


    //EXPORTADO
    public void mensajeExportado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Exportado Correctamente");
        alert.showAndWait();
    }


//MODIFICADO

    public void mensajeModificado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Modificado Correctamente");
        alert.showAndWait();
    }

    //ELIMINADO
    public void mensajeEliminado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Eliminado Correctamente");
        alert.showAndWait();
    }

    public void mostrarAlertaError(String titulo, String mensaje) {
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setTitle(titulo);
        alertaError.setHeaderText(null); // Sin encabezado
        alertaError.setContentText(mensaje);
        alertaError.showAndWait(); // Espera a que el usuario cierre la alerta
    }

}
