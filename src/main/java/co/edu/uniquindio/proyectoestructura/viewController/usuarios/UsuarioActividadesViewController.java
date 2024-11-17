package co.edu.uniquindio.proyectoestructura.viewController.usuarios;

import co.edu.uniquindio.proyectoestructura.controller.AdminActividadController;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class UsuarioActividadesViewController {

    @FXML
    private Button btnBuscarActividades;

    @FXML
    private Button btnExportar;

    @FXML
    private TableColumn<Actividad, String> colDescripcion;

    @FXML
    private TableColumn<Actividad, String> colNombre;

    @FXML
    private TableColumn<Actividad, String> colObligatoria;

    @FXML
    private TableColumn<Actividad, String> colTareas;

    @FXML
    private TableView<Actividad> tablaActividad;

    @FXML
    private TextField txtBuscarActividades;

    private List<Actividad> listaActividades = new ArrayList<>();

    private final AdminActividadController adminActividadController = new AdminActividadController();

    @FXML
    public void initialize() {
        initDataBinding();
        cargarActividadesDesdeArchivo();
    }

    /**
     * Configura las columnas de la tabla y especifica cómo mostrar los datos.
     */
    private void initDataBinding() {
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colDescripcion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescripcion()));
        colObligatoria.setCellValueFactory(cell -> new SimpleStringProperty(Boolean.toString(cell.getValue().isEsObligatoria())));
        colTareas.setCellFactory(col -> new TableCell<Actividad, String>() {
            private final ComboBox<Tarea> comboBox = new ComboBox<>();

            {
                comboBox.setConverter(new StringConverter<>() {
                    @Override
                    public String toString(Tarea tarea) {
                        return tarea != null ? tarea.getNombre() : "";
                    }

                    @Override
                    public Tarea fromString(String string) {

                        return null;
                    }
                });
                comboBox.setOnShowing(event -> {
                    Actividad actividad = getTableView().getItems().get(getIndex());
                    if (actividad != null && actividad.getTareas() != null) {
                        comboBox.getItems().clear();
                        comboBox.getItems().addAll(new ArrayList<>(actividad.getTareas()));
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
                    Actividad actividad = getTableView().getItems().get(getIndex());
                    if (actividad != null && actividad.getTareas() != null) {
                        comboBox.getItems().clear();
                        comboBox.getItems().addAll(new ArrayList<>(actividad.getTareas())); // Convertir cola a lista y cargar tareas
                    }
                    setGraphic(comboBox); // Establecer el ComboBox como elemento gráfico
                }
            }
        });
    }


    public void cargarActividadesDesdeArchivo() {
        String rutaArchivo = "src/main/resources/archivosTxt/Actividades.txt";
        listaActividades.clear();
        Actividad[] actividadArray = adminActividadController.leerArchivo(rutaArchivo);

        if (actividadArray != null) {
            for (Actividad actividad : actividadArray) {
                listaActividades.add(actividad);
            }
        }
        construirActividades();
    }

    private void construirActividades() {
        tablaActividad.getItems().clear();
        tablaActividad.getItems().addAll(listaActividades);
    }

    @FXML
    void buscarActividades(ActionEvent event) {
        // Implementar búsqueda de actividades
    }

    @FXML
    void exportarProceso(ActionEvent event) {
        // Implementar exportación de datos
    }
}
