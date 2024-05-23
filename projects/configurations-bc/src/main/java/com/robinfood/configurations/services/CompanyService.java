package com.robinfood.configurations.services;

import com.robinfood.configurations.models.Company;

import java.util.List;

public interface CompanyService {

    /**
     * Get company information
     * @param id identifier company
     * @return Obj company
     */
    Company findById(Long id);

    /**
     * Get all company information
     * @param status Identifies the status of the company
     * @return
     */
    List<Company> findByAll(Long status);
}
