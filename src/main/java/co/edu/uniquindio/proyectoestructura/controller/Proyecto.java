package co.edu.uniquindio.proyectoestructura.controller;

import co.edu.uniquindio.proyectoestructura.modelo.Login;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtil;
import javafx.scene.control.Alert;

public class Proyecto {

    public boolean validarUsuarioProperties(String usuario, String contrasena){
        Login datosArchivo = ArchivoUtil.leerArchivos();

        if( datosArchivo.getUsername().equals(usuario) && datosArchivo.getContrasena().equals(contrasena) ){
            return true;
        }else {
            return false;
        }}

    //mensaje de error
    public void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
