package com.slanda.employeemanagement.api.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import static com.slanda.employeemanagement.api.util.constants.EmployeeErrorConstants.*;

/**
 * Class with the attributes required to register an employee and the
 * validations to be applied in the body of the request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest implements Serializable {

    @Size(min = 2, max = 30, message = VALIDATION_SIZE_MAX_30)
    @NotBlank(message = FIRST_NAME_REQUIRED)
    private String firstName;

    private String secondName;

    @Size(min = 2, max = 30, message = VALIDATION_SIZE_MAX_30)
    @NotBlank(message = PATERNAL_LAST_NAME_REQUIRED)
    private String paternalLastName;

    private String maternalLastName;

    @Size(min = 2, max = 15, message = VALIDATION_SIZE_MAX_15)
    @NotBlank(message = SEX_REQUIRED)
    private String sex;

    @NotNull(message = DATE_OF_BIRTH_REQUIRED)
    private Date dateBirth;

    @Size(min = 2, max = 50, message = VALIDATION_SIZE_MAX_50)
    @NotBlank(message = POSITION_REQUIRED)
    private String position;

}
