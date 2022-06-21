package com.example.kyrsavayajava;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Window1 {
    public void Open5(ActionEvent event){

        try{

        }
        catch (Exception ex){

        }


    }
    public void Open6(ActionEvent event){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("Window3.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Новая заявка");
            primaryStage.setScene(new Scene(root, 500, 500));
            primaryStage.show();

        }
        catch (Exception ex){

        }


    }

}

