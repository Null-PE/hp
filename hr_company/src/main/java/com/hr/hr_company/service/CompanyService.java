package com.hr.hr_company.service;

import com.hr.domain.company.Company;
import com.hr.hr_common.utils.IdWorker;
import com.hr.hr_company.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IdWorker idWorker;

    public Company add(Company company) {
        company.setId(String.valueOf(idWorker.nextId()));
        company.setCreateTime(new Date());
        company.setState(1);
        company.setAuditState("0");
        company.setBalance(0d);
        return companyDao.save(company);
    }

    public Company update(Company company) {
        return companyDao.save(company);
    }

    public Company findById(String id) {
        return companyDao.findById(id).get();
    }

    public void deleteById(String id) {
        companyDao.deleteById(id);
    }

    public List<Company> findAll() {
        return companyDao.findAll();
    }



}
