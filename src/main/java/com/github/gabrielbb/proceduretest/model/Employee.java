package com.github.gabrielbb.proceduretest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
/*@NamedStoredProcedureQuery(name = "Employee.getEmployees",
        procedureName = "get_employees",
        resultClasses = Employee.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)*/
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
