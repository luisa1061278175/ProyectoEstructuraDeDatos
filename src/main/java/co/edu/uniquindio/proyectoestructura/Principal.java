package co.edu.uniquindio.proyectoestructura;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Principal extends Application {

    public static void cargarAdmin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("/co/edu/uniquindio/proyectoestructura/inicioSesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bienvenido Administrador");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        cargarAdmin(stage);
    }

    public static void main(String[] args) {
        launch();  // Esto inicializa el entorno JavaFX y llama al método start()
    }
}
