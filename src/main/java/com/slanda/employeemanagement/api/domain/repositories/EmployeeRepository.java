package com.slanda.employeemanagement.api.domain.repositories;

import com.slanda.employeemanagement.api.domain.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface with the main JPA methods
 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
