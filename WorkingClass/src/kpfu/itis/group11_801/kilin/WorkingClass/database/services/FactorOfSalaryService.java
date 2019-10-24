package kpfu.itis.group11_801.kilin.workingClass.database.services;

import kpfu.itis.group11_801.kilin.workingClass.database.DAO.FactorOfSalaryDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorOfSalary;

import java.sql.SQLException;
import java.util.List;

public class FactorOfSalaryService {
    public FactorOfSalary getOrCreate(FactorOfSalary fs) {
        try {
            for (FactorOfSalary factorOfSalary : FactorOfSalaryDAO.getFactorOfSalaryDAO().getAll()) {
                if (factorOfSalary.getCompany().equals(fs.getCompany()) && factorOfSalary.getName().equals(fs.getName())) {
                    return factorOfSalary;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return FactorOfSalaryDAO.getFactorOfSalaryDAO().create(fs);
    }
}
