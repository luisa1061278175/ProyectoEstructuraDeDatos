package co.edu.uniquindio.proyectoestructura.modelo;

public class UsuarioRegular extends Usuario {
    private String id;
    private String contrasenia;


    public UsuarioRegular(String nombre, String email, String rol, String id, String contrasenia) {
        super(nombre, email, rol);
        this.id = id;
        this.contrasenia = contrasenia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}