package com.example.kyrsavayajava;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Window1 {
    @FXML
    public TextField name = new TextField();
    @FXML
    public TextField phone = new TextField();
    @FXML
    public TextField plateNr = new TextField();
    @FXML
    public TextField reason = new TextField();

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

    private String generateRequestReason() {
        DamageGenerator dg              = new DamageGenerator();
        Damage       damage          = dg.generateDamage(new JsonReader().readJson("поломки.json"));
        List<String> damageDBStrings = new ArrayList<>();
        for (int i = 0; i < damage.getSubCategories().size(); i++) {
            DamageSubCategory damageSubCategory = damage.getSubCategories().get(i);
            if (damageSubCategory.getSubType() != null) {
                for (int j = 0; j < damageSubCategory.getSubType().size(); j++) {
                    String   subCategoryType = damageSubCategory.getSubType().get(j);
                    DamageDB e               = new DamageDB(damage.getCategory(), damageSubCategory.getType(), subCategoryType);
                    damageDBStrings.add(e.toString());
                }
            } else {
                DamageDB e = new DamageDB(damage.getCategory(), damageSubCategory.getType(), "");
                damageDBStrings.add(e.toString());
            }
        }
        return String.join("/", damageDBStrings);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String requestReason = generateRequestReason();
        this.reason.setText(requestReason);
    }
}

