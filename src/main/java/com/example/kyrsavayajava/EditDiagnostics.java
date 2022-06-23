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

public class EditDiagnostics implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requestDao = new RequestDaoImpl();
        employeeDAO = new EmployeeDAOImpl();
    }

    public void setRequest(Request currentRequest) {
        this.currentRequest = currentRequest;
    }

    public void setMasterToRequest(ActionEvent event) {
        ThreadLocalRandom random           = ThreadLocalRandom.current();
        List<Employee>    employeeList     = employeeDAO.findByJobPosition(JobPosition.MASTER);
        Employee          selectedEmployee = employeeList.get(random.nextInt(0, employeeList.size()));
        currentRequest.setEmployeeId(selectedEmployee.getId());
        requestDao.update(currentRequest);
    }

    public void startDiagnostics(ActionEvent event) {
        if (currentRequest.getEmployeeId() != 0) {
            currentRequest.setRequestStatus(RequestStatus.DIAGNOSTICS);
            currentRequest.setExecutionStage(ExecutionStage.DIAGNOSTICS);
            requestDao.update(currentRequest);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Невозможно начать диагностику! Не выбран мастер-приемщик.", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
