package com.example.kyrsavayajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window2 {
    public void Open4(ActionEvent event){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("Window4.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Модерация заявки");
            primaryStage.setScene(new Scene(root, 500, 600));
            primaryStage.show();

        }
        catch (Exception ex){

        }


    }

}
