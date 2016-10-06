package com.app.dao;

import java.util.List;

/**
 * Created by Andre on 21.06.2016.
 */
public interface DAO {
    public void createEmployee(String name);

    public Employee getEmployye(long id);

    public List<Employee> getEmployeeByName(String name);

    public void updateEmployee(long id, Employee employee);

    public void deleteEmployee(long id);

    public void readMetadata();

    public void bulkAction();

}
