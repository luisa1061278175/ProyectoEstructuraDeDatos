package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.Usuarios;

import co.edu.uniquindio.proyectoestructura.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListaEnlazadaUsuario {
    private Nodo cabeza;
    private static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/archivosTxt/UsuariosRegistrados.txt";

    public ListaEnlazadaUsuario() {
        this.cabeza = null;
        cargarDesdeArchivo(RUTA_ARCHIVO_USUARIOS);
    }

    /*
     * MÉTODOS PROPIOS DE LA LISTA PARA HACER CRUD
     */

    public void agregarUsuario(Usuario usuario) {
        Nodo nuevoNodo = new Nodo(usuario);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
    }

    public void eliminarUsuario(String id) {
        if (cabeza == null) return;

        if (cabeza.getUsuario().getId().equals(id)) {
            cabeza = cabeza.getSiguiente();
            return;
        }

        Nodo actual = cabeza;
        Nodo anterior = null;

        while (actual != null && !actual.getUsuario().getId().equals(id)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual != null) {
            anterior.setSiguiente(actual.getSiguiente());
        }
    }

    public boolean modificarUsuario(String id, String nuevoNombre, String nuevoEmail, String nuevaContrasena) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getUsuario().getId().equals(id)) {
                actual.getUsuario().setNombre(nuevoNombre);
                actual.getUsuario().setEmail(nuevoEmail);
                actual.getUsuario().setContrasenia(nuevaContrasena);
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    public boolean modificarTareasEnArchivo(String id, String nombre, String email, String contrasenia) {
        File archivo = new File(RUTA_ARCHIVO_USUARIOS);
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(nombre)) {

                    lineas.add(id + ";" + nombre + ";" + email + ";" + contrasenia);
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }

        return false;
    }

    public Usuario buscarUsuario(String id) {
        cargarDesdeArchivo(RUTA_ARCHIVO_USUARIOS);
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return null;
        }
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getUsuario().getId().equals(id)) {
                return actual.getUsuario();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }


    /*
     * MÉTODOS PARA GUARDAR USUARIOS EN TXT
     */

    public void agregarUsuarioAlArchivo(String id, String nombre, String email, String contrasena) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_USUARIOS, true))) {
            writer.write(id + ";" + nombre + ";" + email + ";" + contrasena);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar el usuario en el archivo: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    String email = datos[2].trim();
                    String contrasena = datos[3].trim();
                    Usuario usuario = new Usuario(id, nombre, email, contrasena);
                    System.out.println("Usuarios: "+usuario);
                    agregarUsuario(usuario);
                }
            }
            System.out.println("Datos cargados exitosamente desde el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void eliminarUsuarioDeArchivo(String idUsuario) {
        File archivo = new File(RUTA_ARCHIVO_USUARIOS);
        List<String> lineasActualizadas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (!datos[0].equals(idUsuario)) {
                    lineasActualizadas.add(linea);
                } else {
                    System.out.println("Usuario con ID " + idUsuario + " eliminado del archivo.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
            for (String lineaActualizada : lineasActualizadas) {
                writer.write(lineaActualizada);
                writer.newLine();
            }
            System.out.println("Archivo actualizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void mostrarUsuarios() {
        if (cabeza == null) {
            System.out.println("No hay usuarios en la lista.");
            return;
        }

        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println(temp.getUsuario());
            temp = temp.getSiguiente();
        }
    }

    public Nodo getCabeza() {
        return cabeza;
    }
}
