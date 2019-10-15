package kpfu.itis.group11_801.kilin.WorkingClass.database.DAO;

import kpfu.itis.group11_801.kilin.WorkingClass.database.Company;
import kpfu.itis.group11_801.kilin.WorkingClass.database.MessageToCompany;
import kpfu.itis.group11_801.kilin.WorkingClass.database.MessageToUser;
import kpfu.itis.group11_801.kilin.WorkingClass.database.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageToCompanyDAO extends DAO<MessageToCompany> {
    private static Connection connection;
    private static MessageToCompanyDAO messageToCompanyDAO;

    private MessageToCompanyDAO() throws SQLException {
        connection = DatabaseConnection.getDatabaseConnection().getConnnection();
    }

    public static MessageToCompanyDAO getMessageToCompanyDAO() {
        if (messageToCompanyDAO == null) {
            try {
                messageToCompanyDAO = new MessageToCompanyDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return messageToCompanyDAO;
    }


    @Override
    public List<MessageToCompany> getAll() throws SQLException {
        List<MessageToCompany> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.messages_to_company;");
            while (rs.next()) {
                res.add(getMessageByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public MessageToCompany getById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.messages_to_company WHERE id=" + id + ";");
            rs.next();
            return getMessageByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MessageToCompany update(MessageToCompany elem) {
        int id = elem.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.messages_to_company SET "
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
            statement.executeQuery("DELETE FROM public.messages_to_company WHERE id=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(MessageToCompany elem) {
        delete(elem.getId());
    }

    @Override
    public MessageToCompany create(MessageToCompany elem) {
        int senderId = elem.getSender().getId();
        int receiverId = elem.getReceiver().getId();
        String text = elem.getText();
        //TODO images
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO public.messages_to_company " +
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

    private MessageToCompany getMessageByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        User sender = UserDAO.getUserDAO().getById(rs.getInt("sender_id"));
        Company receiver = CompanyDAO.getCompanyDAO().getById(rs.getInt("receiver_id"));
        String text = rs.getString("text");
        //TODO images
        return new MessageToCompany(id, sender, text, null, receiver);
    }
}
