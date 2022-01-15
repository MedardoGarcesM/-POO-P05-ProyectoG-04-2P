module com.mycompany.proyectog4parcial2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;
    requires java.mail;   //paquete requerido de mail, para enviar correo     

    opens com.mycompany.proyectog4parcial2 to javafx.fxml;
    opens com.mycompany.proyectog4parcial2.modelo to javafx.base;
    exports com.mycompany.proyectog4parcial2;
}
