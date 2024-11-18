package co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.Usuarios;

import co.edu.uniquindio.proyectoestructura.modelo.Usuario;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ListaEnlazadaUsuarioTest {

    private static final String RUTA_TEST_USUARIOS = "src/test/resources/UsuariosRegistradosTest.txt";
    private ListaEnlazadaUsuario lista;

    @BeforeEach
    void setUp() throws IOException {
        // Crear archivo temporal para pruebas
        Files.createDirectories(Paths.get("src/test/resources"));
        Files.write(Paths.get(RUTA_TEST_USUARIOS), "".getBytes()); // Archivo vacío
        lista = new ListaEnlazadaUsuario();
    }

    @AfterEach
    void tearDown() throws IOException {
        // Eliminar archivo de prueba
        Files.deleteIfExists(Paths.get(RUTA_TEST_USUARIOS));
    }

    @Test
    void testAgregarUsuario() {
        Usuario usuario = new Usuario("1", "Juan", "juan@example.com", "1234");
        lista.agregarUsuario(usuario);

        assertNotNull(lista.getCabeza(), "La cabeza no debería ser null.");
        assertEquals(usuario, lista.getCabeza().getUsuario(), "El usuario no se agregó correctamente.");
    }

    @Test
    void testEliminarUsuario() {
        Usuario usuario1 = new Usuario("1", "Juan", "juan@example.com", "1234");
        Usuario usuario2 = new Usuario("2", "Maria", "maria@example.com", "abcd");
        lista.agregarUsuario(usuario1);
        lista.agregarUsuario(usuario2);

        lista.eliminarUsuario("1");

        assertNotNull(lista.getCabeza(), "La lista no debería estar vacía.");
        assertEquals("2", lista.getCabeza().getUsuario().getId(), "El usuario con ID 1 no fue eliminado correctamente.");
    }

    @Test
    void testModificarUsuario() {
        Usuario usuario = new Usuario("1", "Juan", "juan@example.com", "1234");
        lista.agregarUsuario(usuario);

        boolean modificado = lista.modificarUsuario("1", "Juan Modificado", "modificado@example.com", "5678");

        assertTrue(modificado, "El usuario debería haberse modificado.");
        assertEquals("Juan Modificado", lista.getCabeza().getUsuario().getNombre(), "El nombre no fue actualizado.");
        assertEquals("modificado@example.com", lista.getCabeza().getUsuario().getEmail(), "El email no fue actualizado.");
    }

    @Test
    void testBuscarUsuario() {
        Usuario usuario1 = new Usuario("1", "Juan", "juan@example.com", "1234");
        Usuario usuario2 = new Usuario("2", "Maria", "maria@example.com", "abcd");
        lista.agregarUsuario(usuario1);
        lista.agregarUsuario(usuario2);

        Usuario encontrado = lista.buscarUsuario("2");

        assertNotNull(encontrado, "El usuario con ID 2 debería existir.");
        assertEquals("Maria", encontrado.getNombre(), "El usuario encontrado no es el esperado.");
    }

    @Test
    void testAgregarUsuarioAlArchivo() throws IOException {
        lista.agregarUsuarioAlArchivo("1", "Juan", "juan@example.com", "1234");

        String contenidoArchivo = Files.readString(Paths.get(RUTA_TEST_USUARIOS));
        assertTrue(contenidoArchivo.contains("1;Juan;juan@example.com;1234"), "El usuario no se guardó correctamente en el archivo.");
    }

    @Test
    void testCargarDesdeArchivo() throws IOException {
        String contenido = "1;Juan;juan@example.com;1234\n2;Maria;maria@example.com;abcd\n";
        Files.write(Paths.get(RUTA_TEST_USUARIOS), contenido.getBytes());

        lista.cargarDesdeArchivo(RUTA_TEST_USUARIOS);

        assertNotNull(lista.getCabeza(), "La cabeza no debería ser null después de cargar el archivo.");
        assertEquals("Juan", lista.getCabeza().getUsuario().getNombre(), "El primer usuario no se cargó correctamente.");
        assertEquals("Maria", lista.getCabeza().getSiguiente().getUsuario().getNombre(), "El segundo usuario no se cargó correctamente.");
    }

    @Test
    void testEliminarUsuarioDeArchivo() throws IOException {
        String contenido = "1;Juan;juan@example.com;1234\n2;Maria;maria@example.com;abcd\n";
        Files.write(Paths.get(RUTA_TEST_USUARIOS), contenido.getBytes());

        lista.eliminarUsuarioDeArchivo("1");

        String contenidoArchivo = Files.readString(Paths.get(RUTA_TEST_USUARIOS));
        assertFalse(contenidoArchivo.contains("1;Juan;juan@example.com;1234"), "El usuario con ID 1 no fue eliminado del archivo.");
    }
}
