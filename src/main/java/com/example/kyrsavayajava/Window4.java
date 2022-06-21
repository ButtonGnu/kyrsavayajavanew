package com.example.kyrsavayajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window4 {

    public void Open9(ActionEvent event){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("Window5.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Счет");
            primaryStage.setScene(new Scene(root, 500, 600));
            primaryStage.show();

        }
        catch (Exception ex){

        }


    }

}
