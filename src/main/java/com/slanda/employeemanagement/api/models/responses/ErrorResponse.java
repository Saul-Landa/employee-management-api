package com.slanda.employeemanagement.api.models.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Class to handle a single error message
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class ErrorResponse extends BaseErrorResponse {
    private String error;
}
