package co.edu.uniquindio.proyectoestructura.controller;
import co.edu.uniquindio.proyectoestructura.listasEnlazadas.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtil;

public class AdminProcesoController {
    ListaEnlazadaProceso listaEnlazadaProceso= new ListaEnlazadaProceso();
    ArchivoUtil archivoUtil= new ArchivoUtil();

    //guardar el proceso
   public void guardarProceso(Proceso proceso){
       listaEnlazadaProceso.agregarProceso(proceso);
       listaEnlazadaProceso.guardarProcesosEnArchivo();

    }


}
