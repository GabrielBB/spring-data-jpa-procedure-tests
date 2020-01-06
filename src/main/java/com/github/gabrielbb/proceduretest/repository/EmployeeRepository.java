package com.github.gabrielbb.proceduretest.repository;

import com.github.gabrielbb.proceduretest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Procedure(value = "get_employees")
    Object[] genericSingleObjectFromResultSet();

    @Procedure(value = "get_employees")
    List<Object[]> genericObjectsFromResultSet();

    @Procedure(value = "get_employees")
    List<Employee> entityListFromResultSet();

    @Procedure(value = "get_employees", outputParameterName = "p_employees")
    List<Employee> namedOutputParameter();

    @Procedure(value = "get_single_employee")
    Employee singleEntityFromResultSet();

    @Procedure(value = "get_single_employee")
    List<Employee> entityListFromSingleRowResultSet();

    @Procedure(value = "get_employees_count")
    Integer noResultSet();
}