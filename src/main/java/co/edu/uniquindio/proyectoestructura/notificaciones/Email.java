package co.edu.uniquindio.proyectoestructura.notificaciones;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Email {

    public static void enviarEmail(String subject, String content, String toEmail) {

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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); // Destinatario dinámico
            message.setSubject(subject);
            message.setText(content);


            Transport.send(message);

            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String subject = "Premio";
        String content = "Te has ganado un premio, comunicate con este número: 3007276599";
        String toEmail = "dillansnayder@gmail.com";
        enviarEmail(subject, content, toEmail);
    }
}
