package com.example.kyrsavayajava;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditRequest implements Initializable {
    @FXML
    public Button                       editRequestButton;
    @FXML
    public TableColumn<Request, Long>   idColumn;
    @FXML
    public TableColumn<Request, String> firstNameColumn;
    @FXML
    public TableColumn<Request, String> lastNameColumn;
    @FXML
    public TableColumn<Request, String> phoneColumn;
    @FXML
    public TableColumn<Request, String> licensePlateColumn;
    @FXML
    public TableColumn<Request, String> reasonColumn;
    @FXML
    public TableColumn<Request, String> requestStatusColumn;
    @FXML
    public TableColumn<Request, String> executionStageColumn;
    @FXML
    public TableColumn<Request, Long>   priceColumn;
    @FXML
    ObservableList<Request> tableData     = FXCollections.observableArrayList();
    @FXML
    TableView<Request>      tableRequests = new TableView<>(tableData);

    private RequestDaoImpl  requestDao;

    public void editRequest(ActionEvent event) throws IOException {
        Request request = tableRequests.getSelectionModel().getSelectedItems().get(0);
        switch (request.getRequestStatus()){
            case NEW_REQUEST:
                editNewRequest(request);
                break;
            case DIAGNOSTICS:
                editDiagnostics(request);
                break;
            case DONE:
                editDone(request);
                break;
        }
    }

    private void editDone(Request request) throws IOException {
        Parent root         = FXMLLoader.load(getClass().getResource("EditCompleted.fxml"));
        Stage  primaryStage = new Stage();
        primaryStage.setTitle("Модерация заявки");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    private void editDiagnostics(Request request) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditJob.fxml"));
        Parent  root            = loader.load();
        EditJob alertController = loader.getController();
        alertController.setRequest(request);
        alertController.labelName.setText(request.getCustomerFirstName() + " "+ request.getCustomerLastName());
        alertController.labelPhone.setText(request.getCustomerPhone());
        alertController.labelReason.setText(request.getReason());
        alertController.labelStatus.setText(request.getRequestStatus().getDisplayName());
        Stage  primaryStage = new Stage();
        primaryStage.setTitle("Модерация заявки");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    private void editNewRequest(Request request) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDiagnostics.fxml"));
        Parent  root            = loader.load();
        EditDiagnostics alertController = loader.getController();
        alertController.setRequest(request);
        alertController.labelName.setText(request.getCustomerFirstName() + " "+ request.getCustomerLastName());
        alertController.labelPhone.setText(request.getCustomerPhone());
        alertController.labelReason.setText(request.getReason());
        alertController.labelStatus.setText(request.getRequestStatus().getDisplayName());
        Stage  primaryStage = new Stage();
        primaryStage.setTitle("Модерация заявки");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    private void reloadTable() {
        tableData.clear();
        List<Request> requestList = requestDao.findAll();
        tableData.addAll(requestList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerFirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerLastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        requestStatusColumn.setCellValueFactory(new PropertyValueFactory<>("requestStatus"));
        executionStageColumn.setCellValueFactory(new PropertyValueFactory<>("executionStage"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableRequests.setItems(tableData);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            requestDao = new RequestDaoImpl();
            reloadTable();
            editRequestButton.disableProperty().bind(Bindings.isEmpty(tableRequests.getSelectionModel().getSelectedItems()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
