package co.edu.uniquindio.proyectoestructura.viewController;

import co.edu.uniquindio.proyectoestructura.controller.AdminProcesoController;
import co.edu.uniquindio.proyectoestructura.listasEnlazadas.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.*;
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
    private List<Proceso> listaProcesos = new ArrayList<>();
    AdminProcesoController adminProcesoController= new AdminProcesoController();
    private Proceso procesoSeleccionado;



    @FXML
    public void initialize() {
        initDataBindig();
        cargarProcesosDesdeArchivo();
        listenerSelection();
    }

    private void initDataBindig() {
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
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
            mostrarInformacion(procesoSeleccionado);
        });
    }

    private void mostrarInformacion(Proceso procesoSeleccionado) {
        if (procesoSeleccionado != null) {
            txtId.setText(procesoSeleccionado.getId());
            txtNombre.setText(procesoSeleccionado.getNombre());
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtId.setText("");
    }


    public void borrarLineaEnArchivo(String rutaArchivo, String id) {
        File archivo = new File(rutaArchivo);
        List<String> lineas = new ArrayList<>();

        // Leer el archivo y almacenar las líneas que no coinciden con el ID
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.startsWith(id + ";")) { // Comprueba si la línea comienza con el ID
                    lineas.add(linea); // Guarda solo las líneas que no coinciden con el ID
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Escribir las líneas actualizadas de vuelta en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void modificarLineaEnArchivo(String rutaArchivo, String id, String nuevaLinea) {
        File archivo = new File(rutaArchivo);

               try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (int i=0;i<listaProcesos.size();i++) {
                String linea=listaProcesos.get(i).getNombre()+";"+listaProcesos.get(i).getId();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modificarProceso(ActionEvent event) {

        String nombre = txtNombre.getText();
        String id = txtId.getText();

        procesoSeleccionado.setId(id);
        procesoSeleccionado.setNombre(nombre);
        String nuevaLinea = procesoSeleccionado.getId() + ";" + procesoSeleccionado.getNombre();

        modificarLineaEnArchivo("src/main/resources/archivosTxt/Procesos.txt",procesoSeleccionado.getId(),nuevaLinea);
        System.out.println("proceso modificado ");

        System.out.println(listaProcesos);

        limpiarCampos();

    }

    @FXML
    void crearProceso(ActionEvent event) {

        String nombre = txtNombre.getText();
        String id = txtId.getText();

        adminProcesoController.guardarProceso(new Proceso(nombre, id));
        cargarProcesosDesdeArchivo();
        limpiarCampos();


    }

    @FXML
    void eliminarProceso(ActionEvent event) {
        cargarProcesosDesdeArchivo();
        String id = txtId.getText();
        for (int i = 0; i < listaProcesos.size(); i++) {

            if (listaProcesos.get(i).getId().equals(id)) {
                listaProcesos.remove(i);
            }
        }
        tablaProceso.getSelectionModel().clearSelection();
        borrarLineaEnArchivo("src/main/resources/archivosTxt/Procesos.txt", id);


        limpiarCampos();

    }


}
