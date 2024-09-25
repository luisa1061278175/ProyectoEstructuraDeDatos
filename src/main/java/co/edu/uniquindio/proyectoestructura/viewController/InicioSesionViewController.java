package co.edu.uniquindio.proyectoestructura.viewController;

import co.edu.uniquindio.proyectoestructura.controller.Proyecto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionViewController {
    Proyecto proyecto= new Proyecto();

    @FXML
    private Button btnInicioSesion;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtUsuario;

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
        String id = txtUsuario.getText();               // Obtén el ID del campo de texto
        String contrasenia = txtContrasenia.getText();  // Obtén la contraseña del campo de contraseña


        boolean respuesta = proyecto.validarUsuarioProperties(id, contrasenia);

        if (respuesta) {
            Stage stage = new Stage();

            cargarAdmin(stage);
        } else {
            System.out.println("Credenciales incorrectas");
            proyecto.mostrarMensajeError("Credenciales incorrectas");


        }
    }

    public void cargarAdmin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectoestructura/principalAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bienvenido Administrador");
        stage.setScene(scene);
        stage.show();
    }
}
