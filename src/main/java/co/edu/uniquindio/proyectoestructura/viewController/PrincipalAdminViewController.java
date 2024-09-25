package co.edu.uniquindio.proyectoestructura.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrincipalAdminViewController {

    @FXML
    private Button btnActividades;

    @FXML
    private Button btnProcesos;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnTareas;

    @FXML
    private Button btnUsuarios;


    @FXML
    private void handleMouseEnterTareas() {
        btnTareas.setStyle("-fx-background-color: #ff57cb; -fx-text-fill: #ffffff;");
    }

    @FXML
    private void handleMouseExitTareas() {
        btnTareas.setStyle("-fx-background-color: #000000; -fx-text-fill: #fffefe;");
    }

    @FXML
    private void handleMouseEnterProcesos() {
        btnProcesos.setStyle("-fx-background-color: #ff57cb; -fx-text-fill: #ffffff;");
    }

    @FXML
    private void handleMouseExitProcesos() {
        btnProcesos.setStyle("-fx-background-color: #000000; -fx-text-fill: #fffefe;");
    }

    @FXML
    private void handleMouseEnterActividades() {
        btnActividades.setStyle("-fx-background-color: #ff57cb; -fx-text-fill: #ffffff;");
    }

    @FXML
    private void handleMouseExitActividades() {
        btnActividades.setStyle("-fx-background-color: #000000; -fx-text-fill: #fffefe;");
    }

    @FXML
    private void handleMouseEnterUsuarios() {
        btnUsuarios.setStyle("-fx-background-color: #ff57cb; -fx-text-fill: #ffffff;");
    }

    @FXML
    private void handleMouseExitUsuarios() {
        btnUsuarios.setStyle("-fx-background-color: #000000; -fx-text-fill: #fffefe;");
    }

    @FXML
    private void handleMouseEnterSalir() {
        btnSalir.setStyle("-fx-background-color: #ff57cb; -fx-text-fill: #ffffff;");
    }

    @FXML
    private void handleMouseExitSalir() {
        btnSalir.setStyle("-fx-background-color: #000000; -fx-text-fill: #fffefe;");
    }
}
