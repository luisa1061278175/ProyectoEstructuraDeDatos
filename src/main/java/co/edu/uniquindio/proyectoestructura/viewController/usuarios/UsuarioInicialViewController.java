package co.edu.uniquindio.proyectoestructura.viewController.usuarios;

import co.edu.uniquindio.proyectoestructura.alerta.Alerta;
import co.edu.uniquindio.proyectoestructura.notificaciones.Email;
import co.edu.uniquindio.proyectoestructura.viewController.administradores.InicioSesionViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UsuarioInicialViewController {


    public String idUsuario;

    @FXML
    private Button btnIniciar;
    private Alerta alerta = new Alerta();
    Email email= new Email();

    public String verificarIdEnArchivo(String idUsuario) {
        System.out.println("Estamos verificando id");

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/archivosTxt/Notificaciones.txt"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);
                String[] datos = linea.split(";");

                if (datos.length > 1) {
                    System.out.println("Comparando ID: " + datos[0].trim() + " con: " + idUsuario.trim());  // Para depuración
                    if (datos[0].trim().equals(idUsuario.trim())) {
                        return datos[1].trim();
                    }
                }
            }

        } catch (IOException e) {
            alerta.mostrarAlertaError("Error de Archivo", "No se pudo leer el archivo.");
        }

        return null;
    }


    public String leerUsuarioAutenticado() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/archivosTxt/UsuarioAutenticado.txt"))) {
            String linea;
            linea = reader.readLine();
            String[] datos = linea.split(";");
            return datos[0].trim();
        } catch (IOException e) {
            alerta.mostrarAlertaError("Error de Archivo", "No se pudo leer el archivo.");
        }
        return null;
    }

    private void preguntarMedioDeRecordatorios() {
        Alert alertaOpciones = new Alert(Alert.AlertType.CONFIRMATION);
        alertaOpciones.setTitle("Recordatorios");
        alertaOpciones.setHeaderText("No encontramos tu registro.");
        alertaOpciones.setContentText("¿Cómo deseas recibir los recordatorios?");

        ButtonType opcionApp = new ButtonType("Por la aplicación");
        ButtonType opcionCorreo = new ButtonType("Por correo");
        ButtonType opcionCancelar = new ButtonType("Cancelar", ButtonType.CANCEL.getButtonData());

        alertaOpciones.getButtonTypes().setAll(opcionApp, opcionCorreo, opcionCancelar);

        Optional<ButtonType> respuesta = alertaOpciones.showAndWait();

        if (respuesta.isPresent()) {
            if (respuesta.get() == opcionApp) {
                guardarConfiguracionEnArchivo("aplicación");
                alerta.mostrarAlertaExito("Recordatorios", "Recibirás los recordatorios por la aplicación.");
            } else if (respuesta.get() == opcionCorreo) {
                guardarConfiguracionEnArchivo("correo");
                alerta.mostrarAlertaExito("Recordatorios", "Recibirás los recordatorios por correo electrónico.");
            } else {
                alerta.mostrarAlertaError("Recordatorios", "No seleccionaste un medio para recibir recordatorios.");
            }
        }
    }

    public void guardarConfiguracionEnArchivo(String opcion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivosTxt/Notificaciones.txt", true))) {
            writer.write(idUsuario + ";" + opcion);  // Escribir idUsuario y la opción seleccionada
            writer.newLine();
            System.out.println("Configuración guardada: " + idUsuario + ";" + opcion);  // Para depuración
        } catch (IOException e) {
            System.out.println("Error al guardar la configuración en el archivo");
        }

        // Verifica que el archivo se ha actualizado
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/archivosTxt/Notificaciones.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Contenido del archivo: " + linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo para verificar.");
        }
    }
    public static void eliminarLinea(String rutaArchivo, String datoAEliminar) {

        List<String> lineas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {

                if (!linea.contains(datoAEliminar)) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    public static void enviarEmail(String titulo, String contenido, String quienRecibe) {

        String host = "smtp.gmail.com";
        final String user = "dedatose@gmail.com";
        final String password = "ybtp nshg efgb pbba";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(quienRecibe)); // Destinatario dinámico
            message.setSubject(titulo);
            message.setText(contenido);


            Transport.send(message);

            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public String buscarCorreoPorId(String idUsuario) {
        System.out.println("Buscando correo para el ID: " + idUsuario);

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/archivosTxt/UsuariosRegistrados.txt"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);
                String[] datos = linea.split(";");

                if (datos.length > 3) {
                    System.out.println("Comparando ID: " + datos[0].trim() + " con: " + idUsuario.trim());
                    if (datos[0].trim().equals(idUsuario.trim())) {
                        return datos[2].trim();
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return null;
    }
    public ArrayList<String> obtenerNombresTareas() {
        ArrayList<String> nombresTareas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/archivosTxt/Tareas.txt"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);
                String[] datos = linea.split(";");

                if (datos.length > 0) {
                    nombresTareas.add(datos[0].trim());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return nombresTareas;
    }

    @FXML
    public void iniciar() {

        idUsuario = leerUsuarioAutenticado();

        ArrayList<String>listaTareas= new ArrayList<>();
        String tareas="";

        for (int i = 0; i < obtenerNombresTareas().size(); i++) {
            listaTareas.add(obtenerNombresTareas().get(i));

            tareas+=obtenerNombresTareas().get(i)+"\n";
        }

        if (idUsuario == null || idUsuario.isEmpty()) {
            alerta.mostrarAlertaError("Error", "ID de usuario no proporcionado.");
            return;
        }

        String configuracion = verificarIdEnArchivo(idUsuario.trim());
        if (configuracion != null) {
            if(configuracion.equals("correo")){

                alerta.mostrarAlertaExito("Espera a que la aplicacion cargue","Por favor no cambies de ventana");
                String id= buscarCorreoPorId(idUsuario);
                enviarEmail("Recuerda las tareas pendientes",tareas,id);
                eliminarLinea("src/main/resources/archivosTxt/UsuarioAutenticado.txt",idUsuario);
            }
            else alerta.mostrarAlertaExito("Lista de tareas pendientes",tareas);
            eliminarLinea("src/main/resources/archivosTxt/UsuarioAutenticado.txt",idUsuario);
        } else {

            preguntarMedioDeRecordatorios();
            eliminarLinea("src/main/resources/archivosTxt/UsuarioAutenticado.txt",idUsuario);

        }
    }


}


