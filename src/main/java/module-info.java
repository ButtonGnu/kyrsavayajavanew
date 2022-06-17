module com.example.kyrsavayajava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kyrsavayajava to javafx.fxml;
    exports com.example.kyrsavayajava;
}