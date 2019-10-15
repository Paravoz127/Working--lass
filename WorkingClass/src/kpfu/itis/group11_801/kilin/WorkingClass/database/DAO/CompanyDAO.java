package kpfu.itis.group11_801.kilin.WorkingClass.database.DAO;

import kpfu.itis.group11_801.kilin.WorkingClass.database.Company;
import kpfu.itis.group11_801.kilin.WorkingClass.database.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompanyDAO extends DAO<Company> {
    private static Connection connection;
    private static CompanyDAO companyDAO;

    private CompanyDAO() throws SQLException {
        connection = DatabaseConnection.getDatabaseConnection().getConnnection();
    }

    public static CompanyDAO getCompanyDAO() {
        if (companyDAO == null) {
            try {
                companyDAO = new CompanyDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return companyDAO;
    }

    @Override
    public List<Company> getAll() throws SQLException {
        List<Company> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.company;");
            while (rs.next()) {
                res.add(getCompanyByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public Company getById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.company WHERE id=" + id + ";");
            rs.next();
            return getCompanyByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Company update(Company elem) {
        int id = elem.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.company SET "
                    + "info='" + elem.getInfo() + "' WHERE id=" + id + ";"
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
            statement.executeQuery("DELETE FROM public.company WHERE id=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Company elem) {
        delete(elem.getId());
    }

    @Override
    public Company create(Company elem) {
        String info = elem.getInfo();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO public.company " +
                    "(info) "
                    + "VALUES "
                    + "('" + info + "');"
            );
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Company getCompanyByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String info = rs.getString("info");
        return new Company(id, info);
    }
}
