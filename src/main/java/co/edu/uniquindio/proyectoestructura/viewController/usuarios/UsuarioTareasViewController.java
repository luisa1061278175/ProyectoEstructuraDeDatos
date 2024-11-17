package co.edu.uniquindio.proyectoestructura.viewController.usuarios;

import co.edu.uniquindio.proyectoestructura.alerta.Alerta;
import co.edu.uniquindio.proyectoestructura.controller.AdminTareaController;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import co.edu.uniquindio.proyectoestructura.util.ExportadorCSV;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    @FXML
    private Button btnTodosProcesos;
    @FXML
    private Button btnHacerTarea;

    AdminTareaController adminTareaController = new AdminTareaController();
    ExportadorCSV exportadorCSV = new ExportadorCSV();
    Alerta alerta = new Alerta();
    List<Tarea> listaTareas = new ArrayList<>();
    List<Tarea> listaAux = new ArrayList<>();

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
        String nombre = txtBuscarTarea.getText().trim();

        if (nombre.isEmpty()) {
            alerta.mostrarAlertaError("Campo vacío", "Por favor, ingresa un ID para buscar.");
            return;
        }

        boolean tareaEncontrada = false;
        listaAux.clear();

        for (Tarea tarea : listaTareas) {
            if (tarea.getNombre().equals(nombre)) {
                listaAux.add(new Tarea(tarea.getNombre(), tarea.getDescripcion(), tarea.isObligatoria(), tarea.getDuracion()));
                tareaEncontrada = true;
            }
        }

        if (tareaEncontrada) {
            tablaTareas.getItems().clear();
            tablaTareas.getItems().addAll(listaAux);
            alerta.procesoEncontrado();
        } else {
            alerta.procesoNoEncontrado();
        }
    }

    private void construirTarea() {
        tablaTareas.getItems().clear();
        tablaTareas.getItems().addAll(listaTareas);
        txtBuscarTarea.setText("");
    }

    public void exportarProceso(ActionEvent event) {
        exportadorCSV.exportarTarea(listaTareas, new Stage());
        alerta.mensajeExportado();
    }

    @FXML
    private void todosProcesos() {
        construirTarea();
        txtBuscarTarea.setText("");
    }
    @FXML
    public void hacerTarea(){

    }
}
