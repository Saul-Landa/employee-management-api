package com.slanda.employeemanagement.api.service.impl;

import com.slanda.employeemanagement.api.domain.entities.EmployeeEntity;
import com.slanda.employeemanagement.api.domain.repositories.EmployeeRepository;
import com.slanda.employeemanagement.api.models.requests.EmployeeRequest;
import com.slanda.employeemanagement.api.models.responses.EmployeeResponse;
import com.slanda.employeemanagement.api.service.IEmployeeService;
import com.slanda.employeemanagement.api.util.LogUtil;
import com.slanda.employeemanagement.api.util.exceptions.EmployeeNotFoundException;
import com.slanda.employeemanagement.api.util.helpers.EmployeeHelper;
import com.slanda.employeemanagement.api.util.mappers.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.slanda.employeemanagement.api.util.constants.LogConstants.*;

/**
 * Class representing a service for managing employees and business logic
 */
@Service
@Transactional
@AllArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeHelper employeeHelper;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    /**
     * List all registered employees
     * @return A list of all the employees in the database
     */
    @Override
    public List<EmployeeResponse> findAll() {
        var employees = employeeRepository.findAll()
                .stream().map(employeeMapper::entityToResponse)
                .toList();

        LogUtil.info(FIND_ALL_EMPLOYEES);
        return employees;
    }

    /**
     * Searches for an employee in the database using the id
     * @param id Employee id
     * @return An object with the information of an employee
     */
    @Override
    public EmployeeResponse findById(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id.toString()));

        LogUtil.info(FIND_EMPLOYEE_BY_ID, id);
        return employeeMapper.entityToResponse(employee);
    }

    /**
     * Register one employee in the database
     * @param request Object with employee information
     * @return An object with the employee saved in the database
     */
    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        LogUtil.info(SAVE_EMPLOYEE_START);
        var employee = employeeMapper.requestToEntity(request);
        employee.setAge(employeeHelper.calculateAge(employee.getDateBirth()));

        EmployeeEntity employeeSaved = employeeRepository.save(employee);
        LogUtil.info(SAVE_EMPLOYEE_END, employeeSaved.getId());

        return employeeMapper.entityToResponse(employeeSaved);
    }

    /**
     * Register one or more employees in the database
     * @param requests List of employees to save in the database
     * @return A list with the employees saved in the database
     */
    @Override
    public List<EmployeeResponse> createAll(List<EmployeeRequest> requests) {
        LogUtil.info(SAVE_EMPLOYEE_START);
        // Convert list of objects request to list of entities
        List<EmployeeEntity> employees = requests.stream()
                .map(employee -> {
                    EmployeeEntity employeeEntity = employeeMapper.requestToEntity(employee);
                    employeeEntity.setAge(employeeHelper.calculateAge(employeeEntity.getDateBirth()));

                    return employeeEntity;
                })
                .toList();

        var employeesSaved = employeeRepository.saveAll(employees);
        LogUtil.info(SAVE_EMPLOYEES_END, employeesSaved.stream().map(EmployeeEntity::getId).toList());

        return employeesSaved.stream()
                .map(employeeMapper::entityToResponse)
                .toList();
    }

    /**
     * Update employee information
     * @param request Employee object to be updated
     * @param id Employee id
     * @return An employee object with the updated information
     */
    @Override
    public EmployeeResponse update(EmployeeRequest request, Long id) {
        LogUtil.info(UPDATE_EMPLOYEE_START);
        // Search if the id exists in the database
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id.toString()));

        // Convert object request to entity
        EmployeeEntity employee = employeeMapper.requestToEntity(request);
        employee.setId(id);
        employee.setAge(employeeHelper.calculateAge(employee.getDateBirth()));

        var employeeUpdated = employeeRepository.save(employee);
        LogUtil.info(UPDATE_EMPLOYEE_END, employeeUpdated.getId());

        return employeeMapper.entityToResponse(employeeUpdated);
    }

    /**
     * Delete an employee from the database
     * @param id Employee id
     */
    @Override
    public void delete(Long id) {
        LogUtil.info(DELETE_EMPLOYEE_START);
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id.toString()));

        employeeRepository.delete(employee);
    }
}
