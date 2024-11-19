package co.edu.uniquindio.proyectoestructura.grafo;

import co.edu.uniquindio.proyectoestructura.controller.AdminActividadController;
import co.edu.uniquindio.proyectoestructura.controller.AdminProcesoController;
import co.edu.uniquindio.proyectoestructura.controller.AdminTareaController;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class GrafoProcesosApp extends Application {
    @Override
    public void start(Stage stage) {
        AnchorPane root = new AnchorPane();

        cargarTareasDesdeArchivo();
        cargarProcesosDesdeArchivo();
        cargarActividadesDesdeArchivo();

        PieChart pieChart = crearGraficoCircular();
        pieChart.setLayoutX(10);
        pieChart.setLayoutY(10);
        pieChart.setPrefWidth(300);
        pieChart.setPrefHeight(300);

        BarChart<String, Number> barChart = crearGraficoBarras();
        barChart.setLayoutX(320);
        barChart.setLayoutY(10);
        barChart.setPrefWidth(400);
        barChart.setPrefHeight(300);

        root.getChildren().addAll(pieChart, barChart);

        Pane grafoContainer = new Pane();
        grafoContainer.setLayoutX(10);
        grafoContainer.setLayoutY(320);
        grafoContainer.setPrefWidth(730);
        grafoContainer.setPrefHeight(600);

        dibujarGrafo(grafoContainer);

        root.getChildren().add(grafoContainer);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Configuración de la escena y la ventana
        Scene scene = new Scene(scrollPane, 750, 540);  // Tamaño máximo de la pantalla
        stage.setTitle("Grafo de Procesos y Estadísticas");
        stage.setScene(scene);
        stage.show();
    }




    private void dibujarGrafo(Pane root) {
        Circle entidadCentral = new Circle(200, 80, 40, Color.DEEPSKYBLUE);  // Reducir tamaño
        Text entidadText = new Text(170, 85, "Empresa");
        root.getChildren().addAll(entidadCentral, entidadText);

        double xProceso = 100;
        double yProceso = 180;

        for (Proceso proceso : procesos) {
            Circle procesoNode = new Circle(xProceso, yProceso, 30, Color.LIGHTBLUE); // Reducir tamaño
            Text procesoText = new Text(xProceso - 20, yProceso + 5, proceso.getNombre());

            Line entidadToProceso = new Line(200, 80, xProceso, yProceso);
            root.getChildren().addAll(entidadToProceso, procesoNode, procesoText);

            double xActividad = xProceso - 50;
            double yActividad = yProceso + 100;

            for (int i = 0; i < actividades.size(); i++) {
                Actividad actividad = actividades.poll();
                Circle actividadNode = new Circle(xActividad, yActividad, 20, Color.LIGHTGREEN); // Reducir tamaño
                Text actividadText = new Text(xActividad - 20, yActividad + 5, actividad.getNombre());
                root.getChildren().addAll(actividadNode, actividadText);

                if (actividad.getTareas() != null && !actividad.getTareas().isEmpty()) {
                    double yTarea = yActividad + 50;

                    for (Tarea tarea : actividad.getTareas()) {
                        Circle tareaNode = new Circle(xActividad, yTarea, 15, Color.LIGHTCORAL); // Reducir tamaño
                        Text tareaText = new Text(xActividad - 15, yTarea + 5, tarea.getNombre());

                        Line actividadToTarea = new Line(xActividad, yActividad, xActividad, yTarea);
                        root.getChildren().addAll(actividadToTarea, tareaNode, tareaText);

                        yTarea += 40;
                    }
                }

                xActividad += 120;
            }

            xProceso += 220;
        }
    }

    AdminActividadController adminActividadController = new AdminActividadController();
    AdminProcesoController adminProcesoController = new AdminProcesoController();
    AdminTareaController adminTareaController = new AdminTareaController();

    Queue<Tarea> tareas = new LinkedList<>();
    Queue<Proceso> procesos = new LinkedList<>();
    Queue<Actividad> actividades = new LinkedList<>();



    private void cargarTareasDesdeArchivo() {
        String rutaArchivo = "src/main/resources/archivosTxt/Tareas.txt";
        tareas.clear();
        Tarea[] tareasArray = adminTareaController.cargarTareaArchivo(rutaArchivo);

        if (tareasArray != null) {
            for (Tarea tarea : tareasArray) {
                tareas.add(tarea);
            }
        }
    }

    private void cargarProcesosDesdeArchivo() {
        String rutaArchivo = "src/main/resources/archivosTxt/Procesos.txt";
        procesos.clear();
        Proceso[] procesosArray = adminProcesoController.leerTxt(rutaArchivo);

        if (procesosArray != null) {
            for (Proceso proceso : procesosArray) {
                procesos.add(proceso);
            }
        }
    }

    private void cargarActividadesDesdeArchivo() {
        actividades.clear();
        String rutaArchivo = "src/main/resources/archivosTxt/Actividades.txt";
        Actividad[] actividadesArray = cargarDesdeArchivo(rutaArchivo);

        for (Actividad actividad : actividadesArray) {
            actividades.add(actividad);
            for (Proceso proceso : procesos) {
                if (proceso.getNombre().equals(actividad.getNombre())) {
                    proceso.getListaActividades().add(actividad);
                }
            }

            for (Tarea tarea : actividad.getTareas()) {
                for (Tarea t : tareas) {
                    if (t.getNombre().equals(tarea.getNombre())) {
                        tarea.setDescripcion(t.getDescripcion());
                        tarea.setDuracion(t.getDuracion());
                        tarea.setObligatoria(t.isObligatoria());
                    }
                }
            }
        }
    }

    public static Actividad[] cargarDesdeArchivo(String nombreArchivo) {
        LinkedList<Actividad> actividades = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 4) {
                    String nombre = datos[0].trim();
                    String descripcion = datos[1].trim();
                    boolean esObligatoria = Boolean.parseBoolean(datos[2].trim());

                    Queue<Tarea> listaTareas = new LinkedList<>();
                    if (datos.length > 3) {
                        String[] nombresTareas = datos[3].split(",");
                        for (String nombreTarea : nombresTareas) {
                            Tarea tarea = new Tarea(nombreTarea.trim(), "", false, 0);
                            listaTareas.add(tarea);
                        }
                    }
                    Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, listaTareas);
                    actividades.add(actividad);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return actividades.toArray(new Actividad[0]);
    }


    private PieChart crearGraficoCircular() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Procesos", procesos.size()),
                new PieChart.Data("Actividades", actividades.size()),
                new PieChart.Data("Tareas", tareas.size())
        );

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Distribución de Datos");
        return pieChart;
    }
    private BarChart<String, Number> crearGraficoBarras() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Categoría");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Cantidad");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Estadísticas de Datos");

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Datos cargados");
        dataSeries.getData().add(new XYChart.Data<>("Procesos", procesos.size()));
        dataSeries.getData().add(new XYChart.Data<>("Actividades", actividades.size()));
        dataSeries.getData().add(new XYChart.Data<>("Tareas", tareas.size()));

        barChart.getData().add(dataSeries);
        return barChart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
