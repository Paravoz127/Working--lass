package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.Image;

import java.sql.*;
import java.util.ArrayList;
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.company;"
            );
            ResultSet rs = preparedStatement.executeQuery();
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
        if (id == 0) {return null;}
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.company WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return getCompanyByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Company getByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.company WHERE name=?;"
            );
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.company SET name=?, photo_path=?, info=? WHERE id=?;"
            );
            preparedStatement.setString(1, elem.getName());
            preparedStatement.setString(2, elem.getImage().getImagePath());
            preparedStatement.setString(3, elem.getInfo());
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
                    "DELETE FROM public.company WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
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
        String name = elem.getName();
        String info = elem.getInfo();
        String photo = elem.getImage().getImagePath();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.company (name, info, photo_path) VALUES (?, ?, ?) RETURNING id;"
            );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, info);
            preparedStatement.setString(3, photo);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Company getCompanyByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String info = rs.getString("info");
        String imagePath = rs.getString("photo_path");
        Image image = new Image(imagePath);
        return new Company(id, name, info, image);
    }

    public static void main(String [] args) {
        CompanyDAO dao = CompanyDAO.getCompanyDAO();
        Company company = dao.create(new Company(0, "Name12", "info", new Image("path")));
        System.out.println(company.getId());
    }
}