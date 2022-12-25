package com.peaksoft.entities.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }

    public Company getCompanyById(Long id){
        return companyRepository.getCompanyById(id);
    }
    public void saveCompany(Company company){
        companyRepository.saveCompany(company);
    }

    @Override
    public void updateCompany(Company company, Long id) {
    companyRepository.updateCompany(company,id);
    }

    public void deleteCompanyById(Long id){
        companyRepository.deleteCompanyById(id);
    }



}
