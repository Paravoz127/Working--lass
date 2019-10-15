package kpfu.itis.group11_801.kilin.WorkingClass.database.DAO;

import kpfu.itis.group11_801.kilin.WorkingClass.database.Company;
import kpfu.itis.group11_801.kilin.WorkingClass.database.FactorOfSalary;
import kpfu.itis.group11_801.kilin.WorkingClass.database.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserDAO extends DAO<User>{
    private static Connection connection;
    private static UserDAO userDAO;

    private UserDAO() throws SQLException {
        connection = DatabaseConnection.getDatabaseConnection().getConnnection();
    }

    public static UserDAO getUserDAO() {
        if (userDAO == null) {
            try {
                userDAO = new UserDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userDAO;
    }

    @Override
    public List<User> getAll() {
        List<User> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.user;");
            while (rs.next()) {
                res.add(getUserByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public User getById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.user WHERE id=" + id + ";");
            rs.next();
            return getUserByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User update(User elem) {
        int id = elem.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.user SET "
                    + "first_name='" + elem.getFirstName() + "', "
                    + "second_name='" + elem.getSecondName() + "', "
                    + "login='" + elem.getLogin() + "', "
                    + "password='" + elem.getPassword() + "', "
                    + "birthday='" + elem.getDate() + "', "
                    + "company_id='" + elem.getCompany().getId() + "', "
                    + "boss_id='" + elem.getBoss().getId() + "' WHERE id=" + id + ";"
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
            statement.executeQuery("DELETE FROM public.user WHERE id=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User elem) {
        delete(elem.getId());
    }

    @Override
    public User create(User elem) {
        String firstName = elem.getFirstName();
        String secondName = elem.getSecondName();
        String login = elem.getLogin();
        String password = elem.getPassword();
        Date date = elem.getDate();
        Company company = elem.getCompany();
        User boss = elem.getBoss();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO public.user " +
                    "(first_name, second_name, login, password, birthday, company_id, boss_id) "
                            + "VALUES "
                            + "('" + firstName + "', "
                            + "'" + secondName + "', "
                            + "'" + login + "', "
                            + "'" + password + "', "
                            + "'" + date + "', "
                            + "'" + company.getId() + "', "
                            + "'" + boss.getId() + "');"
                    );
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User getUserByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String secondName = rs.getString("second_name");
        String login = rs.getString("login");
        String password = rs.getString("password");
        Date birthDay = rs.getDate("birthday");
        Company company = (new CompanyDAO).getById(rs.getInt("company_id"));
        User boss = getById(rs.getInt("boss_id"));
        //TODO factors
        return new User(id, firstName, secondName, login, password, birthDay, company, boss, null);
    }
}
