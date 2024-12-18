package co.edu.uniquindio.proyectoestructura.controller;

import co.edu.uniquindio.proyectoestructura.estructurasPropias.colas.actividad.ColaActividad;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;
import co.edu.uniquindio.proyectoestructura.util.ArchivoUtilActividades;

import java.io.IOException;
import java.util.Queue;

public class AdminActividadController {

    ColaActividad colaActividad = new ColaActividad();
    ArchivoUtilActividades archivoUtilActividades = new ArchivoUtilActividades();

    public void guardar(Actividad actividad) {
        colaActividad.agregarActividad(actividad);
    }

    public void eliminar(String nombre) {
        colaActividad.eliminarActividad(nombre);
    }

    public void modificar(String nombre, String descripcion, boolean obligatorio) {
        colaActividad.modificarActividad(nombre, descripcion, obligatorio);
    }

    //METODOS AL TXT

    public void agregarTxt(Queue<Actividad> actividades) {
        archivoUtilActividades.agregarActividadAlArchivo(actividades);

    }

    public void modificarTxt(String nombre, String nuevaDescripcion, boolean esObligatoria) {
        archivoUtilActividades.modificarActividadEnArchivo(nombre, nuevaDescripcion, esObligatoria);
    }

    public void eliminarTxt(String identificador) {
        archivoUtilActividades.eliminarLineaDelArchivo(identificador);
    }

    public Actividad buscarActividad(String nombre) {
        return colaActividad.buscarActividadPorNombre(nombre);
    }

    public Queue<Actividad> cargarActividadesDesdeArchivo() {
        return archivoUtilActividades.cargarActividadesDesdeArchivo();
    }

    public Actividad[] leerArchivo(String ruta) {
        return archivoUtilActividades.leerArchivo(ruta);
    }

    public boolean intercambiarActividades(Queue<Actividad>cola, int indice1, int indice2) {
        return colaActividad.intercambiarActividades(cola,indice1,indice2);
    }

    public void intercambiarActividadesTxt(String rutaArchivo, int indice1, int indice2) throws IOException {
          archivoUtilActividades.intercambiarActividadesEnArchivo(rutaArchivo,indice1,indice2);
    }

}
