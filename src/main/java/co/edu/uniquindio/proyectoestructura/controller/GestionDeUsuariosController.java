package co.edu.uniquindio.proyectoestructura.controller;

import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.Usuarios.ListaEnlazadaUsuario;
import co.edu.uniquindio.proyectoestructura.modelo.Usuario;

public class GestionDeUsuariosController {

    ListaEnlazadaUsuario listaEnlazadaUsuario = new ListaEnlazadaUsuario();


    // Método para guardar un usuario en la lista
    public void guardarUsuario(Usuario usuario) {
        listaEnlazadaUsuario.agregarUsuario(usuario);
    }

    // Método para eliminar un usuario por ID
    public void eliminarUsuario(String id) {
        listaEnlazadaUsuario.eliminarUsuario(id);
    }

    // Método para modificar un usuario
    public void modificarUsuario(String id, String nuevoNombre, String nuevoEmail, String nuevaContraseña) {
        listaEnlazadaUsuario.modificarUsuario(id, nuevoNombre, nuevoEmail, nuevaContraseña);
    }

    // Métodos para operaciones en el archivo
    public void modificarTxt(String id, String nombre, String email, String contrasenia) {
        listaEnlazadaUsuario.modificarTareasEnArchivo(id, nombre, email,contrasenia);
    }

    public void guardarTxt(String id, String nombre, String email, String contraseña) {
        listaEnlazadaUsuario.agregarUsuarioAlArchivo(id, nombre, email, contraseña);
    }

    public void eliminarTxt(String id) {
        listaEnlazadaUsuario.eliminarUsuarioDeArchivo(id);
    }

    // Método para cargar los datos desde un archivo al inicio
    public void cargarInicio(String nombreArchivo) {
        listaEnlazadaUsuario.cargarDesdeArchivo(nombreArchivo);
    }

    // Método para buscar un usuario por ID
    public Usuario buscarUsuario(String id) {
        return listaEnlazadaUsuario.buscarUsuario(id);
    }
}

