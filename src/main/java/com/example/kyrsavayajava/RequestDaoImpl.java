package com.example.kyrsavayajava;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDaoImpl implements DAO<Request> {

    String     dbURL = "jdbc:postgresql://localhost:5432/SPARES3";
    String     user  = "postgres";
    String     pass  = "2001Eliz!!";
    Connection conn;
    private final String INSERT_STATEMENT_CUSTOMER     = "INSERT INTO public.customers(first_name, last_name, phone_number) VALUES (?, ?, ?);";
    private final String DELETE_STATEMENT_CUSTOMER     = "DELETE FROM public.customers WHERE id=?;";
    private final String INSERT_STATEMENT_REQUESTS     = "INSERT INTO public.requests(reason, employee_diagnostics, employee_repair,execution_stage, request_status, customer_id) VALUES (?, ?, ?, ?, ?, ?);";
    private final String UPDATE_STATEMENT_REQUESTS     = "UPDATE public.requests SET employee_diagnostics=?, employee_repair=?, execution_stage=?, request_status=?, price=? WHERE id=?;";
    private final String FIND_BY_ID_STATEMENT_REQUESTS = "select * from public.requests r inner join public.customers cus on r.id=cus.id where r.id=?;";
    private final String FIND_ALL_REQUESTS             = "select * from public.requests r left join public.customers cus on r.id=cus.id;";

    public RequestDaoImpl() {
        try {
            conn = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Request request) {
        long customerId = 0L;
        try {
            PreparedStatement preparedStatementInsertCustomer = conn.prepareStatement(INSERT_STATEMENT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
            preparedStatementInsertCustomer.setString(1, request.getCustomerFirstName());
            preparedStatementInsertCustomer.setString(2, request.getCustomerLastName());
            preparedStatementInsertCustomer.setString(3, request.getCustomerPhone());

            int affectedRows = preparedStatementInsertCustomer.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatementInsertCustomer.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customerId = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
                PreparedStatement preparedStatementInsertRequest = conn.prepareStatement(INSERT_STATEMENT_REQUESTS, Statement.RETURN_GENERATED_KEYS);
                preparedStatementInsertRequest.setString(1, request.getReason());
                if (request.getEmployeeRepair() == 0) {
                    preparedStatementInsertRequest.setNull(3, Types.BIGINT);
                } else {
                    preparedStatementInsertRequest.setLong(3, request.getEmployeeRepair());
                }
                if (request.getEmployeeDiagnostics() == 0) {
                    preparedStatementInsertRequest.setNull(2, Types.BIGINT);
                } else {
                    preparedStatementInsertRequest.setLong(2, request.getEmployeeDiagnostics());
                }
                preparedStatementInsertRequest.setString(4, request.getExecutionStage().getDisplayName());
                preparedStatementInsertRequest.setString(5, request.getRequestStatus().getDisplayName());
                preparedStatementInsertRequest.setLong(6, customerId);

                preparedStatementInsertRequest.executeUpdate();

            }
        } catch (SQLException ex) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(DELETE_STATEMENT_CUSTOMER);
                preparedStatement.setLong(1, customerId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Request request) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_STATEMENT_REQUESTS);
            if (request.getEmployeeDiagnostics() == 0) {
                preparedStatement.setNull(1, Types.BIGINT);
            } else {
                preparedStatement.setLong(1, request.getEmployeeDiagnostics());
            }
            if (request.getEmployeeRepair() == 0) {
                preparedStatement.setNull(2, Types.BIGINT);
            } else {
                preparedStatement.setLong(2, request.getEmployeeRepair());
            }
            preparedStatement.setString(3, request.getExecutionStage().getDisplayName());
            preparedStatement.setString(4, request.getRequestStatus().getDisplayName());
            preparedStatement.setLong(5, request.getPrice());
            preparedStatement.setLong(6, request.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Request findById(Long id) {
        Request request = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_ID_STATEMENT_REQUESTS);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                request = new Request();
                request.setId(resultSet.getLong("id"));
                request.setCustomerId(resultSet.getLong("customer_id"));
                request.setReason(resultSet.getString("reason"));
                request.setRequestStatus(RequestStatus.retrieveByDisplayedName(resultSet.getString("request_status")));
                request.setExecutionStage(ExecutionStage.retrieveByDisplayedName(resultSet.getString("execution_stage")));
                request.setCustomerPhone(resultSet.getString("phone_number"));
                request.setCustomerLastName(resultSet.getString("last_name"));
                request.setCustomerFirstName(resultSet.getString("first_name"));
                request.setEmployeeRepair(resultSet.getLong("employee_id"));
                request.setLicensePlate(resultSet.getString("license_plate"));
                request.setPrice(resultSet.getLong("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL_REQUESTS);
            ResultSet         resultSet         = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Request request = new Request();
                request.setId(resultSet.getLong("id"));
                request.setCustomerId(resultSet.getLong("customer_id"));
                request.setReason(resultSet.getString("reason"));
                request.setRequestStatus(RequestStatus.retrieveByDisplayedName(resultSet.getString("request_status")));
                request.setExecutionStage(ExecutionStage.retrieveByDisplayedName(resultSet.getString("execution_stage")));
                request.setCustomerPhone(resultSet.getString("phone_number"));
                request.setCustomerLastName(resultSet.getString("last_name"));
                request.setCustomerFirstName(resultSet.getString("first_name"));
                request.setEmployeeRepair(resultSet.getLong("employee_repair"));
                request.setEmployeeDiagnostics(resultSet.getLong("employee_diagnostics"));
                request.setLicensePlate(resultSet.getString("license_plate"));
                request.setPrice(resultSet.getLong("price"));
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
}
