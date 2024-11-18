package co.edu.uniquindio.proyectoestructura.controller;

import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtil;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtilProcesos;

public class AdminProcesoController {
    ListaEnlazadaProceso listaEnlazadaProceso = new ListaEnlazadaProceso();
    ArchivoUtilProcesos archivoUtilProcesos = new ArchivoUtilProcesos();

    public void guardarProceso(Proceso proceso) {
        listaEnlazadaProceso.agregarProceso(proceso);

    }

    public void eliminarProceso(String id) {
        listaEnlazadaProceso.eliminarProceso(id);

    }

    public void modificarProceso(String id, String nuevoNombre) {
        listaEnlazadaProceso.modificarProceso(id, nuevoNombre);
    }

    /*

     * METODOS PARA TXT
     *
     *
     * */

    public void modificarTxt(String ruta, String id, String linea) {
        archivoUtilProcesos.modificarTxt(ruta, id, linea);
    }

    public void guardarTxt(String id, String nombre) {
        archivoUtilProcesos.agregarProcesoAlArchivo(id, nombre);
    }

    public void eliminarTxt(String id) {
        archivoUtilProcesos.eliminarProcesoDeArchivo(id);
    }

    public void cargarInicio(String nombreArchivo) {
        archivoUtilProcesos.cargarDesdeArchivo(nombreArchivo);
    }

    public void agregarActividadAProceso(String id, Actividad actividad) {
        listaEnlazadaProceso.agregarActividadAProceso(id, actividad);

    }

    public void guardarActividadEnProcesoTxt(String idProceso, Actividad nuevaActividad) {
        archivoUtilProcesos.guardarActividadEnProcesoTxt(idProceso, nuevaActividad);
    }

    public  Proceso[] leerTxt(String ruta){
        return archivoUtilProcesos.leerTxt(ruta);
    }


}
