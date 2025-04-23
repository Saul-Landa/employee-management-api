package com.slanda.employeemanagement.api.controller;

import com.slanda.employeemanagement.api.models.requests.EmployeeRequest;
import com.slanda.employeemanagement.api.models.responses.EmployeeResponse;
import com.slanda.employeemanagement.api.models.responses.ErrorResponse;
import com.slanda.employeemanagement.api.models.responses.ErrorsResponse;
import com.slanda.employeemanagement.api.service.IEmployeeService;
import com.slanda.employeemanagement.api.util.LogUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.slanda.employeemanagement.api.util.constants.APIDefinitionConstants.*;
import static com.slanda.employeemanagement.api.util.constants.LogConstants.*;

/**
 * Class that exposes the end points to the http client.
 */
@RestController
@AllArgsConstructor
@Tag(name = TAG_EMPLOYEE)
@RequestMapping("employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    /**
     * End point to list all registered employees.
     * @return A list of employees registered in the database.
     */
    @Operation(summary = GET_ALL_DESCRIPTION)
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        LogUtil.info(REQUEST_GET_ALL);
        return ResponseEntity.ok(employeeService.findAll());
    }

    /**
     * End point to searches for an employee in the database using the id
     * @param id The employee id
     * @return A response object with the information of an employee
     */
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = CODE_HTTP_400,
                    description = GET_BY_ID_ERROR,
                    content = {
                            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = CODE_HTTP_200,
                    description = GET_BY_ID_RETURN,
                    content = {
                            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = EmployeeResponse.class))
                    }
            )
    })
    @Operation(summary = GET_BY_ID_DESCRIPTION)
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(
            @Parameter(description = EMPLOYEE_ID_GET) @PathVariable Long id) {
        LogUtil.info(REQUEST_GET_BY_ID, id);
        return ResponseEntity.ok(employeeService.findById(id));
    }

    /**
     * End point to register one or more employees in the database
     * @param employees List of employees to save in the database
     * @return A list with the employees saved in the database
     */
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = CODE_HTTP_400,
                    description = SAVE_ERROR,
                    content = {
                            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = ErrorsResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = CODE_HTTP_200,
                    description = SAVE_RETURN,
                    content = {
                            @Content(
                                    mediaType = APPLICATION_JSON,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = EmployeeResponse.class)
                                    ))
                    }
            )
    })
    @Operation(summary = SAVE_DESCRIPTION)
    @PostMapping
    public ResponseEntity<List<EmployeeResponse>> save(@Valid @RequestBody List<EmployeeRequest> employees) {
        LogUtil.info(REQUEST_SAVE, employees.size());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.createAll(employees));
    }

    /**
     * End point to update employee information
     * @param employeeRequest Employee object to be updated
     * @param id Employee id
     * @return An employee object with the updated information
     */
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = CODE_HTTP_400,
                    description = UPDATE_ERROR,
                    content = {
                            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = ErrorsResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = CODE_HTTP_200,
                    description = UPDATE_RETURN,
                    content = {
                            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = EmployeeResponse.class))
                    }
            )
    })
    @Operation(summary = UPDATE_DESCRIPTION)
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(
            @Valid @RequestBody EmployeeRequest employeeRequest,
            @Parameter(description = EMPLOYEE_ID_UPDATE) @PathVariable Long id) {
        LogUtil.info(REQUEST_UPDATE, id);
        return ResponseEntity.ok(employeeService.update(employeeRequest, id));
    }

    /**
     * End point to delete an employee from the database
     * @param id Employee id
     * @return A no content status
     */
    @Operation(summary = DELETE_DESCRIPTION)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = EMPLOYEE_ID_DELETE) @PathVariable Long id) {
        LogUtil.info(REQUEST_DELETE, id);
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
