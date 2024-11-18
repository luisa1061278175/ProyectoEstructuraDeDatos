package co.edu.uniquindio.proyectoestructura.controller;

import co.edu.uniquindio.proyectoestructura.estructurasPropias.colas.actividad.ColaActividad;
import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;

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

    public Queue<Actividad> cargarActividadesDesdeArchivo(String ruta) {
        return colaActividad.cargarActividadesDesdeArchivo(ruta);
    }

    public Actividad[] leerArchivo(String ruta) {
        return colaActividad.leerArchivo(ruta);
    }

    public boolean intercambiarActividades(String nom1, String nom2, String archivo) {
        return colaActividad.intercambiarActividades(nom1, nom2, archivo);
    }
}
