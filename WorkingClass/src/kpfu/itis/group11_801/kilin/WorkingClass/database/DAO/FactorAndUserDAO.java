package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FactorAndUserDAO extends DAO<FactorAndUser> {
    private static Connection connection;
    private static FactorAndUserDAO factorAndUserDAO;

    private FactorAndUserDAO() throws SQLException {
        connection = DatabaseConnection.getDatabaseConnection().getConnnection();
    }

    public static FactorAndUserDAO getFactorAndUserDAO() {
        if (factorAndUserDAO == null) {
            try {
                factorAndUserDAO = new FactorAndUserDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return factorAndUserDAO;
    }

    @Override
    public List<FactorAndUser> getAll() throws SQLException {
        List<FactorAndUser> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.factor_and_user;");
            while (rs.next()) {
                res.add(getFactorAndUserByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<FactorAndUser> getPositiveByUser(int id) throws SQLException {
        List<FactorAndUser> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.factor_and_user WHERE user_id=" + id + " AND value > 0;");
            while (rs.next()) {
                res.add(getFactorAndUserByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<FactorAndUser> getNegativeByUser(int id) throws SQLException {
        List<FactorAndUser> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.factor_and_user WHERE user_id=" + id + " AND value < 0;");
            while (rs.next()) {
                res.add(getFactorAndUserByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public FactorAndUser getById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.factor_and_user WHERE id=" + id + ";");
            rs.next();
            return getFactorAndUserByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FactorAndUser update(FactorAndUser elem) {
        int id = elem.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.factor_and_user SET "
                    + "user_id=" + elem.getUser().getId() + ", "
                    + "factor_id=" + elem.getFactorOfSalary().getId() + ", "
                    + "value =" + elem.getValue() + " WHERE id=" + id + ";"
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
            statement.executeQuery("DELETE FROM public.factor_and_user WHERE id=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(FactorAndUser elem) {
        delete(elem.getId());
    }

    @Override
    public FactorAndUser create(FactorAndUser elem) {
        int user_id = elem.getUser().getId();
        int factor_id = elem.getFactorOfSalary().getId();
        int value = elem.getValue();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("INSERT INTO public.factor_and_user " +
                    "(user_id, factor_id, value ) "
                    + "VALUES "
                    + "(" + user_id + ", " + factor_id + ", " + value + ") RETURNING id;"
            );
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private FactorAndUser getFactorAndUserByResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        User user = UserDAO.getUserDAO().getById(rs.getInt("user_id"));
        FactorOfSalary factor = FactorOfSalaryDAO.getFactorOfSalaryDAO().getById(rs.getInt("factor_id"));
        int value = rs.getInt("value");
        return new FactorAndUser(id, user, factor, value);
    }

    public static void main(String [] args) {
        FactorAndUserDAO dao = FactorAndUserDAO.getFactorAndUserDAO();
        FactorAndUser factorAndUser = dao.create(new FactorAndUser(0, UserDAO.getUserDAO().getById(11), FactorOfSalaryDAO.getFactorOfSalaryDAO().getById(1), 1000));
        System.out.println(factorAndUser.getUser().getId());
    }
}
