package com.slanda.employeemanagement.api.models.responses;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Base class for error handling
 */
@Data
@SuperBuilder
public class BaseErrorResponse implements Serializable {
    private String status;
    private Integer code;
}
