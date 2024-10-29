package co.edu.uniquindio.proyectoestructura.viewController;

import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
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
    private ComboBox<Proceso> jComboTarea;

    @FXML
    private TableView<?> tablaProceso;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    void crearProceso(ActionEvent event) {

    }

    @FXML
    void eliminarProceso(ActionEvent event) {

    }

    @FXML
    void modificarProceso(ActionEvent event) {

    }

    private static Map<String, Proceso> procesoMap = new HashMap<>();

    public static void llenarComboBoxConNombres(ComboBox<String> comboBox, Proceso[] procesos) {
        comboBox.getItems().clear();
        procesoMap.clear();

        for (Proceso proceso : procesos) {
            comboBox.getItems().add(proceso.getNombre());
            procesoMap.put(proceso.getNombre(), proceso);
        }
    }


    public static Proceso obtenerProcesoSeleccionado(ComboBox<String> comboBox) {
        String nombreSeleccionado = comboBox.getSelectionModel().getSelectedItem();
        return procesoMap.get(nombreSeleccionado);
    }

}
