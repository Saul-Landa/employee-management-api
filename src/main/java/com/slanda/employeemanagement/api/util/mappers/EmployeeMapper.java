package com.slanda.employeemanagement.api.util.mappers;

import com.slanda.employeemanagement.api.domain.entities.EmployeeEntity;
import com.slanda.employeemanagement.api.models.requests.EmployeeRequest;
import com.slanda.employeemanagement.api.models.responses.EmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Class for mapping the types of requests, responses and entities of an employee.
 */
@Component
public class EmployeeMapper {

    /**
     * Converts an entity class to a response class
     * @param employeeEntity Employee entity class object
     * @return Employee response class object
     */
    public EmployeeResponse entityToResponse(EmployeeEntity employeeEntity) {
        var employeeResponse = new EmployeeResponse();
        // Copy properties from entity to response
        BeanUtils.copyProperties(employeeEntity, employeeResponse);

        return employeeResponse;
    }

    /**
     * Converts a request class to an entity class
     * @param employeeRequest Employee request class object
     * @return Employee entity class object
     */
    public EmployeeEntity requestToEntity(EmployeeRequest employeeRequest) {
        var employeeEntity = new EmployeeEntity();
        // Copy properties from request to entity
        BeanUtils.copyProperties(employeeRequest, employeeEntity);

        return employeeEntity;
    }
}
