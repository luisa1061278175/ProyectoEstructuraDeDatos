package co.edu.uniquindio.proyectoestructura.viewController.administradores;

import co.edu.uniquindio.proyectoestructura.grafo.GrafoProcesosApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminTabViewController {
    @FXML
    private Button mostrarGraficoBtn;

    @FXML
    public void initialize() {
    }
    @FXML
    public void mostrarGraficas(){
        GrafoProcesosApp grafoApp = new GrafoProcesosApp();
        grafoApp.start(new Stage());
    }
}
