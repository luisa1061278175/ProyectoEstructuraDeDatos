package co.edu.uniquindio.proyectoestructura.viewController;

import co.edu.uniquindio.proyectoestructura.controller.AdminActividadController;
import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
    private ComboBox<String> jComboObligatoria;

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

    private List<Proceso> listaProcesos = new ArrayList<>();
    private List<Actividad> listaActividades = new ArrayList<>();
    private Proceso procesoSeleccionado;
    private Queue<String> colaAuxProcesos = new LinkedList<>();

    AdminActividadController adminActividadController = new AdminActividadController();

    private static final String RUTA_ARCHIVO_ACTIVIDADES = "src/main/resources/archivosTxt/actividades.txt";

    @FXML
    public void initialize() {
        initDataBinding();
        cargarProcesosDesdeArchivo();

        jComboObligatoria.getItems().addAll("No", "Si");
    }

    private void initDataBinding() {
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));

        colTareaAsignada.setCellFactory(col -> new TableCell<Proceso, String>() {
            private final ComboBox<String> comboBox = new ComboBox<>();

            {
                comboBox.getItems().addAll("Opción 1", "Opción 2", "Opción 3");

                comboBox.setOnAction(event -> {
                    Proceso proceso = getTableView().getItems().get(getIndex());
                    procesoSeleccionado = proceso;  // Actualizar la selección actual
                    colaAuxProcesos.offer(comboBox.getValue());
                    System.out.println("proceso seleccionado: " + colaAuxProcesos);
                    procesoSeleccionado.setListaActividades(colaAuxProcesos); // Suponiendo que `Proceso` tiene el método `setTareaAsignada`
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    comboBox.setValue(item);
                    setGraphic(comboBox);
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

    private void construirProcesos() {
        tablaProceso.getItems().clear();
        tablaProceso.getItems().addAll(listaProcesos);
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

        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        listaActividades.add(new Actividad(nombre, descripcion, isObligatoria(), null));

        Queue<Actividad> listaAux = new LinkedList<>();
        listaAux.add(new Actividad(nombre, descripcion, isObligatoria(), null));

        adminActividadController.agregarTxt(listaAux);

        System.out.println("Lista Actividades" + listaActividades);

        limpiarCampos();
    }

    @FXML
    void eliminarActividad(ActionEvent event) {
        String nombre = txtNombre.getText();

        adminActividadController.eliminar(nombre);
        adminActividadController.eliminarTxt(nombre);

        cargarProcesosDesdeArchivo();
        construirProcesos();


    }

    @FXML
    void modificarActividad(ActionEvent event) {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        adminActividadController.modificar(nombre, descripcion, isObligatoria());
        adminActividadController.modificar(nombre, descripcion, isObligatoria());
        cargarProcesosDesdeArchivo();
        construirProcesos();
    }

    @FXML
    void refrescar(ActionEvent event) {


    }
}
