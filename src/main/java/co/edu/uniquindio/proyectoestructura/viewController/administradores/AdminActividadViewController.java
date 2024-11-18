package co.edu.uniquindio.proyectoestructura.viewController.administradores;

import co.edu.uniquindio.proyectoestructura.alerta.Alerta;
import co.edu.uniquindio.proyectoestructura.controller.AdminActividadController;
import co.edu.uniquindio.proyectoestructura.controller.AdminProcesoController;
import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import co.edu.uniquindio.proyectoestructura.util.ExportadorCSV;
import co.edu.uniquindio.proyectoestructura.util.ImportadorCSV;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdminActividadViewController {

    @FXML
    private Button btnCrear;
    @FXML
    private Button btnExportar;
    @FXML
    private Button btnImportar;
    @FXML
    private Button btnCambiar;
    @FXML
    private Button btnRefrescar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnBuscarActividad;

    @FXML
    private ComboBox<String> jComboObligatoria;
    @FXML

    private ComboBox<String> JComboTareasDeActividadBuscada;
    @FXML
    private TextField txtBuscarActividad;

    @FXML
    private TextField txtAct1;

    @FXML
    private TextField txtAct2;


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
    private TextField txtNombre;

    @FXML
    private TextField txtActividadObligatoria;

    private List<Proceso> listaProcesos = new ArrayList<>();
    private List<Actividad> listaActividades = new ArrayList<>();
    private Proceso procesoSeleccionado;
    private Queue<String> colaAuxProcesos = new LinkedList<>();

    private boolean esObligatoria;
    ExportadorCSV exportadorCSV = new ExportadorCSV();
    ImportadorCSV importadorCSV = new ImportadorCSV();

    AdminActividadController adminActividadController = new AdminActividadController();
    AdminProcesoController adminProcesoController = new AdminProcesoController();

    Alerta alerta = new Alerta();

    private static final String RUTA_ARCHIVO_ACTIVIDADES = "src/main/resources/archivosTxt/actividades.txt";

    public void initialize() {
        initDataBinding();
        cargarProcesosDesdeArchivo();
        jComboObligatoria.getItems().addAll("No", "Si");

        jComboObligatoria.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Valor seleccionado en ComboBox: " + newValue);
                esObligatoria = newValue.equals("Si");
            }
        });

        // Detecta el proceso seleccionado en la tabla y lo asigna a `procesoSeleccionado`
        tablaProceso.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                procesoSeleccionado = newSelection;
                System.out.println("Proceso seleccionado: " + procesoSeleccionado.getNombre());

            }
        });
    }

    private void initDataBinding() {
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));

        colTareaAsignada.setCellFactory(col -> new TableCell<Proceso, String>() {
            private final ComboBox<Actividad> comboBox = new ComboBox<>();

            {

                comboBox.setOnShowing(event -> {
                    Proceso proceso = getTableView().getItems().get(getIndex());
                    if (proceso != null) {

                        comboBox.getItems().clear();
                        comboBox.getItems().addAll(proceso.getListaActividades());
                    }
                });

                comboBox.setOnAction(event -> {
                    String nombreactividadSeleccionada = String.valueOf(comboBox.getValue());
                    if (nombreactividadSeleccionada != null) {
                        Actividad actividad = adminActividadController.buscarActividad(nombreactividadSeleccionada);

                        txtNombre.setText(actividad.getNombre());
                        txtDescripcion.setText(actividad.getDescripcion());
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
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

    private void cargarCamposTexto() {
        txtNombre.setText(colaAuxProcesos.peek());
    }

    private void construirProcesos() {
        tablaProceso.getItems().clear();
        tablaProceso.getItems().addAll(listaProcesos);
    }

    private void construirActividad() {

    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
    }


    public boolean isObligatoria() {
        if (jComboObligatoria.getValue().equals("Si")) {
            return true;
        }
        System.out.println("Estado del JCombo" + jComboObligatoria.getValue());
        return false;

    }

    @FXML
    void crearActividad(ActionEvent event) {
        if (procesoSeleccionado == null) {
            System.out.println("Error: No se ha seleccionado ningún proceso.");
            return;
        }

        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();

        Actividad nuevaActividad = new Actividad(nombre, descripcion, isObligatoria(), null);
        listaActividades.add(nuevaActividad);

        Queue<Actividad> listaAux = new LinkedList<>();
        listaAux.add(nuevaActividad);

        adminActividadController.agregarTxt(listaAux);


        adminProcesoController.agregarActividadAProceso(procesoSeleccionado.getId(), nuevaActividad);

        adminProcesoController.guardarActividadEnProcesoTxt(procesoSeleccionado.getId(), nuevaActividad);

        alerta.mensajeCreado();
        limpiarCampos();
    }

    @FXML
    void eliminarActividad(ActionEvent event) {
        String nombre = txtNombre.getText();

        adminActividadController.eliminar(nombre);
        adminActividadController.eliminarTxt(nombre);

        cargarProcesosDesdeArchivo();
        construirProcesos();

        alerta.mensajeEliminado();

    }

    @FXML
    void modificarActividad(ActionEvent event) {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();

        adminActividadController.modificar(nombre, descripcion, isObligatoria());
        adminActividadController.modificarTxt(nombre, descripcion, isObligatoria());
        cargarProcesosDesdeArchivo();
        construirProcesos();
        alerta.mensajeModificado();
    }

    @FXML
    void refrescar(ActionEvent event) {
    }

    @FXML
    public void exportar() {
        exportadorCSV.exportarActividad(listaActividades, new Stage());
    }

    public static String seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar un archivo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {

            File archivoSeleccionado = fileChooser.getSelectedFile();

            return archivoSeleccionado.getAbsolutePath();
        } else {

            return null;
        }
    }

    @FXML
    public void importar() {

        String ruta = seleccionarArchivo();

        if (ruta != null) {
            System.out.println("Archivo seleccionado: " + ruta);

            if (ruta == null || ruta.trim().isEmpty()) {
                System.out.println("No se ingresó una ruta válida.");
                return;
            }
            String archivoDestino = "src/main/resources/archivosTxt/Procesos.txt";

            importadorCSV.importarDatos(ruta, archivoDestino);
        }


    }

    public void BuscarActividad(ActionEvent event) {

        String nombre = txtBuscarActividad.getText();

        Actividad actividad = adminActividadController.buscarActividad(nombre);

        txtNombre.setText(actividad.getNombre());
        txtDescripcion.setText(actividad.getDescripcion());
        txtActividadObligatoria.setText(actividad.isEsObligatoria() + "");
    }
    @FXML
    public void cambiarActividades() {

        String nomAct1=txtAct1.getText();
        String nomAct2=txtAct2.getText();

        //adminActividadController.intercambiarActividades(nomAct1,nomAct2,"src/main/resources/archivosTxt/Actividades.txt");
    }
    public static void mostrarActividades(Proceso proceso) {
        for (Object actividad : proceso.getListaActividades()) {
            System.out.println(actividad);
        }
    }

    public void calcularTiempo(){

    }
}

