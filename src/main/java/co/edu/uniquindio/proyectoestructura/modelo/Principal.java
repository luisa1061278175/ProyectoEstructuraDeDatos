package co.edu.uniquindio.proyectoestructura.modelo;

import co.edu.uniquindio.proyectoestructura.util.ArchivoUtil;
import javafx.stage.Stage;

import java.io.IOException;

public class Principal {

    public boolean validarUsuarioProperties(String usuario, String contrasena){
        Login datosArchivo = ArchivoUtil.leerArchivos();

        if( datosArchivo.getUsername().equals(usuario) && datosArchivo.getContrasena().equals(contrasena) ){
            return true;
        }else {
            return false;
        }
    }



}
