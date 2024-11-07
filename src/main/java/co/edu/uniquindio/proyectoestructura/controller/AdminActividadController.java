package co.edu.uniquindio.proyectoestructura.controller;

import co.edu.uniquindio.proyectoestructura.estructurasPropias.colas.actividad.ColaActividad;
import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Proceso;

import java.util.Queue;

public class AdminActividadController {

    ColaActividad colaActividad = new ColaActividad();
    AdminProcesoController adminProcesoController;
    ListaEnlazadaProceso listaEnlazadaProceso;

    public void guardar(Actividad actividad) {
        colaActividad.guardarActividad(actividad);
    }

    public void eliminar(String nombre) {
        colaActividad.eliminarActividad(nombre);
    }

    //se ingresa el nombre anterior pero con nueva descripcion
    public void modificar(String nombre, String descripcion, boolean obligatorio) {
        colaActividad.modificarActividad(nombre, descripcion, obligatorio);
    }

    //METODOS AL TXT

    public void agregarTxt(Queue<Actividad> actividades) {
        colaActividad.agregarActividadAlArchivo(actividades);

    }

    public void modificarTxt(String nombre, String nuevaDescripcion, boolean esObligatoria) {
        colaActividad.modificarActividadEnArchivo(nombre, nuevaDescripcion, esObligatoria);
    }

    public void eliminarTxt(String identificador) {
        colaActividad.eliminarLineaDelArchivo(identificador);
    }

    public Actividad buscarActividad(String nombre) {
        return colaActividad.buscarActividadPorNombre(nombre);
    }


}
