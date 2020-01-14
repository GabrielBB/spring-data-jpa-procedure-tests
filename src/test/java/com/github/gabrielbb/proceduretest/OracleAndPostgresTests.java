package com.github.gabrielbb.proceduretest;

import com.github.gabrielbb.proceduretest.model.Employee;
import com.github.gabrielbb.proceduretest.repository.EmployeeRepositoryWithRefCursor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
class OracleAndPostgresTests {

    @Autowired
    private EmployeeRepositoryWithRefCursor repo;

    @Test
    void testGenericSingleObjectFromResultSet() {
        Object[] employee = repo.genericSingleObjectFromResultSet();
        Assertions.assertThat(employee).isNotNull();
        Assertions.assertThat(employee).hasSize(2);
    }

    @Test
    void testGenericObjectsFromResultSet() {
        List<Object[]> employees = repo.genericObjectsFromResultSet();
        Assertions.assertThat(employees).isNotEmpty();
        Assertions.assertThat(employees.get(0)).hasSize(2);
    }

    @Test
    void testEntityListFromResultSet() {
        List<Employee> employees = repo.entityListFromResultSet();
        Assertions.assertThat(employees).isNotEmpty();
        Assertions.assertThat(employees.get(0)).isInstanceOf(Employee.class);
    }

    @Test
    void testNamedOutputParameter() {
        List<Employee> employees = repo.namedOutputParameter();
        Assertions.assertThat(employees).isNotEmpty();
        Assertions.assertThat(employees.get(0)).isInstanceOf(Employee.class);
    }

    @Test
    void testSingleEntityFromResultSet() {
        Employee employee = repo.singleEntityFromResultSet();
        Assertions.assertThat(employee).isNotNull();
    }

    @Test
    void testEntityListFromSingleRowResultSet() {
        List<Employee> employees = repo.entityListFromSingleRowResultSet();
        Assertions.assertThat(employees).isNotEmpty();
        Assertions.assertThat(employees.get(0)).isInstanceOf(Employee.class);
    }

    @Test
    void testNoResultSet() {
        int count = repo.noResultSet();
        Assertions.assertThat(count).isEqualTo(2);
    }

    @Test
    void testEntityListFromNamedProcedure() {
        List<Employee> employees = repo.entityListFromNamedProcedure();
        Assertions.assertThat(employees).hasSize(2);
        Assertions.assertThat(employees.get(0)).isInstanceOf(Employee.class);
    }
}
