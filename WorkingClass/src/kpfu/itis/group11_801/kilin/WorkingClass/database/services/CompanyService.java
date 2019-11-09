package kpfu.itis.group11_801.kilin.workingClass.database.services;

import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.CompanyRegistrationObject;
import kpfu.itis.group11_801.kilin.workingClass.database.DAO.CompanyDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.DAO.UserDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.User;

import java.sql.SQLException;
import java.util.List;

public class CompanyService {
    public Company getByName(String name) {
        CompanyDAO companyDAO = CompanyDAO.getCompanyDAO();
        return companyDAO.getByName(name);
    }

    public Company getCompanyById(int id) {
        CompanyDAO companyDAO = CompanyDAO.getCompanyDAO();
        return companyDAO.getById(id);
    }

    public CompanyRegistrationObject registrate(Company company, User user) {
        String name = company.getName();

        if (company.getName() == null || company.getName().equals("")) {
            return new CompanyRegistrationObject(null, 1);
        }

        if (company.getInfo() == null || company.getInfo().equals("")) {
            return new CompanyRegistrationObject(null, 2);
        }

        CompanyDAO companyDAO = CompanyDAO.getCompanyDAO();
        if (getByName(company.getName()) != null) {return new CompanyRegistrationObject(null, -1);}

        company = companyDAO.create(company);
        user.setCompany(company);
        new UserService().update(user);
        return new CompanyRegistrationObject(company, 0);
    }
}
