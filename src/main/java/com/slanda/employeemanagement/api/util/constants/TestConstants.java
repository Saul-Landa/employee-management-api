package com.slanda.employeemanagement.api.util.constants;

import java.util.Date;

/**
 * Class with constants for the tests
 */
public final class TestConstants {

    public static final String FIRST_NAME = "Saul";
    public static final String FIRST_NAME_UPDATED = "Robbie";
    public static final String SECOND_NAME = "Joel";
    public static final String PATERNAL_LAST_NAME = "Doe";
    public static final String PATERNAL_LAST_NAME_UPDATED = "Rich";
    public static final String MATERNAL_LAST_NAME = "Williams";
    public static final Long EMPLOYEE_ID = 1L;
    public static final Integer EMPLOYEE_ID_FOR_REST = 1;
    public static final Long EMPLOYEE_SECOND_ID = 2L;
    public static final String EMPLOYEE_SEX = "Male";
    public static final Date DATE_OF_BIRTH = new Date(983414821000L);
    public static final Integer EMPLOYEE_AGE = 25;
    public static final String POSITION = "Developer";

    public static final String EMPLOYEE_END_POINT = "/employees";
    public static final String EMPLOYEE_END_POINT_ID = "/employees/{id}";
}
