package co.edu.uniquindio.proyectoestructura.viewController;

import co.edu.uniquindio.proyectoestructura.controller.AdminProcesoController;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminProcesosViewController {
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
    private TableView<?> tablaProceso;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    AdminProcesoController adminProcesoController= new AdminProcesoController();

    @FXML
    void crearProceso(ActionEvent event) {

        String nombre= txtNombre.getText();
        String id= txtId.getText();

        adminProcesoController.guardarProceso(new Proceso(nombre,id));
        System.out.println("Se ha seleccionado el boton para crear un proceso ");

    }

    @FXML
    void eliminarProceso(ActionEvent event) {

    }

    @FXML
    void modificarProceso(ActionEvent event) {

    }


}
