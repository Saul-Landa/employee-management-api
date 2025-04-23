package com.slanda.employeemanagement.api.models.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Class to handle multiple error messages
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class ErrorsResponse extends BaseErrorResponse {
    private List<String> errors;
}
