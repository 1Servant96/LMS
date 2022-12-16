package com.peaksoft.entities.company;

import java.util.List;

public interface CompanyRepository {
    List<Company> getCompanies();
    Company getCompanyById(Long id);
    void saveCompany(Company company);
    void updateCompany(Company company, Long id);
    void deleteCompanyById(Long id);



}
