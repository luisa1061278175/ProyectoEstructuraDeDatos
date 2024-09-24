module co.edu.uniquindio.proyectoestructura {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyectoestructura to javafx.fxml;
    exports co.edu.uniquindio.proyectoestructura;
    exports co.edu.uniquindio.proyectoestructura.modelo;
    opens co.edu.uniquindio.proyectoestructura.modelo to javafx.fxml;
}