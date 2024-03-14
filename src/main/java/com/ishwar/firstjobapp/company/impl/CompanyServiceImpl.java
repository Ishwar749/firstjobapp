package com.ishwar.firstjobapp.company.impl;

import com.ishwar.firstjobapp.company.Company;
import com.ishwar.firstjobapp.company.CompanyRepository;
import com.ishwar.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company oldCompany = companyOptional.get();
            setCompanyDetails(oldCompany,company);
            companyRepository.save(oldCompany);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    void setCompanyDetails(Company oldCompany, Company updatedCompany){
        // DON'T SET THE ID.
        oldCompany.setName(updatedCompany.getName());
        oldCompany.setDescription(updatedCompany.getDescription());
        oldCompany.setJobs(updatedCompany.getJobs());
    }
}
