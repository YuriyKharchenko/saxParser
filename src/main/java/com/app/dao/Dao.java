package com.app.dao;

import java.util.List;


public interface DAO {
    public void createEmployee(String name);

    public Employee getEmployye(long id);

    public List<Employee> getEmployeeByName(String name);

    public void updateEmployee(long id, Employee employee);

    public void deleteEmployee(long id);

    public void readMetadata();

    public void bulkAction();

}
