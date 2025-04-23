package com.slanda.employeemanagement.api.service;

import java.util.List;

/**
 * Interface with the definition of the main methods of a CRUD
 * @param <RQ> Object Request
 * @param <RS> Object Response
 */
public interface ICrudService<RQ, RS> {
    List<RS> findAll();
    RS findById(Long id);
    RS create(RQ request);
    List<RS> createAll(List<RQ> requests);
    RS update(RQ request, Long id);
    void delete(Long id);
}
