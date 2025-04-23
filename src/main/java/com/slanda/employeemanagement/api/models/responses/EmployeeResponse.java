package com.slanda.employeemanagement.api.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Class with attributes that are exposed in employee endpoints
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse implements Serializable {

    private Long id;
    private String firstName;
    private String secondName;
    private String paternalLastName;
    private String maternalLastName;
    private Integer age;
    private String sex;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateBirth;
    private String position;
}
