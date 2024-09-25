package co.edu.uniquindio.proyectoestructura.util;

import co.edu.uniquindio.proyectoestructura.modelo.Login;

import java.util.ResourceBundle;

public class ArchivoUtil {

    public static Login leerArchivos() {
        ResourceBundle resourceBundle;
        resourceBundle = ResourceBundle.getBundle("usuario");
        String usuario = resourceBundle.getString("usuario");
        String contrasena = resourceBundle.getString("contrasena");

        return new Login(usuario, contrasena);
    }
}
