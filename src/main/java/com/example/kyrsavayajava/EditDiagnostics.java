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

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requestDao = new RequestDaoImpl();
        employeeDAO = new EmployeeDAOImpl();
    }

    public void setRequest(Request currentRequest) {
        this.currentRequest = currentRequest;
    }

    public void setMasterToRequest(ActionEvent event) {
        List<Employee> employeeList     = employeeDAO.findByJobPosition(JobPosition.MASTER);
        Employee       selectedEmployee = employeeList.get(random.nextInt(0, employeeList.size()));
        currentRequest.setEmployeeDiagnostics(selectedEmployee.getId());
        requestDao.update(currentRequest);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Назначен мастер-приемщик.\n" + selectedEmployee.getFirstName() + "  " + selectedEmployee.getLastName(), ButtonType.OK);
        alert.showAndWait();
    }

    public void startDiagnostics(ActionEvent event) {
        if (currentRequest.getEmployeeDiagnostics() != 0) {
            currentRequest.setRequestStatus(RequestStatus.DIAGNOSTICS);
            currentRequest.setExecutionStage(ExecutionStage.DIAGNOSTICS);
            long priceDiagnostics = random.nextLong(1000, 5000);
            currentRequest.setPrice(priceDiagnostics);
            requestDao.update(currentRequest);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Диагностика проведена.\nСтоимость диагностики " + priceDiagnostics, ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Невозможно начать диагностику! Не выбран мастер-приемщик.", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
