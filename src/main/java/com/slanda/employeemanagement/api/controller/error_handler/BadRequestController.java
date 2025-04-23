package com.slanda.employeemanagement.api.controller.error_handler;

import com.slanda.employeemanagement.api.models.responses.BaseErrorResponse;
import com.slanda.employeemanagement.api.models.responses.ErrorResponse;
import com.slanda.employeemanagement.api.models.responses.ErrorsResponse;
import com.slanda.employeemanagement.api.util.LogUtil;
import com.slanda.employeemanagement.api.util.StringUtil;
import com.slanda.employeemanagement.api.util.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

import static com.slanda.employeemanagement.api.util.constants.EmployeeErrorConstants.*;
import static com.slanda.employeemanagement.api.util.constants.LogConstants.ERROR_ADVICE;

/**
 * Class to control custom errors
 */
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    /**
     * Handles errors that occur when an argument is not valid in the request body.
     * @param notValidException The exception produced
     * @return A response object with errors and additional information such as status and error code.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleValidation(MethodArgumentNotValidException notValidException) {
        LogUtil.error(ERROR_ADVICE, ARGUMENT_NO_VALID,
                ARGUMENT_NO_VALID_DETAIL, StringUtil.getSafeTrace(notValidException));

        var errors = new ArrayList<String>();

        notValidException.getAllErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));

        return ErrorsResponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    /**
     * Handles errors that occur when an employee does not exit in the database.
     * @param notFoundException The custom exception indicating that the employee was not found.
     * @return A response object with error message and additional information such as status and error code.
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public BaseErrorResponse handleNotFound(EmployeeNotFoundException notFoundException) {
        LogUtil.error(ERROR_ADVICE, NOT_FOUND,
                notFoundException.getMessage(), StringUtil.getSafeTrace(notFoundException));

        return ErrorResponse.builder()
                .error(notFoundException.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

}
