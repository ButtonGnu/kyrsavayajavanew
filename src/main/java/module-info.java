module com.example.kyrsavayajava {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.sql;


    opens com.example.kyrsavayajava to javafx.fxml;
    exports com.example.kyrsavayajava;
}