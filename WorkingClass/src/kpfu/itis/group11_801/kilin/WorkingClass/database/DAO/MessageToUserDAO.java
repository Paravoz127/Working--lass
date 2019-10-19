package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            ResultSet rs = statement.executeQuery("SELECT * FROM public.user_message;");
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
            ResultSet rs = statement.executeQuery("SELECT * FROM public.user_message WHERE id=" + id + ";");
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
        String [] paths = {"null", "null", "null", "null", "null"};
        int i = 0;
        for (Image image : elem.getImages()) {
            if (i != 5) {
                paths[i] = image.getImagePath();
            }
            i++;
        }
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.user_message SET "
                    + "sender=" + elem.getSender().getId() + ", "
                    + "receiver=" + elem.getReceiver().getId() + ", "
                    + "images_paths[0]='" + paths[0] + "', "
                    + "images_paths[1]='" + paths[1] + "', "
                    + "images_paths[2]='" + paths[2] + "', "
                    + "images_paths[3]='" + paths[3] + "', "
                    + "images_paths[4]='" + paths[4] + "', "
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
            statement.executeQuery("DELETE FROM public.user_message WHERE id=" + id + ";");
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
        String [] paths = {"null", "null", "null", "null", "null"};
        int i = 0;
        for (Image image : elem.getImages()) {
            if (i != 5) {
                paths[i] = image.getImagePath();
            }
            i++;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("INSERT INTO public.user_message " +
                    "(sender, receiver, images_paths[0], images_paths[1], images_paths[2], images_paths[3], images_paths[4], text) "
                    + "VALUES "
                    + "(" + senderId + ","
                    + "" + receiverId + ", "
                    + "'" + paths[0] + "', "
                    + "'" + paths[1] + "', "
                    + "'" + paths[2] + "', "
                    + "'" + paths[3] + "', "
                    + "'" + paths[4] + "', "
                    + "'" + text + "') RETURNING id;"
            );
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MessageToUser getMessageByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        User sender = UserDAO.getUserDAO().getById(rs.getInt("sender"));
        User receiver = UserDAO.getUserDAO().getById(rs.getInt("receiver"));
        String text = rs.getString("text");
        List<Image> images;
        Array paths = rs.getArray("images_paths");

        List<String> list = Arrays.asList((String[])paths.getArray()).stream()
                .map(x -> x == null ? "null" : x)
                .collect(Collectors.toList());
        images = list.stream()
                .map(y -> new Image(y))
                .collect(Collectors.toList());
        return new MessageToUser(id, sender, text, images, receiver);
    }

    public static void main(String [] args) {
        MessageToUserDAO dao = MessageToUserDAO.getMessageToUserDAO();
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image("link1"));
        images.add(new Image("link2"));
        MessageToUser messageToUser = dao.create(new MessageToUser(0, UserDAO.getUserDAO().getById(10), "fff", images, UserDAO.getUserDAO().getById(11)));
        System.out.println(messageToUser.getId());

    }
}