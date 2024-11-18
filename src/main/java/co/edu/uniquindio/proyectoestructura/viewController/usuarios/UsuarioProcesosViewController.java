package co.edu.uniquindio.proyectoestructura.viewController.usuarios;

import co.edu.uniquindio.proyectoestructura.alerta.Alerta;
import co.edu.uniquindio.proyectoestructura.controller.AdminProcesoController;
import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import co.edu.uniquindio.proyectoestructura.util.ExportadorCSV;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UsuarioProcesosViewController {

    @FXML
    private TableColumn<Proceso, String> colId;
    @FXML
    private TableColumn<Proceso, String> colActividades;

    @FXML
    private TableColumn<Proceso, String> colNombre;

    @FXML
    private TableView<Proceso> tablaProceso;
    @FXML
    private TextField txtProcesoABuscar;

    @FXML
    private Button btnExportar;
    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnTodosProcesos;

    private AdminProcesoController adminProcesoController = new AdminProcesoController();
    private ExportadorCSV exportadorCSV = new ExportadorCSV();


    private List<Proceso> listaProcesos = new ArrayList<>();
    private List<Proceso> listaAux = new ArrayList<>();
    Alerta alerta = new Alerta();


    public void initialize() {
        initDataBindig();
        cargarProcesosDesdeArchivo();
        adminProcesoController.cargarInicio("src/main/resources/archivosTxt/Procesos.txt");
    }

    private void initDataBindig() {
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colActividades.setCellFactory(col -> new TableCell<Proceso, String>() {
            private final ComboBox<Actividad> comboBox = new ComboBox<>();

            {

                comboBox.setOnShowing(event -> {
                    Proceso proceso = getTableView().getItems().get(getIndex());
                    if (proceso != null && proceso.getListaActividades() != null) {
                        comboBox.getItems().clear();
                        comboBox.getItems().addAll(new ArrayList<>(proceso.getListaActividades()));
                    }
                });

            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getIndex() >= getTableView().getItems().size()) {
                    setGraphic(null);
                } else {

                    Proceso proceso = getTableView().getItems().get(getIndex());
                    if (proceso != null && proceso.getListaActividades() != null) {
                        comboBox.getItems().clear();
                        comboBox.getItems().addAll(new ArrayList<>(proceso.getListaActividades())); // Convertir cola a lista y cargar tareas
                    }
                    setGraphic(comboBox);
                }
            }
        });
    }

    private void cargarProcesosDesdeArchivo() {
        String rutaArchivo = "src/main/resources/archivosTxt/Procesos.txt";
        listaProcesos.clear();
        Proceso[] procesosArray = adminProcesoController.leerTxt(rutaArchivo);

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
    public void exportarProceso(Stage stage) {

        exportadorCSV.exportToCSV(listaProcesos, stage);
        alerta.mensajeExportado();
    }

    public void exportarProceso(ActionEvent event) {
        exportarProceso(new Stage());
    }

    @FXML
    public void buscarProceso() {
        String id = txtProcesoABuscar.getText().trim();

        if (id.isEmpty()) {
            alerta.mostrarAlertaError("Campo vacío", "Por favor, ingresa un ID para buscar.");
            return;
        }

        boolean procesoEncontrado = false;
        listaAux.clear();

        for (Proceso proceso : listaProcesos) {
            if (proceso.getId().equals(id)) {
                listaAux.add(new Proceso(proceso.getNombre(), proceso.getId(), proceso.getListaActividades()));
                procesoEncontrado = true;
            }
        }

        if (procesoEncontrado) {
            tablaProceso.getItems().clear();
            tablaProceso.getItems().addAll(listaAux);
            alerta.procesoEncontrado();
        } else {
            alerta.procesoNoEncontrado();
        }
    }

    @FXML
    private void todosProcesos() {
        construirProcesos();
        txtProcesoABuscar.setText("");
    }
}

