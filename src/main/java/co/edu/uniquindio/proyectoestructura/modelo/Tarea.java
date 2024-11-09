package co.edu.uniquindio.proyectoestructura.modelo;

public class Tarea {
    private String nombre;
    private String descripcion;
    private boolean obligatoria;
    private int duracion;

    public Tarea(String nombre, String descripcion, boolean obligatoria, int duracion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatoria = obligatoria;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}