package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorOfSalary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactorOfSalaryDAO extends DAO<FactorOfSalary> {
    private static Connection connection;
    private static FactorOfSalaryDAO factorOfSalaryDAO;

    private FactorOfSalaryDAO() throws SQLException {
        connection = DatabaseConnection.getDatabaseConnection().getConnnection();
    }

    public static FactorOfSalaryDAO getFactorOfSalaryDAO() {
        if (factorOfSalaryDAO == null) {
            try {
                factorOfSalaryDAO = new FactorOfSalaryDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return factorOfSalaryDAO;
    }

    @Override
    public List<FactorOfSalary> getAll() throws SQLException {
        List<FactorOfSalary> res = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.factor_of_salary;"
            );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(getFactorOfSalaryByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public FactorOfSalary getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.factor_of_salary WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return getFactorOfSalaryByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FactorOfSalary update(FactorOfSalary elem) {
        int id = elem.getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.factor_of_salary SET name=?, info=?, company_id=? WHERE id=?;"
            );
            preparedStatement.setString(1, elem.getName());
            preparedStatement.setString(2, elem.getInfo());
            preparedStatement.setInt(3, elem.getCompany().getId());
            preparedStatement.setInt(4, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM public.factor_of_salary WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(FactorOfSalary elem) {
        delete(elem.getId());
    }

    @Override
    public FactorOfSalary create(FactorOfSalary elem) {
        String name = elem.getName();
        String info = elem.getInfo();
        int companyId = elem.getCompany().getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.factor_of_salary (name, info, company_id) VALUES (?, ?, ?) RETURNING id;"
            );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, info);
            preparedStatement.setInt(3, companyId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private FactorOfSalary getFactorOfSalaryByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String info = rs.getString("info");
        int companyId = rs.getInt("company_id");
        Company company = CompanyDAO.getCompanyDAO().getById(companyId);
        return new FactorOfSalary(id, name, info, company);
    }

    public static void main(String [] args) {
        FactorOfSalaryDAO dao = FactorOfSalaryDAO.getFactorOfSalaryDAO();
        FactorOfSalary factor = dao.create(new FactorOfSalary(0, "name", "info", CompanyDAO.getCompanyDAO().getById(2)));
        System.out.println(factor.getId());
    }
}
