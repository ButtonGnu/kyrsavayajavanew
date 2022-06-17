package com.example.kyrsavayajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Home {

    public void Open1(ActionEvent event){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("Window1.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Новая заявка");
            primaryStage.setScene(new Scene(root, 1000, 500));
            primaryStage.show();

        }
        catch (Exception ex){

        }


    }
    public void Open2(ActionEvent event){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("Window2.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Новая заявка");
            primaryStage.setScene(new Scene(root, 1000, 500));
            primaryStage.show();

        }
        catch (Exception ex){

        }


    }
    public void Open3(ActionEvent event){
            System.exit(0);
        }
                   }
