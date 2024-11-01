package co.edu.uniquindio.proyectoestructura.viewController;

import co.edu.uniquindio.proyectoestructura.listasEnlazadas.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminActividadViewController {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<?, ?> colDescripcion;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private ComboBox<String> jComboTarea;

    @FXML
    private TableView<?> tablaProceso;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    public void initialize() {
        cargarProcesosDesdeArchivo();
        llenarComboBoxConNombres(jComboTarea, listaProcesos);

        jComboTarea.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                onGuardarSeleccion();
            }
        });
    }

    private static Map<String, Proceso> procesoMap = new HashMap<>();
    private List<Proceso> listaProcesos = new ArrayList<>();


    @FXML
    void crearProceso(ActionEvent event) {

    }

    @FXML
    void eliminarProceso(ActionEvent event) {
        // Implementación del método para eliminar un proceso
    }

    @FXML
    void modificarProceso(ActionEvent event) {
        // Implementación del método para modificar un proceso
    }

    public static Proceso obtenerProcesoSeleccionado(ComboBox<String> comboBox) {
        String nombreSeleccionado = comboBox.getSelectionModel().getSelectedItem();
        return procesoMap.get(nombreSeleccionado);
    }

    public void llenarComboBoxConNombres(ComboBox<String> comboBox, List<Proceso> procesos) {
        comboBox.getItems().clear();

        procesoMap.clear();

        for (Proceso proceso : procesos) {
            comboBox.getItems().add(proceso.getNombre());
            procesoMap.put(proceso.getNombre(), proceso);
        }

        System.out.println("Elementos en el ComboBox: " + comboBox.getItems().size()); // Imprime la cantidad de elementos cargados
    }

    private void onGuardarSeleccion() {
        String nombreProcesoSeleccionado = jComboTarea.getSelectionModel().getSelectedItem();
        Proceso procesoSeleccionado = procesoMap.get(nombreProcesoSeleccionado);

        if (procesoSeleccionado != null) {
            System.out.println("Proceso seleccionado: " + procesoSeleccionado);

        } else {
            System.out.println("No hay un proceso seleccionado.");
        }


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
        System.out.println("estamos cargando procesos desde archivo "+listaProcesos);
    }

    @FXML
    public void llenarConNombres(ActionEvent event) throws IOException {
        System.out.println("Estamos en el evento del comboBox");
        llenarComboBoxConNombres(jComboTarea, listaProcesos);
    }

}
