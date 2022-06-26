package com.example.kyrsavayajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class EditCompleted implements Initializable {
    @FXML
    public Label labelName;
    @FXML
    public Label labelPhone;
    @FXML
    public Label labelReason;
    @FXML
    public Label labelStatus;
    @FXML
    public Label labelEmployeeDiagnostics;
    @FXML
    public Label labelEmployeeRep;
    @FXML
    public Label labelPrice;

    private Request currentRequest;

    public void setRequest(Request request) {
        this.currentRequest = request;
    }

    public void Open10(ActionEvent event) {

        FileChooser                 chooser   = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        chooser.setTitle("Выбор папки");
        File selectedFile = null;
        while (selectedFile == null) {
            selectedFile = chooser.showSaveDialog(null);
        }

        File file = new File(String.valueOf(selectedFile));
        try (PrintWriter outFile = new PrintWriter(file)) {
            outFile.println(labelName.getText());
            outFile.println(labelPhone.getText());
            outFile.println(labelReason.getText());
            outFile.println(labelEmployeeDiagnostics.getText());
            outFile.println(labelEmployeeRep.getText());
            outFile.println(labelPrice.getText());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
