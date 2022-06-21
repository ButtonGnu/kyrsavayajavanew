package com.example.kyrsavayajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window3 {
        public void Open7 (ActionEvent event){

        try {

        } catch (Exception ex) {

        }


    }
        public void Open8 (ActionEvent event) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("Window4.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Модерация заявки");
                primaryStage.setScene(new Scene(root, 500, 500));
                primaryStage.show();

            } catch (Exception ex) {

            }
        }
            public void Open11 (ActionEvent event){

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Window5.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Модерация заявки");
                    primaryStage.setScene(new Scene(root, 500, 500));
                    primaryStage.show();

                } catch (Exception ex) {

                }


    }

    }

