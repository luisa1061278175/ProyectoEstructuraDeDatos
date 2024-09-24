package co.edu.uniquindio.proyectoestructura.modelo;

import java.util.Date;

public class Notificacion {

    private String mensaje ;
    private Date fechaRecordatorio;


    public Notificacion(String mensaje, Date fechaRecordatorio) {
        this.mensaje = mensaje;
        this.fechaRecordatorio = fechaRecordatorio;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public void setFechaRecordatorio(Date fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }
}
