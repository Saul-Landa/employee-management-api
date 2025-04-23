package com.slanda.employeemanagement.api.util.constants;

/**
 * Class with constants for API definition
 */
public final class APIDefinitionConstants {

    public final static String API_TITLE = "Employee Management API";
    public final static String API_VERSION = "1.0.0";
    public final static String API_DESCRIPTION = "Project for managing employee data.";
    public final static String TAG_EMPLOYEE = "Employee";
    public final static String CODE_HTTP_400 = "400";
    public final static String CODE_HTTP_200 = "200";
    public final static String APPLICATION_JSON = "application/json";
    public final static String GET_ALL_DESCRIPTION = "Retrieves the list of all registered employees.";
    public final static String GET_BY_ID_DESCRIPTION = "Retrieves an employee by id.";
    public final static String GET_BY_ID_RETURN = "Retrieves a object employee response.";
    public final static String GET_BY_ID_ERROR = "When the employee does not exist in the database with this id, we responds this.";
    public final static String SAVE_DESCRIPTION = "Inserts one or several employees in a single request.";
    public final static String SAVE_RETURN = "Provides a list of employee response objects with saved information.";
    public final static String SAVE_ERROR = "When the request have and invalid field, we response this.";
    public final static String UPDATE_DESCRIPTION = "Update any employee data.";
    public final static String UPDATE_RETURN = "Provides an object employee response with updated information.";
    public final static String UPDATE_ERROR = "When the employee to updated does not exist in the database with this id, we responds this.";
    public final static String DELETE_DESCRIPTION = "Delete an employee, requires as parameter the registration id.";
    public final static String EMPLOYEE_ID_GET = "Employee Id to be searched";
    public final static String EMPLOYEE_ID_UPDATE = "Employee Id to be updated";
    public final static String EMPLOYEE_ID_DELETE = "Employee Id to be deleted";
}
