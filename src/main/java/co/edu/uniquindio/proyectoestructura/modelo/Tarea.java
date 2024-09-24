package co.edu.uniquindio.proyectoestructura.modelo;

public class Tarea {
    private String descripcion;
    private boolean obligatoria;
    private int duracion;


    public Tarea(java.lang.String descripcion, boolean obligatoria, int duracion) {
        this.descripcion = descripcion;
        this.obligatoria = obligatoria;
        this.duracion = duracion;
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
