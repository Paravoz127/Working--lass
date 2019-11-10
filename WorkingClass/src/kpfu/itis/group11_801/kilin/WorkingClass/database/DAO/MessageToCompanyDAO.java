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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.company_message;"
            );
            ResultSet rs = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.company_message WHERE sender=? AND receiver=?;"
            );
            preparedStatement.setInt(1, u1.getId());
            preparedStatement.setInt(2, company.getId());
            ResultSet rs = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.company_message WHERE receiver=?;"
            );
            preparedStatement.setInt(1, company.getId());
            ResultSet rs = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.company_message WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.company_message SET sender=?, receiver=?, images_paths[0]=?, "
                            + "images_paths[1]=?, images_paths[2]=?, images_paths[3]=?, images_paths[4]=?, text=? WHERE id=?"
            );
            preparedStatement.setInt(1, elem.getSender().getId());
            preparedStatement.setInt(2, elem.getReceiver().getId());
            for (i = 0; i <= 4; i++) {
                preparedStatement.setString(i + 3, paths[i]);
            }
            preparedStatement.setString(8, elem.getText());
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
                    "DELETE FROM public.company_message WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.company_message (sender, receiver, images_paths[0], images_paths[1], "
                            + "images_paths[2], images_paths[3], images_paths[4], time, text) VALUES (?, ?, ?, ?, ?, ?, ?, 'now', ?) RETURNING id;"
            );
            preparedStatement.setInt(1, senderId);
            preparedStatement.setInt(2, receiverId);
            for (i = 0; i <= 4; i++) {
                preparedStatement.setString(i + 3, paths[i]);
            }
            preparedStatement.setString(8, text);

            ResultSet rs = preparedStatement.executeQuery();
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