package com.slanda.employeemanagement.api.util.exceptions;

import static com.slanda.employeemanagement.api.util.constants.EmployeeErrorConstants.EMPLOYEE_NOT_FOUND;

/**
 * Custom exception for when an employee is not in the database
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String id) {
        super(String.format(EMPLOYEE_NOT_FOUND, id));
    }
}
