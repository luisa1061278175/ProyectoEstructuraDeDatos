package co.edu.uniquindio.proyectoestructura.viewController.administradores;

import co.edu.uniquindio.proyectoestructura.alerta.Alerta;
import co.edu.uniquindio.proyectoestructura.controller.GestionDeUsuariosController;
import co.edu.uniquindio.proyectoestructura.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GestionDeUsuariosViewController {
    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;
    @FXML
    private Button btnBuscar;

    @FXML
    private TextField txtContrasenia;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdABuscar;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;
    Alerta alerta= new Alerta();
    GestionDeUsuariosController gestionDeUsuariosController= new GestionDeUsuariosController();

    void limpiar(){
        txtContrasenia.setText("");
        txtEmail.setText("");
        txtNombre.setText("");
        txtIdentificacion.setText("");
    }

    @FXML
    void crearUsuario(ActionEvent event) {
        String nombre= txtNombre.getText();
        String id= txtIdentificacion.getText();
        String email= txtEmail.getText();
        String contrasenia= txtContrasenia.getText();
        alerta.mensajeCreado();

        gestionDeUsuariosController.guardarTxt(id, nombre,email,contrasenia);
        gestionDeUsuariosController.guardarUsuario(new Usuario(id, nombre,email,contrasenia));
        limpiar();
    }

    @FXML
    void eliminarUsuario(ActionEvent event) {
        String id= txtIdentificacion.getText();

        gestionDeUsuariosController.eliminarUsuario(id);
        gestionDeUsuariosController.eliminarTxt(id);
        alerta.mensajeEliminado();
        limpiar();

    }

    @FXML
    void modificarUsuario(ActionEvent event) {

        String nombre= txtNombre.getText();
        String id= txtIdentificacion.getText();
        String email= txtEmail.getText();
        String contrasenia= txtContrasenia.getText();
        alerta.mensajeModificado();

        gestionDeUsuariosController.modificarUsuario(id, nombre,email,contrasenia);
        gestionDeUsuariosController.modificarTxt(id, nombre,email,contrasenia);

    }

    @FXML
    void buscarUsuario(){
        String id= txtIdentificacion.getText();
        Usuario usuario= gestionDeUsuariosController.buscarUsuario(id);

        txtNombre.setText(usuario.getNombre());
        txtIdentificacion.setText(usuario.getId());
        txtEmail.setText(usuario.getEmail());
        txtContrasenia.setText(usuario.getContrasenia());

    }

    public void exportarProceso(ActionEvent event) {
    }
}


