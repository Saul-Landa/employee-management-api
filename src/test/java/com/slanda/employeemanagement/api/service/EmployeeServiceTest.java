package com.slanda.employeemanagement.api.service;

import com.slanda.employeemanagement.api.domain.entities.EmployeeEntity;
import com.slanda.employeemanagement.api.domain.repositories.EmployeeRepository;
import com.slanda.employeemanagement.api.models.requests.EmployeeRequest;
import com.slanda.employeemanagement.api.models.responses.EmployeeResponse;
import com.slanda.employeemanagement.api.service.impl.EmployeeService;
import com.slanda.employeemanagement.api.util.helpers.EmployeeHelper;
import com.slanda.employeemanagement.api.util.mappers.EmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

import static com.slanda.employeemanagement.api.util.constants.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

/**
 * Class for testing service methods
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeHelper employeeHelper;
    @Mock
    private EmployeeMapper employeeMapper;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;

    private EmployeeEntity employeeEntity;
    private EmployeeRequest employeeRequest;
    private EmployeeResponse employeeResponse;

    /**
     * Initializes the objects to be used in the flow
     */
    @BeforeEach
    void setUp() {
        // Create entity, request and response objects of Employee
        employeeRequest = buildEmployeeRequest();
        employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeRequest, employeeEntity);
        employeeEntity.setId(EMPLOYEE_ID);
        employeeEntity.setAge(EMPLOYEE_AGE);
        employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employeeEntity, employeeResponse);
    }

    /**
     * Test the service method for listing all employees
     */
    @Test
    void listEmployeeTest() {
        // given
        given(employeeRepository.findAll())
                .willReturn(List.of(employeeEntity));
        // mock mapper to Response
        given(employeeMapper.entityToResponse(employeeEntity))
                .willReturn(employeeResponse);

        // when
        List<EmployeeResponse> employees = employeeService.findAll();

        // then
        assertThat(employees).isNotEmpty();
        assertEquals(employeeEntity.getAge(), employees.get(0).getAge());
        assertEquals(employeeEntity.getMaternalLastName(), employees.get(0).getMaternalLastName());
    }

    /**
     * Test the service method for find and employee by id
     */
    @Test
    void getEmployeeByIdTest() {
        // given
        given(employeeRepository.findById(EMPLOYEE_ID))
                .willReturn(Optional.of(employeeEntity));
        given(employeeMapper.entityToResponse(employeeEntity))
                .willReturn(employeeResponse);

        // when
        EmployeeResponse employeeResponse = employeeService.findById(EMPLOYEE_ID);

        // then
        assertEquals(employeeEntity.getId(), employeeResponse.getId());
        assertEquals(employeeEntity.getPosition(), employeeResponse.getPosition());
        assertEquals(employeeEntity.getFirstName(), employeeResponse.getFirstName());
    }

    /**
     * Test the service method for save many employees
     */
    @Test
    void saveEmployeeTest() {
        // given
        // create list of request to save employees
        EmployeeRequest employeeRequestSecond = employeeRequest;
        List<EmployeeRequest> employeesToSave = List.of(employeeRequest, employeeRequestSecond);
        // create list for results of save employees
        EmployeeEntity employeeSecond = employeeEntity;
        employeeSecond.setId(EMPLOYEE_SECOND_ID);
        List<EmployeeEntity> employeesSaved = List.of(employeeEntity, employeeSecond);

        // mock mapper to Entity
        given(employeeMapper.requestToEntity(employeeRequest))
                .willReturn(employeeEntity);
        given(employeeMapper.requestToEntity(employeeRequestSecond))
                .willReturn(employeeSecond);

        // mock method of repository to save many records
        given(employeeRepository.saveAll(employeesSaved))
                .willReturn(employeesSaved);

        EmployeeResponse employeeResponseSecond = employeeResponse;
        employeeResponseSecond.setId(EMPLOYEE_SECOND_ID);
        // mock mapper to Response
        given(employeeMapper.entityToResponse(employeeEntity))
                .willReturn(employeeResponse);
        given(employeeMapper.entityToResponse(employeeSecond))
                .willReturn(employeeResponseSecond);
        // mock method to calculate age
        given(employeeHelper.calculateAge(DATE_OF_BIRTH)).willReturn(EMPLOYEE_AGE);

        // when
        List<EmployeeResponse> employeeSaved = employeeService.createAll(employeesToSave);

        // then
        assertThat(employeeSaved.size()).isEqualTo(employeesToSave.size());
        assertEquals(employeeEntity.getId(), employeeSaved.get(0).getId());
        assertEquals(employeeEntity.getFirstName(), employeeSaved.get(0).getFirstName());
        assertEquals(EMPLOYEE_AGE, employeeSaved.get(0).getAge());
        assertEquals(EMPLOYEE_SECOND_ID, employeeSaved.get(1).getId());
        assertEquals(employeeSecond.getDateBirth(), employeeSaved.get(1).getDateBirth());
        assertEquals(EMPLOYEE_AGE, employeeSaved.get(1).getAge());
    }

    /**
     * Test the service method for update an employee
     */
    @Test
    void updateEmployeeTest() {
        // given
        given(employeeRepository.findById(EMPLOYEE_ID))
                .willReturn(Optional.of(employeeEntity));

        // add new values to object
        employeeEntity.setFirstName(FIRST_NAME_UPDATED);
        employeeEntity.setPaternalLastName(PATERNAL_LAST_NAME_UPDATED);
        employeeResponse.setFirstName(FIRST_NAME_UPDATED);
        employeeResponse.setPaternalLastName(PATERNAL_LAST_NAME_UPDATED);

        // mock mapper to Entity
        given(employeeMapper.requestToEntity(employeeRequest))
                .willReturn(employeeEntity);
        // mock method of repository to update
        given(employeeRepository.save(employeeEntity))
                .willReturn(employeeEntity);

        // mock mapper to Response
        given(employeeMapper.entityToResponse(employeeEntity))
                .willReturn(employeeResponse);

        // mock method to calculate age
        given(employeeHelper.calculateAge(DATE_OF_BIRTH)).willReturn(EMPLOYEE_AGE);

        // when
        EmployeeResponse employeeUpdated = employeeService.update(employeeRequest, EMPLOYEE_ID);

        // then
        assertEquals(FIRST_NAME_UPDATED, employeeUpdated.getFirstName());
        assertEquals(PATERNAL_LAST_NAME_UPDATED, employeeUpdated.getPaternalLastName());
        assertEquals(EMPLOYEE_AGE, employeeUpdated.getAge());
    }

    /**
     * Test the service method for delete an employee
     */
    @Test
    void deleteEmployeeTest() {
        // given
        when(employeeRepository.findById(EMPLOYEE_ID))
                .thenReturn(Optional.of(employeeEntity));
        willDoNothing().given(employeeRepository).delete(employeeEntity);

        // when
        employeeService.delete(EMPLOYEE_ID);

        // then
        verify(employeeRepository, times(1)).delete(employeeEntity);
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
}
