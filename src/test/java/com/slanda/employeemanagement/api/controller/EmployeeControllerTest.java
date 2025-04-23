package com.slanda.employeemanagement.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slanda.employeemanagement.api.models.requests.EmployeeRequest;
import com.slanda.employeemanagement.api.models.responses.EmployeeResponse;
import com.slanda.employeemanagement.api.service.impl.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.slanda.employeemanagement.api.util.constants.TestConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class to test the controller
 */
@Slf4j
@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Method for testing the end point of listing all employees
     */
    @Test
    void listEmployeesTest() {
        try {
            // given
            List<EmployeeResponse> employeeList = List.of(
                    buildEmployeeResponse(), buildEmployeeResponse(), buildEmployeeResponse(),
                    buildEmployeeResponse(), buildEmployeeResponse(), buildEmployeeResponse()
            );
            // mock to method of repository for list all
            given(employeeService.findAll()).willReturn(employeeList);

            // when
            ResultActions response = mockMvc.perform(get(EMPLOYEE_END_POINT));

            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.size()", is(employeeList.size())));
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    /**
     * Method for testing the end point of find by id
     */
    @Test
    void getByIdTest() {
        try {
            // given
            EmployeeResponse employeeResponse = buildEmployeeResponse();
            given(employeeService.findById(EMPLOYEE_ID)).willReturn(employeeResponse);

            // when
            ResultActions response = mockMvc.perform(get(EMPLOYEE_END_POINT_ID, EMPLOYEE_ID));

            // then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.id", is(EMPLOYEE_ID_FOR_REST)))
                    .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                    .andExpect(jsonPath("$.paternalLastName", is(PATERNAL_LAST_NAME)));
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    /**
     * Method for testing the end point of save employees
     */
    @Test
    void saveEmployee() {
        try {
            // given
            List<EmployeeRequest> employeeRequests = List.of(buildEmployeeRequest(), buildEmployeeRequest());
            List<EmployeeResponse> employeeResponses = List.of(buildEmployeeResponse(), buildEmployeeResponse());
            given(employeeService.createAll(employeeRequests)).willReturn(employeeResponses);

            // when
            ResultActions response = mockMvc.perform(post(EMPLOYEE_END_POINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(employeeRequests)));

            // then
            response.andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.[0].id", is(EMPLOYEE_ID_FOR_REST)))
                    .andExpect(jsonPath("$.[0].firstName", is(FIRST_NAME)))
                    .andExpect(jsonPath("$.[1].age", is(EMPLOYEE_AGE)));

        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    /**
     * Method for testing the end point of update an employee
     */
    @Test
    void updateEmployeeTest() {
        try {
            // given
            EmployeeRequest employeeRequest = buildEmployeeRequest();
            EmployeeResponse employeeResponse = buildEmployeeResponse();
            given(employeeService.update(employeeRequest, EMPLOYEE_ID)).willReturn(employeeResponse);

            // when
            ResultActions response = mockMvc.perform(put(EMPLOYEE_END_POINT_ID, EMPLOYEE_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(employeeRequest)));

            // then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.id", is(EMPLOYEE_ID_FOR_REST)))
                    .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                    .andExpect(jsonPath("$.paternalLastName", is(PATERNAL_LAST_NAME)));

        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    /**
     * Method for testing the end point of delete an employee
     */
    @Test
    void deleteEmployeeTest() {
        try {
            // given
            willDoNothing().given(employeeService).delete(EMPLOYEE_ID);

            // when
            ResultActions response = mockMvc.perform(delete(EMPLOYEE_END_POINT_ID, EMPLOYEE_ID));

            // then
            response.andExpect(status().isNoContent())
                    .andDo(print());
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    /**
     * Create a test employee request
     * @return Employee's object request
     */
    private EmployeeRequest buildEmployeeRequest() {
        return EmployeeRequest.builder()
                .firstName(FIRST_NAME)
                .secondName(SECOND_NAME)
                .paternalLastName(PATERNAL_LAST_NAME)
                .maternalLastName(MATERNAL_LAST_NAME)
                .sex(EMPLOYEE_SEX)
                .dateBirth(DATE_OF_BIRTH)
                .position(POSITION)
                .build();
    }

    /**
     * Create a test employee response
     * @return Employee's object response
     */
    private EmployeeResponse buildEmployeeResponse() {
        return EmployeeResponse.builder()
                .id(EMPLOYEE_ID)
                .firstName(FIRST_NAME)
                .secondName(SECOND_NAME)
                .paternalLastName(PATERNAL_LAST_NAME)
                .maternalLastName(MATERNAL_LAST_NAME)
                .sex(EMPLOYEE_SEX)
                .dateBirth(DATE_OF_BIRTH)
                .position(POSITION)
                .age(EMPLOYEE_AGE)
                .build();
    }
}
