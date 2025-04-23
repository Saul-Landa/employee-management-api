package com.slanda.employeemanagement.api.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Employee entity used to map the data in the database
 */
@Data
@Entity
@Table(name = "employees")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String firstName;

    private String secondName;

    @Column(length = 30, nullable = false)
    private String paternalLastName;
    @Column(length = 30)
    private String maternalLastName;
    private Integer age;
    @Column(length = 20, nullable = false)
    private String sex;
    @Column(nullable = false)
    private Date dateBirth;
    @Column(length = 50, nullable = false)
    private String position;
}
