package co.edu.uniquindio.proyectoestructura.viewController;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminTareasViewController {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Actividad, String> colActividades;

    @FXML
    private TableColumn<Actividad, String> colDescripcion;

    @FXML
    private TableColumn<Actividad, String>colDuración;

    @FXML
    private TableColumn<Actividad, String> colNombre;

    @FXML
    private TableColumn<Actividad, String> colObligatoria;

    @FXML
    private ComboBox<String> jcomboObligatoria;

    @FXML
    private TableView<Actividad> tablaActividad;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtDuracion;

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
}
