package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorOfSalary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.factor_of_salary;");
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.factor_of_salary WHERE id=" + id + ";");
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
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.factor_of_salary SET "
                    + "name='" + elem.getName() + "', "
                    + "info='" + elem.getInfo() + "', "
                    + "company_id=" + elem.getCompany().getId() + " WHERE id=" + id + ";"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public void delete(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("DELETE FROM public.factor_of_salary WHERE id=" + id + ";");
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("INSERT INTO public.factor_of_salary " +
                    "(name, info, company_id) "
                    + "VALUES "
                    + "('" + name + "',"
                    + "'" + info + "',"
                    + companyId + ") RETURNING id;"
            );
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
