package com.slanda.employeemanagement.api.util.constants;

/**
 * Constants to API logs
 */
public final class LogConstants {

    public final static String LINE_RETURN = "\n";
    public final static String ERROR_ADVICE = "Processing error %s : %s  %s";
    public final static String REQUEST_GET_ALL = "Request received to list all employees";
    public final static String REQUEST_GET_BY_ID = "Request received get an employee by id : %s";
    public final static String REQUEST_SAVE = "Request received to save %s employee(s)";
    public final static String REQUEST_UPDATE = "Request received to update an employee with id : %s";
    public final static String REQUEST_DELETE = "Request received to delete the employee with id: %s";
    public final static String FIND_ALL_EMPLOYEES = "The employees in the database were consulted.";
    public final static String FIND_EMPLOYEE_BY_ID = "The employee was consulted with the id : %s";
    public final static String SAVE_EMPLOYEE_START = "Entering employee creation";
    public final static String SAVE_EMPLOYEE_END = "Created employee with id : %s";
    public final static String SAVE_EMPLOYEES_END = "Created employees with ids : %s";
    public final static String UPDATE_EMPLOYEE_START = "Entering employee updating";
    public final static String UPDATE_EMPLOYEE_END = "Updated employee with id : %s";
    public final static String DELETE_EMPLOYEE_START = "Entering employee deleting";
    public final static String REQUEST_HEADERS_START = "== Request Headers ===";
    public final static String REQUEST_HEADERS= "%s: %s";
    public final static String REQUEST_HEADERS_END = "== End of Request Headers ===";
}
