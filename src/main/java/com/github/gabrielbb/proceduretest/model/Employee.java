package com.github.gabrielbb.proceduretest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
