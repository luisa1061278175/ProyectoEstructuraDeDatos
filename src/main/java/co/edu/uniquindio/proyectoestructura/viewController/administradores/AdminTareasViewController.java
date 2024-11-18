package co.edu.uniquindio.proyectoestructura.viewController.administradores;

import co.edu.uniquindio.proyectoestructura.alerta.Alerta;
import co.edu.uniquindio.proyectoestructura.controller.AdminActividadController;
import co.edu.uniquindio.proyectoestructura.controller.AdminTareaController;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdminTareasViewController {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;
    @FXML
    private Button btnBuscarTarea;

    @FXML
    private TableColumn<Actividad, String> colTareas;

    @FXML
    private TableColumn<Actividad, String> colDescripcion;


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
    private TextField txtTareaABuscar;

    private Actividad actividadSeleccionada;
    private Queue<String> colaAuxActividades = new LinkedList<>();
    Alerta alerta= new Alerta();

    AdminTareaController adminTareaController = new AdminTareaController();
    AdminActividadController adminActividadController = new AdminActividadController();
    Tarea tareaSeleccionada;
    List<Tarea> listaTareas = new ArrayList<>();
    private List<Actividad> listaActividades = new ArrayList<>();
    private boolean esObligatoria;


    public void initialize() {

        initDataBinding();
        cargarActividadesDesdeArchivo();
        cargarTareasDesdeArchivo();

        jcomboObligatoria.getItems().addAll("No", "Si");


        jcomboObligatoria.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Valor seleccionado en ComboBox: " + newValue);
                esObligatoria = newValue.equals("Si");
            }
        });

        // Detecta el proceso seleccionado en la tabla y lo asigna a `actividadSeleccionada`
        tablaActividad.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                actividadSeleccionada = newSelection;
                System.out.println("Actividad seleccionada: " + actividadSeleccionada.getNombre());
            }
        });
    }


    private void initDataBinding() {

        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colDescripcion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescripcion()));
        colObligatoria.setCellValueFactory(cell ->
                new SimpleBooleanProperty(cell.getValue().isEsObligatoria()).asString()
        );

        colTareas.setCellFactory(col -> new TableCell<Actividad, String>() {
            private final ComboBox<Tarea> comboBox = new ComboBox<>();

            {
                comboBox.setConverter(new StringConverter<>() {
                    @Override
                    public String toString(Tarea tarea) {
                        return tarea != null ? tarea.getNombre() : ""; // Mostrar solo el nombre de la tarea
                    }

                    @Override
                    public Tarea fromString(String string) {
                        // Este método no es necesario para esta implementación, pero debe estar definido
                        return null;
                    }
                });
                // Listener para cargar las tareas al abrir el ComboBox
                comboBox.setOnShowing(event -> {
                    Actividad actividad = getTableView().getItems().get(getIndex());
                    if (actividad != null && actividad.getTareas() != null) {
                        comboBox.getItems().clear();
                        comboBox.getItems().addAll(new ArrayList<>(actividad.getTareas()));
                    }
                });

                // Listener para manejar la selección de una atarea
                comboBox.setOnAction(event -> {
                    Tarea tareaSeleccionada = comboBox.getValue();

                    if (tareaSeleccionada != null) {

                        Tarea tarea = adminTareaController.buscarTarea(tareaSeleccionada.getNombre());
                        if (tarea != null) {
                            txtNombre.setText(tarea.getNombre());
                            txtDescripcion.setText(tarea.getDescripcion());
                            txtDuracion.setText(String.valueOf(tarea.getDuracion()));
                        } else {
                            System.out.println("Tarea no encontrada");
                        }
                    } else {
                        System.out.println("Tarea seleccionada nula");
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


    private void cargarActividadesDesdeArchivo() {
        String rutaArchivo = "src/main/resources/archivosTxt/Actividades.txt";
        listaActividades.clear();
        Actividad[] actividadArray = adminActividadController.leerArchivo(rutaArchivo);

        if (actividadArray != null) {
            for (Actividad actividad : actividadArray) {
                listaActividades.add(actividad);
            }
        }
        construirActividad();

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


    }


    private void construirActividad() {
        tablaActividad.getItems().clear();
        tablaActividad.getItems().addAll(listaActividades);
    }


    public void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtDuracion.setText("");
    }

    @FXML
    void crearProceso(ActionEvent event) {
        if (actividadSeleccionada == null) {
            System.out.println("Error: No se ha seleccionado ninguna actividad.");
            return;
        }

        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        int duracion = Integer.parseInt(txtDuracion.getText());

        Tarea nuevaTarea = new Tarea(nombre, descripcion, esObligatoria, duracion);
        listaTareas.add(nuevaTarea);

        adminTareaController.agregarTxt(listaTareas, "src/main/resources/archivosTxt/Tareas.txt");
        adminTareaController.guardarTareaEnActividad(actividadSeleccionada.getNombre(), nuevaTarea);
        adminTareaController.guardar(nuevaTarea);

        System.out.println("Lista de tareas: " + listaTareas);
        limpiarCampos();
    }


    @FXML
    void eliminarProceso(ActionEvent event) {
        String nombre = txtNombre.getText();

        adminTareaController.eliminar(nombre);
        adminTareaController.eliminarTxt(nombre);

        cargarActividadesDesdeArchivo();
        construirActividad();

    }

    @FXML
    void modificarProceso(ActionEvent event) {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        int duracion = Integer.parseInt(txtDuracion.getText());

        adminTareaController.modificar(nombre, descripcion, esObligatoria, duracion);
        adminTareaController.modificarTxt(nombre, descripcion, esObligatoria, duracion);
        cargarActividadesDesdeArchivo();
        construirActividad();
    }

    private void buscarTarea(String nombre){
        cargarTareasDesdeArchivo();
        System.out.println("lista tareas: "+ listaTareas);

        if (nombre.isEmpty()) {
            alerta.mostrarAlertaError("Campo vacío", "Por favor, ingresa un ID para buscar.");
            return;
        }

        boolean tareaEncontrada = false;


        for (int i = 0; i < listaTareas.size(); i++) {

            if (listaTareas.get(i).getNombre().equals(nombre)) {
               txtDescripcion.setText(listaTareas.get(i).getDescripcion());
               txtDuracion.setText(listaTareas.get(i).getDuracion()+"");
               txtNombre.setText(listaTareas.get(i).getNombre());
                tareaEncontrada = true;
            }
        }

        if (tareaEncontrada) {

            alerta.mostrarAlertaExito("Exitoso","Tarea encontrada");
        } else {
            alerta.mostrarAlertaError("No se encuentra la tarea","ingresa nuevamente la tarea");
        }


    }

    public void buscarTarea(ActionEvent event) {

       String nombre= txtTareaABuscar.getText();
        buscarTarea(nombre);

    }
}
