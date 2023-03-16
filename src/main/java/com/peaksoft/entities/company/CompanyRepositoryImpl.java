package com.peaksoft.entities.company;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Company> getCompanies() {
        return entityManager.createQuery("from Company", Company.class).getResultList();
    }
    public void saveCompany(Company company){
        entityManager.persist(company);
    }
    public Company getCompanyById(Long id){
       return entityManager.find(Company.class, id);
    }
    public void updateCompany(Company updatedCompany, Long  id){
        Company companyToBeUpdated = entityManager.find(Company.class, id);
        companyToBeUpdated.setCompanyName(updatedCompany.getCompanyName());
        companyToBeUpdated.setCourses(updatedCompany.getCourses());
        companyToBeUpdated.setLocatedCountry(updatedCompany.getLocatedCountry());
    entityManager.merge(companyToBeUpdated);
    }
    public void deleteCompanyById(Long id){
        entityManager.remove(getCompanyById(id));
    }



}
