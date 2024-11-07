package co.edu.uniquindio.proyectoestructura.viewController;

import co.edu.uniquindio.proyectoestructura.controller.AdminProcesoController;
import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableColumn<Proceso, String> colNombre;

    @FXML
    private TableView<Proceso> tablaProceso;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    private AdminProcesoController adminProcesoController = new AdminProcesoController();
    private ListaEnlazadaProceso listaEnlazadaProceso = new ListaEnlazadaProceso();
    private Proceso procesoSeleccionado;

    //estamos usando la lista para poder cargar los datos en la tabla
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
    }

    //ESTE METODO SE ENCARGA DE CARGAR PROCESOS PERO EN LA INTERFAZ

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

            // Recarga la lista en la vista
            cargarProcesosDesdeArchivo();
            limpiarCampos();
        } else {
            System.out.println("Seleccione un proceso para modificar.");
        }
    }

    @FXML
    void crearProceso(ActionEvent event) {
        String nombre = txtNombre.getText();
        String id = txtId.getText();

        Proceso nuevoProceso = new Proceso(nombre, id, null);
        adminProcesoController.guardarProceso(nuevoProceso);
        adminProcesoController.guardarTxt();
        cargarProcesosDesdeArchivo();
        limpiarCampos();
    }

    @FXML
    void eliminarProceso(ActionEvent event) {
        if (procesoSeleccionado != null) {
            String id = procesoSeleccionado.getId();
            adminProcesoController.eliminarProceso(id);
            adminProcesoController.eliminarTxt(id);

            cargarProcesosDesdeArchivo();
            limpiarCampos();
            System.out.println("Proceso eliminado: " + id);
        } else {
            System.out.println("Seleccione un proceso para eliminar.");
        }
    }


}
