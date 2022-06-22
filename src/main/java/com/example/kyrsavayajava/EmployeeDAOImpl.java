package com.example.kyrsavayajava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements DAO<Employee> {

    String     dbURL = "jdbc:postgresql://localhost:5432/postgres";
    String     user  = "postgres";
    String     pass  = "Shery1511Noya";
    Connection conn;
    private final String FIND_BY_ID_STATEMENT = "select * from public.employees;";
    private final String FIND_ALL_REQUESTS    = "select * from public.employees;";

    public EmployeeDAOImpl() {
        try {
            conn = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Employee employee) {

    }

    @Override
    public void update(Employee employee) {


    }

    @Override
    public Employee findById(Long id) {
        Employee employee = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_ID_STATEMENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setJobPosition(JobPosition.retrieveByDisplayedName(resultSet.getString("job_position")));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setFirstName(resultSet.getString("first_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> requests = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL_REQUESTS);
            ResultSet         resultSet         = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setJobPosition(JobPosition.retrieveByDisplayedName(resultSet.getString("job_position")));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setFirstName(resultSet.getString("first_name"));
                requests.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
}
