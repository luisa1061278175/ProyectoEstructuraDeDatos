package co.edu.uniquindio.proyectoestructura.viewController.usuarios;

import co.edu.uniquindio.proyectoestructura.controller.AdminTareaController;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class UsuarioTareasViewController {


    @FXML
    private TableColumn<Tarea, String> colTiempo;

    @FXML
    private TableColumn<Tarea, String> colDescripcion;


    @FXML
    private TableColumn<Tarea, String> colNombre;

    @FXML
    private TableColumn<Tarea, String> colObligatoria;

    @FXML
    private TextField txtBuscarTarea;

    @FXML
    private TableView<Tarea> tablaTareas;

    AdminTareaController adminTareaController = new AdminTareaController();
    List<Tarea> listaTareas = new ArrayList<>();

    public void initialize() {
        initDataBinding();
        cargarTareasDesdeArchivo();
    }

    private void initDataBinding() {

        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colDescripcion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescripcion()));
        colObligatoria.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().isObligatoria() + ""));
        colTiempo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDuracion() + ""));
    }


    private void cargarTareasDesdeArchivo() {
        String rutaArchivo = "src/main/resources/archivosTxt/Tareas.txt";
        listaTareas.clear();

        Tarea[] tareasArray = adminTareaController.cargarTareaArchivo(rutaArchivo);

        if (tareasArray != null) {
            for (Tarea tarea : tareasArray) {
                listaTareas.add(tarea);
            }
        }
        construirTarea();

    }

    @FXML
    public void buscarTarea() {
        String nombre = txtBuscarTarea.getText();
        Tarea tarea = adminTareaController.buscarTarea(nombre);
        listaTareas.clear();
        listaTareas.add(tarea);
    }

    private void construirTarea() {
        tablaTareas.getItems().clear();
        tablaTareas.getItems().addAll(listaTareas);
    }

    public void exportarProceso(ActionEvent event) {
    }
}
