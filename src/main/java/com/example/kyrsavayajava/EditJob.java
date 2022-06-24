package com.example.kyrsavayajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class EditJob implements Initializable {
    @FXML
    public Label labelName;
    @FXML
    public Label labelPhone;
    @FXML
    public Label labelReason;
    @FXML
    public Label labelStatus;

    private RequestDaoImpl  requestDao;
    private EmployeeDAOImpl employeeDAO;
    private Request         currentRequest;

    public void assignTo(ActionEvent event) {
        ThreadLocalRandom random           = ThreadLocalRandom.current();
        List<Employee>    employeeList     = employeeDAO.findByJobPosition(JobPosition.MASTER);
        Employee          selectedEmployee = employeeList.get(random.nextInt(0, employeeList.size()));
        currentRequest.setEmployeeId(selectedEmployee.getId());
        currentRequest.setExecutionStage(ExecutionStage.REPAIR);
        requestDao.update(currentRequest);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Назначен " + selectedEmployee.getJobPosition().getDisplayName() + ":\n"+ selectedEmployee.getFirstName() + "  "+ selectedEmployee.getLastName(), ButtonType.OK);
        alert.showAndWait();
    }

    public void setRequest(Request request) {
        this.currentRequest = request;
    }

    public void startJob(ActionEvent event) {
        currentRequest.setRequestStatus(RequestStatus.DONE);
        currentRequest.setExecutionStage(ExecutionStage.COMPLETED);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requestDao = new RequestDaoImpl();
        employeeDAO = new EmployeeDAOImpl();
    }

    public void cancel(ActionEvent actionEvent) {

    }
}

