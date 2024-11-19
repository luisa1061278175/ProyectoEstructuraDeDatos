module co.edu.uniquindio.proyectoestructura {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.mail;
    requires javax.mail.api;
    requires java.desktop;
    requires gs.core;


    opens co.edu.uniquindio.proyectoestructura to javafx.fxml;
    exports co.edu.uniquindio.proyectoestructura;
    exports co.edu.uniquindio.proyectoestructura.modelo;
    opens co.edu.uniquindio.proyectoestructura.modelo to javafx.fxml;
    exports co.edu.uniquindio.proyectoestructura.controller;
    opens co.edu.uniquindio.proyectoestructura.controller to javafx.fxml;
    exports co.edu.uniquindio.proyectoestructura.viewController.administradores;
    opens co.edu.uniquindio.proyectoestructura.viewController.administradores;
    exports co.edu.uniquindio.proyectoestructura.viewController.usuarios;
    opens co.edu.uniquindio.proyectoestructura.viewController.usuarios;
    exports co.edu.uniquindio.proyectoestructura.grafo to javafx.graphics;



}