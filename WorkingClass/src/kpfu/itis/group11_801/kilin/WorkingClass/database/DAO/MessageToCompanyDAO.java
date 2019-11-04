package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            ResultSet rs = statement.executeQuery("SELECT * FROM public.company_message;");
            while (rs.next()) {
                res.add(getMessageByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<MessageToCompany> getMessagesFrom1To2(User u1, Company company) {
        List<MessageToCompany> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.company_message WHERE sender=" + u1.getId() + " AND receiver=" + company.getId() + ";");
            while (rs.next()) {
                res.add(getMessageByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<MessageToCompany> getMessagesByCompany(Company company) {
        List<MessageToCompany> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.company_message WHERE receiver=" + company.getId() + ";");
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
            ResultSet rs = statement.executeQuery("SELECT * FROM public.company_message WHERE id=" + id + ";");
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
            statement.executeQuery("UPDATE public.company_message SET "
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
            statement.executeQuery("DELETE FROM public.company_message WHERE id=" + id + ";");
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
        String [] paths = {"null", "null", "null", "null", "null"};
        int i = 0;
        for (Image image : elem.getImages()) {
            if (i != 5) {
                if(image != null) {
                    paths[i] = image.getImagePath();
                }
            }
            i++;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("INSERT INTO public.company_message " +
                    "(sender, receiver, images_paths[0], images_paths[1], images_paths[2], images_paths[3], images_paths[4], time, text) "
                    + "VALUES "
                    + "(" + senderId + ","
                    + "" + receiverId + ", "
                    + "'" + paths[0] + "', "
                    + "'" + paths[1] + "', "
                    + "'" + paths[2] + "', "
                    + "'" + paths[3] + "', "
                    + "'" + paths[4] + "', "
                    + "'now', "
                    + "'" + text + "') RETURNING id;"
            );
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MessageToCompany getMessageByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        User sender = UserDAO.getUserDAO().getById(rs.getInt("sender"));
        Company receiver = CompanyDAO.getCompanyDAO().getById(rs.getInt("receiver"));
        String text = rs.getString("text");
        List<Image> images;
        Array paths = rs.getArray("images_paths");
        System.out.println(rs.getString("time"));
        DateTime dateTime = new DateTime(rs.getString("time").substring(0, 19));

        List<String> list = Arrays.asList((String[])paths.getArray()).stream()
                .map(x -> x == null ? "null" : x)
                .collect(Collectors.toList());
        images = list.stream()
                .map(y -> new Image(y))
                .collect(Collectors.toList());
        return new MessageToCompany(id, sender, text, images, receiver, dateTime);
    }

    public static void main(String [] args) {
        MessageToCompanyDAO dao = MessageToCompanyDAO.getMessageToCompanyDAO();
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image("link1"));
        images.add(new Image("link2"));
        MessageToCompany messageToCompany = dao.create(new MessageToCompany(0, UserDAO.getUserDAO().getById(38), "fff", images, CompanyDAO.getCompanyDAO().getById(20), null));
        System.out.println(messageToCompany.getId());

    }
}
