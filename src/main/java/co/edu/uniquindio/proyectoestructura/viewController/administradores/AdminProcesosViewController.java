package co.edu.uniquindio.proyectoestructura.viewController.administradores;

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

public class AdminProcesosViewController {
    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Proceso, String> colDescripcion;

    @FXML
    private TableColumn<Proceso, String> colId;
    @FXML
    private TableColumn<Proceso, String> colActividades;

    @FXML
    private TableColumn<Proceso, String> colNombre;

    @FXML
    private TableView<Proceso> tablaProceso;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    private AdminProcesoController adminProcesoController = new AdminProcesoController();
    private ListaEnlazadaProceso listaEnlazadaProceso = new ListaEnlazadaProceso();
    private ExportadorCSV exportadorCSV = new ExportadorCSV();
    Alerta alerta = new Alerta();
    private Proceso procesoSeleccionado;

    private List<Proceso> listaProcesos = new ArrayList<>();


    @FXML
    public void initialize() {
        initDataBindig();
        cargarProcesosDesdeArchivo();
        listenerSelection();
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
                    // Obtener la actividad asociada a esta fila
                    Proceso proceso = getTableView().getItems().get(getIndex());
                    if (proceso != null && proceso.getListaActividades() != null) {
                        comboBox.getItems().clear();
                        comboBox.getItems().addAll(new ArrayList<>(proceso.getListaActividades())); // Convertir cola a lista y cargar tareas
                    }
                    setGraphic(comboBox); // Establecer el ComboBox como elemento gráfico
                }
            }
        });
    }

    private void cargarProcesosDesdeArchivo() {
        String rutaArchivo = "src/main/resources/archivosTxt/Procesos.txt";
        listaProcesos.clear();
        Proceso[] procesosArray = ListaEnlazadaProceso.leerTxt(rutaArchivo);

        if (procesosArray != null) {
            for (Proceso proceso : procesosArray) {
                listaProcesos.add(proceso);
            }

        }
        construirProcesos();
        System.out.println("Procesos cargados desde archivo: " + listaProcesos);
    }

    ;

    private void construirProcesos() {
        tablaProceso.getItems().clear();
        tablaProceso.getItems().addAll(listaProcesos);
    }

    private void listenerSelection() {
        tablaProceso.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            procesoSeleccionado = newSelection;
            if (procesoSeleccionado != null) {
                txtId.setText(procesoSeleccionado.getId());
                txtNombre.setText(procesoSeleccionado.getNombre());
            }
        });
    }

    public void limpiarCampos() {
        txtNombre.clear();
        txtId.clear();
        procesoSeleccionado = null;
    }

    @FXML
    void modificarProceso(ActionEvent event) {
        if (procesoSeleccionado != null) {
            String nombre = txtNombre.getText();
            String id = txtId.getText();

            adminProcesoController.modificarProceso(id, nombre);

            procesoSeleccionado.setId(id);
            procesoSeleccionado.setNombre(nombre);

            String nuevaLinea = procesoSeleccionado.getId() + ";" + procesoSeleccionado.getNombre();
            adminProcesoController.modificarTxt("src/main/resources/archivosTxt/Procesos.txt", procesoSeleccionado.getId(), nuevaLinea);
            System.out.println("Proceso modificado: " + procesoSeleccionado);

            construirProcesos();
            cargarProcesosDesdeArchivo();
            limpiarCampos();
            alerta.mensajeModificado();
        }
    }

    @FXML
    void crearProceso(ActionEvent event) {
        String nombre = txtNombre.getText();
        String id = txtId.getText();

        Proceso nuevoProceso = new Proceso(nombre, id, null);

        adminProcesoController.guardarProceso(nuevoProceso);
        adminProcesoController.guardarTxt(id, nombre);
        construirProcesos();
        cargarProcesosDesdeArchivo();
        limpiarCampos();
        alerta.mensajeCreado();
    }

    @FXML
    void eliminarProceso(ActionEvent event) {
        if (procesoSeleccionado != null) {
            String id = procesoSeleccionado.getId();
            adminProcesoController.eliminarProceso(id);
            adminProcesoController.eliminarTxt(id);

            construirProcesos();
            cargarProcesosDesdeArchivo();
            limpiarCampos();
            alerta.mensajeEliminado();
        }
    }

    public void exportarProceso(Stage stage) {

        exportadorCSV.exportToCSV(listaProcesos, stage);
    }


}
