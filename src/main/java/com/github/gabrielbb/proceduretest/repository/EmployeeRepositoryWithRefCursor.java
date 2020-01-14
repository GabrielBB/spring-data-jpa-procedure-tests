package com.github.gabrielbb.proceduretest.repository;

import com.github.gabrielbb.proceduretest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface EmployeeRepositoryWithRefCursor extends JpaRepository<Employee, Integer> {

    @Procedure(value = "get_employees", refCursor = true)
    Object[] genericSingleObjectFromResultSet();

    @Procedure(value = "get_employees", refCursor = true)
    List<Object[]> genericObjectsFromResultSet();

    @Procedure(value = "get_employees", refCursor = true)
    List<Employee> entityListFromResultSet();

    @Procedure(value = "get_employees", outputParameterName = "p_employees", refCursor = true)
    List<Employee> namedOutputParameter();

    @Procedure(value = "get_single_employee", refCursor = true)
    Employee singleEntityFromResultSet();

    @Procedure(value = "get_single_employee", refCursor = true)
    List<Employee> entityListFromSingleRowResultSet();

    @Procedure(value = "get_employees_count")
    Integer noResultSet();

    @Procedure(name = "get_employees_oracle_postgres", refCursor = true)
    List<Employee> entityListFromNamedProcedure();
}