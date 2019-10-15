package kpfu.itis.group11_801.kilin.WorkingClass.database.DAO;

import kpfu.itis.group11_801.kilin.WorkingClass.database.Image;
import kpfu.itis.group11_801.kilin.WorkingClass.database.PromotionRequest;
import kpfu.itis.group11_801.kilin.WorkingClass.database.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PromotionRequestDAO extends DAO<PromotionRequest> {
    private static Connection connection;
    private static PromotionRequestDAO promotionRequestDAO;

    private PromotionRequestDAO() throws SQLException {
        connection = DatabaseConnection.getDatabaseConnection().getConnnection();
    }

    public static PromotionRequestDAO getPromotionRequestDAO() {
        if (promotionRequestDAO == null) {
            try {
                promotionRequestDAO = new PromotionRequestDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return promotionRequestDAO;
    }

    @Override
    public List<PromotionRequest> getAll() throws SQLException {
        List<PromotionRequest> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.promotion_request;");
            while (rs.next()) {
                res.add(getPromotionRequestByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public PromotionRequest getById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.promotion_request WHERE id=" + id + ";");
            rs.next();
            return getPromotionRequestByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PromotionRequest update(PromotionRequest elem) {
        int id = elem.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.promotion_request SET "
                    + "user_id=" + elem.getSender().getId() + ", "
                    + "message='" + elem.getMessage() + "', "
                    + "decided=" + elem.isDecided() + " WHERE id=" + id + ";"
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
            statement.executeQuery("DELETE FROM public.promotion_request WHERE id=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PromotionRequest elem) {
        delete(elem.getId());
    }

    @Override
    public PromotionRequest create(PromotionRequest elem) {
        int user_id = elem.getSender().getId();
        String message = elem.getMessage();
        boolean decided = elem.isDecided();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO public.promotion_request " +
                    "(user_id, message, decided) "
                    + "VALUES "
                    + "(" + user_id + ","
                    + "'" + message + "', "
                    + "" + decided + ";"
            );
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PromotionRequest getPromotionRequestByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        User user = UserDAO.getUserDAO().getById(rs.getInt("user_id"));
        User target = user.getBoss();
        String message = rs.getString("message");
        boolean decided = rs.getBoolean("decided");
        return new PromotionRequest(id, user, target, message, decided);
    }
}
