package kpfu.itis.group11_801.kilin.WorkingClass.database.DAO;

import kpfu.itis.group11_801.kilin.WorkingClass.database.Company;
import kpfu.itis.group11_801.kilin.WorkingClass.database.Image;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO extends DAO<Image>{

    private static Connection connection;
    private static ImageDAO imageDAO;

    private ImageDAO() throws SQLException {
        connection = DatabaseConnection.getDatabaseConnection().getConnnection();
    }

    public static ImageDAO getImageDAO() {
        if (imageDAO == null) {
            try {
                imageDAO = new ImageDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return imageDAO;
    }
    @Override
    public List<Image> getAll() throws SQLException {
        List<Image> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.images;");
            while (rs.next()) {
                res.add(getImageByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public Image getById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.images WHERE id=" + id + ";");
            rs.next();
            return getImageByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Image update(Image elem) {
        int id = elem.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.images SET "
                    + "number=" + elem.getNumber() + ", "
                    + "number_link=" + Integer.parseInt(elem.getImagePath()) + ", "
                    + "message_id=" + elem.getMessageId() + ", "
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
            statement.executeQuery("DELETE FROM public.images WHERE id=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Image elem) {
        delete(elem.getId());
    }

    @Override
    public Image create(Image elem) {
        int number = elem.getNumber();
        String imagePath = elem.getImagePath();
        String info = elem.getInfo();
        int messageId = elem.getMessageId();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO public.images " +
                    "(message_id, number, number_link, info) "
                    + "VALUES "
                    + "(" + messageId + ","
                    + "" + number + ", "
                    + "" + Integer.parseInt(imagePath) + ", "
                    + "'" + info + "');"
            );
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Image getImageByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int number = rs.getInt("number");
        String imagePath = "" + rs.getInt("number_link");
        String info = rs.getString("info");
        int messageId = rs.getInt("message_id");
        return new Image(id, number, imagePath, info, messageId);
    }
}
