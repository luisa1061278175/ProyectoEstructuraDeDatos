package co.edu.uniquindio.proyectoestructura.viewController;

import co.edu.uniquindio.proyectoestructura.listasEnlazadas.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminActividadViewController {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnRefrescar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Proceso, String> colDescripcion;
    @FXML
    private TableColumn<Proceso, String> colTareaAsignada;

    @FXML
    private TableColumn<Proceso, String> colId;

    @FXML
    private TableColumn<Proceso, String> colNombre;

    @FXML
    private TableView<Proceso> tablaProceso;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    private List<Proceso> listaProcesos = new ArrayList<>();

    @FXML
    public void initialize() {
        initDataBindig();
        cargarProcesosDesdeArchivo();

    }

    private void initDataBindig() {
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));

    }

    private void cargarProcesosDesdeArchivo() {
        String rutaArchivo = "src/main/resources/archivosTxt/Procesos.txt";
        listaProcesos.clear();
        Proceso[] procesosArray = ListaEnlazadaProceso.leerArchivo(rutaArchivo);

        if (procesosArray != null) {
            for (Proceso proceso : procesosArray) {
                listaProcesos.add(proceso);
            }
        }
        construirProcesos();
        System.out.println("Procesos cargados desde archivo: " + listaProcesos);
    }

    private void construirProcesos() {
        tablaProceso.getItems().clear();
        tablaProceso.getItems().addAll(listaProcesos);
    }

    @FXML
    void crearProceso(ActionEvent event) {

    }

    @FXML
    void eliminarProceso(ActionEvent event) {

    }

    @FXML
    void modificarProceso(ActionEvent event) {

    }
}
