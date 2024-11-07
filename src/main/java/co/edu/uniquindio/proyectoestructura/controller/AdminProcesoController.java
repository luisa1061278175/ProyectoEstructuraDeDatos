package co.edu.uniquindio.proyectoestructura.controller;

import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtil;

public class AdminProcesoController {
    ListaEnlazadaProceso listaEnlazadaProceso = new ListaEnlazadaProceso();
    ArchivoUtil archivoUtil = new ArchivoUtil();

    public void guardarProceso(Proceso proceso) {
        listaEnlazadaProceso.agregarProceso(proceso);

    }

    public void eliminarProceso(String id) {
        listaEnlazadaProceso.eliminarProceso(id);

    }

    public void modificarProceso(String id, String nuevoNombre) {
        listaEnlazadaProceso.modificarProceso(id, nuevoNombre);
    }

    public void modificarTxt(String ruta, String id, String linea) {
        listaEnlazadaProceso.modificarTxt(ruta, id, linea);
    }

    public void guardarTxt() {
        listaEnlazadaProceso.guardarTxt();
    }

    public void eliminarTxt(String id) {
        listaEnlazadaProceso.eliminarProceso(id);
    }

    public void cargarInicio(String nombreArchivo){
        listaEnlazadaProceso.cargarDesdeArchivo(nombreArchivo);
    }

    //metodo para guardar las actividades creadas
    public void agregarActividadAProceso(String id, Actividad actividad){
        listaEnlazadaProceso.agregarActividadAProceso(id, actividad);
    }

    public void guardarActividadEnProcesoTxt(){
        listaEnlazadaProceso.guardarActividadEnProcesoTxt();
    }


}
