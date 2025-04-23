package com.slanda.employeemanagement.api.service;

import com.slanda.employeemanagement.api.models.requests.EmployeeRequest;
import com.slanda.employeemanagement.api.models.responses.EmployeeResponse;

/**
 * Employee interface that extends the ICrudService interface to have the methods of a CRUD
 */
public interface IEmployeeService extends ICrudService<EmployeeRequest, EmployeeResponse> {
}
