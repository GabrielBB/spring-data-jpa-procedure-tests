package com.github.gabrielbb.proceduretest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "get_employees_mysql",
        procedureName = "get_employees",
        resultClasses = Employee.class
)
@NamedStoredProcedureQuery(
        name = "get_employees_oracle_postgres",
        procedureName = "get_employees",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        },
        resultClasses = Employee.class
)
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}