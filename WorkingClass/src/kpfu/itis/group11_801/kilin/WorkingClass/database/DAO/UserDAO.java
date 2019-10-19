package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.Image;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.Date;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            ResultSet rs = statement.executeQuery("SELECT * FROM public.user WHERE id>0;");
            while (rs.next()) {
                res.add(getUserByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<User> getEmployees(User u) {
        List<User> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.user WHERE boss_id=" + u.getId() + ";");
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
        if (id == 0) {return null;}
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

    public User getByEmail(String email) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.user WHERE email='" + email + "';");
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
        String photoPath = "null";
        int companyId;
        if (elem.getCompany() != null) {
            companyId = elem.getCompany().getId();
        } else {
            companyId = 0;
        }
        int boss_id;
        if (elem.getBoss() != null) {
            boss_id = elem.getBoss().getId();
        } else {
            boss_id = 0;
        }
        if (elem.getImage() != null) {
            photoPath = "'" + elem.getImage().getImagePath() + "'";
        }
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.user SET "
                    + "first_name='" + elem.getFirstName() + "', "
                    + "second_name='" + elem.getSecondName() + "', "
                    + "email='" + elem.getLogin() + "', "
                    + "password='" + elem.getPassword() + "', "
                    + "birthday='" + elem.getDate() + "', "
                    + "photo_path=" + photoPath + ", "
                    + "company_id=" + companyId + ", "
                    + "boss_id=" + boss_id + " WHERE id=" + id + ";"
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
        String email = elem.getLogin();
        String password = elem.getPassword();
        Date date = elem.getDate();
        int companyId;
        if (elem.getCompany() != null) {
            companyId = elem.getCompany().getId();
        } else {
            companyId = 0;
        }
        int boss_id;
        if (elem.getBoss() != null) {
            boss_id = elem.getBoss().getId();
        } else {
            boss_id = 0;
        }
        String photoPath = "null";
        if (elem.getImage() != null) {
            photoPath = "'" + elem.getImage().getImagePath() + "'";
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("INSERT INTO public.user " +
                    "(first_name, second_name, email, password, birthday, company_id, photo_path, boss_id) "
                            + "VALUES "
                            + "('" + firstName + "', "
                            + "'" + secondName + "', "
                            + "'" + email + "', "
                            + "'" + password + "', "
                            + "'" + date + "', "
                            + "" + companyId + ", "
                            + "" + photoPath + ", "
                            + "" + boss_id + ") RETURNING id;"
                    );
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
        String login = rs.getString("email");
        String password = rs.getString("password");
        Date birthDay = new Date(rs.getString("birthday"));
        Company company = (CompanyDAO.getCompanyDAO()).getById(rs.getInt("company_id"));
        Image image = null;
        String photoPath = rs.getString("photo_path");
        if (photoPath != null) {
            image = new Image(photoPath);
        }
        User boss;
        if (rs.getInt("boss_id") == 0) {
            boss = null;
        } else {
            boss = getById(rs.getInt("boss_id"));
        }

        return new User(id, firstName, secondName, login, password, birthDay, company, boss, image);
    }

    public static void main(String [] args) {
        UserDAO dao = UserDAO.getUserDAO();
        Date date = new Date("1999-1-2");
        User user = new User(0, "Name1", "sName1", "login", "1234", date, CompanyDAO.getCompanyDAO().getById(2), dao.getById(10), null);
        user = dao.create(user);
        System.out.println(user.getId());
    }
}
