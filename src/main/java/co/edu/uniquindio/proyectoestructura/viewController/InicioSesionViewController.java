package co.edu.uniquindio.proyectoestructura.viewController;

import co.edu.uniquindio.proyectoestructura.controller.Proyecto;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtil;
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
    ArchivoUtil archivoUtil= new ArchivoUtil();

    @FXML
    private Button btnInicioSesion;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtUsuario;

    private static final String RUTA_ARCHIVO_USUARIO_REGISTRADO = "src/main/resources/archivosTxt/UsuariosRegistrados.txt";

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String id = txtUsuario.getText();
        String contrasenia = txtContrasenia.getText();

        boolean respuesta = proyecto.validarUsuarioProperties(id, contrasenia);

        if (respuesta) {

            cargarAdmin(stage);
        } else {
            String rutaArchivo = RUTA_ARCHIVO_USUARIO_REGISTRADO;
            System.out.println("Id "+id);
            System.out.println("Contraseña "+contrasenia);


            if (archivoUtil.verificarCredenciales(rutaArchivo, id, contrasenia)) {

                cargarUsuario(stage);
            } else {
                System.out.println("Usuario no encontrado.");
            }
        }
    }


    public void cargarAdmin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectoestructura/adminPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bienvenido Administrador");
        stage.setScene(scene);
        stage.show();
    }
    public void cargarUsuario(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectoestructura/usuarioPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bienvenido Usuario");
        stage.show();
    }
}
