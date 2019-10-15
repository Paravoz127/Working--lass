package kpfu.itis.group11_801.kilin.WorkingClass.database.DAO;

import kpfu.itis.group11_801.kilin.WorkingClass.database.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageToUserDAO extends DAO<MessageToUser> {
    private static Connection connection;
    private static MessageToUserDAO messageToUserDAO;

    private MessageToUserDAO() throws SQLException {
        connection = DatabaseConnection.getDatabaseConnection().getConnnection();
    }

    public static MessageToUserDAO getMessageToUserDAO() {
        if (messageToUserDAO == null) {
            try {
                messageToUserDAO = new MessageToUserDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return messageToUserDAO;
    }

    @Override
    public List<MessageToUser> getAll() throws SQLException {
        List<MessageToUser> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.messages_to_user;");
            while (rs.next()) {
                res.add(getMessageByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public MessageToUser getById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.messages_to_user WHERE id=" + id + ";");
            rs.next();
            return getMessageByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MessageToUser update(MessageToUser elem) {
        int id = elem.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.messages_to_user SET "
                    + "sender_id=" + elem.getSender().getId() + ", "
                    + "receiver_id=" + elem.getReceiver().getId() + ", "
                    + "text='" + elem.getText() + "' WHERE id=" + id + ";"
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
            statement.executeQuery("DELETE FROM public.messages_to_user WHERE id=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(MessageToUser elem) {
        delete(elem.getId());
    }

    @Override
    public MessageToUser create(MessageToUser elem) {
        int senderId = elem.getSender().getId();
        int receiverId = elem.getReceiver().getId();
        String text = elem.getText();
        //TODO images
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO public.messages_to_user " +
                    "(sender_id, receiver_id, text) "
                    + "VALUES "
                    + "(" + senderId + ","
                    + "" + receiverId + ", "
                    + "'" + text + "');"
            );
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MessageToUser getMessageByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        User sender = UserDAO.getUserDAO().getById(rs.getInt("sender_id"));
        User receiver = UserDAO.getUserDAO().getById(rs.getInt("receiver_id"));
        String text = rs.getString("text");
        //TODO images
        return new MessageToUser(id, sender, text, null, receiver);
    }
}