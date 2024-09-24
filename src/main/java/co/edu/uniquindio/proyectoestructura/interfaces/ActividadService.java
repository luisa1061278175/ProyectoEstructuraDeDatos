package co.edu.uniquindio.proyectoestructura.interfaces;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import java.util.List;

public interface ActividadService {


    public void crearActividad(String nombre, String descripcion, boolean esObligatoria, String actividadPrecedente);

    public Actividad buscarActividad(String nombre);

    public void intercambiarActividades(String nombreActividad1, String nombreActividad2, boolean incluirTareas);

    public List<Actividad> obtenerActividadesDeProceso(int idProceso);

    public void eliminarActividad(String nombreActividad);

    public int obtenerTiempoMinimo(Actividad actividad);

    public int obtenerTiempoMaximo(Actividad actividad);

}
