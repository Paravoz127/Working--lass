package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.Image;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.Date;

import java.sql.*;
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.user WHERE id>0;"
            );
            ResultSet rs = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.user WHERE boss_id=?;"
            );
            preparedStatement.setInt(1, u.getId());
            ResultSet rs = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.user WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return getUserByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.user WHERE email=?;"
            );
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
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
        String photoPath = null;
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
            photoPath = elem.getImage().getImagePath();
        }
        Date date = elem.getDate();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.user SET first_name=?, second_name=?, email=?, password=?, birthday=?, "
                            +"photo_path=?, company_id=?, boss_id=? WHERE id=?;"
            );
            preparedStatement.setString(1, elem.getFirstName());
            preparedStatement.setString(2, elem.getSecondName());
            preparedStatement.setString(3, elem.getLogin());
            preparedStatement.setString(4, elem.getPassword());
            preparedStatement.setDate(5, new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDay()));
            if (photoPath != null) {
                preparedStatement.setString(6, photoPath);
            } else {
                preparedStatement.setNull(6, Types.VARCHAR);
            }
            preparedStatement.setInt(7, companyId);
            preparedStatement.setInt(8, boss_id);
            preparedStatement.setInt(9, id);

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
                    "DELETE FROM public.user WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
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
        String photoPath = null;
        if (elem.getImage() != null) {
            photoPath = elem.getImage().getImagePath();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.user (first_name, second_name, email, password, birthday, company_id, photo_path, boss_id) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id"
            );

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, secondName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setDate(5, new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDay()));
            preparedStatement.setInt(6, companyId);
            if (photoPath != null) {
                preparedStatement.setString(7, photoPath);
            } else {
                preparedStatement.setNull(7, Types.VARCHAR);
                System.out.println("setted null");
            }
            preparedStatement.setInt(8, boss_id);

            ResultSet rs = preparedStatement.executeQuery();
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
        User user = new User(0, "Name1", "sName1", "logsdfghin", "1234", date, CompanyDAO.getCompanyDAO().getById(2), dao.getById(10), null);
        user = dao.create(user);
        System.out.println(user.getId());
    }
}