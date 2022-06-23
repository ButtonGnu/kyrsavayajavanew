package com.example.kyrsavayajava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;


public class Window1 implements Initializable {
    @FXML
    public  TextField name    = new TextField();
    @FXML
    public  TextField phone   = new TextField();
    @FXML
    public  TextField plateNr = new TextField();
    @FXML
    public  TextField reason  = new TextField();
    @FXML
    public TableColumn<Request, Long> idColumn;
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
    public TableColumn<Request, Long> priceColumn;
    @FXML
    ObservableList<Request> tableData     = FXCollections.observableArrayList();
    @FXML
    TableView<Request>      tableRequests = new TableView<>(tableData);

    private Employee  selectedEmployee;

    public void setMasterToRequest(ActionEvent event) {
        ThreadLocalRandom random       = ThreadLocalRandom.current();
        EmployeeDAOImpl   employeeDAO  = new EmployeeDAOImpl();
        List<Employee>    employeeList = employeeDAO.findByJobPosition(JobPosition.MASTER);
        Employee          employee     = employeeList.get(random.nextInt(0, employeeList.size()));
        selectedEmployee = employee;
        try {

        } catch (Exception ex) {

        }


    }

    public void startDiagnostics(ActionEvent event) {
        try {
            Parent root         = FXMLLoader.load(getClass().getResource("Window3.fxml"));
            Stage  primaryStage = new Stage();
            primaryStage.setTitle("Новая заявка");
            primaryStage.setScene(new Scene(root, 500, 500));
            primaryStage.show();

        } catch (Exception ex) {

        }


    }

    private String generateRequestReason() {
        DamageGenerator dg              = new DamageGenerator();
        Damage          damage          = dg.generateDamage(new JsonReader().readJson("поломки.json"));
        List<String>    damageDBStrings = new ArrayList<>();
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

    private void reloadTable() {
        tableData.clear();
        RequestDaoImpl requestDao  = new RequestDaoImpl();
        List<Request>  requestList = requestDao.findAll();
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
            reloadTable();
            String requestReason = generateRequestReason();
            this.reason.setText(requestReason);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createRequest(ActionEvent actionEvent) {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        Request        request    = new Request();
        request.setRequestStatus(RequestStatus.NEW_REQUEST);
        request.setExecutionStage(ExecutionStage.NEW_REQUEST);
        request.setReason(this.reason.getText());
        String[] name = this.name.getText().split(" ");
        request.setCustomerFirstName(name[0]);
        request.setCustomerLastName(name[1]);
        request.setCustomerPhone(this.phone.getText());
        request.setLicensePlate(this.plateNr.getText());
        request.setEmployeeId(selectedEmployee.getId());
        requestDao.create(request);
    }
}

