package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.FactorAndUser;
import kpfu.itis.group11_801.kilin.workingClass.database.Invite;
import kpfu.itis.group11_801.kilin.workingClass.database.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InviteDAO extends DAO<Invite>{
    private static Connection connection;
    private static InviteDAO inviteDAO;

    private InviteDAO() throws SQLException {
        connection = DatabaseConnection.getDatabaseConnection().getConnnection();
    }

    public static InviteDAO getInviteDAO() {
        if (inviteDAO == null) {
            try {
                inviteDAO = new InviteDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inviteDAO;
    }

    @Override
    public List<Invite> getAll() throws SQLException {
        List<Invite> res = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.invite;"
            );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(getInviteByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public Invite getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.invite WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return getInviteByResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Invite> getByTarget(int id) {
        List<Invite> res = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.invite WHERE receiver=?;"
            );
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(getInviteByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Invite> getBySender(int id) {
        List<Invite> res = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM public.invite WHERE sender=?;"
            );
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(getInviteByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Invite update(Invite elem) {
        int id = elem.getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.invite SET sender=?, receiver=?, factor_user=? WHERE id=?;"
            );
            preparedStatement.setInt(1, elem.getSender().getId());
            preparedStatement.setInt(2, elem.getReceiver().getId());
            preparedStatement.setInt(3, elem.getFactorAndUser().getId());
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
                    "DELETE FROM public.invite WHERE id=?;"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Invite elem) {
        delete(elem.getId());
    }

    @Override
    public Invite create(Invite elem) {
        int sender = elem.getSender().getId();
        int receiver = elem.getReceiver().getId();
        int factorAndUser = elem.getFactorAndUser().getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.invite (sender, receiver, factor_user) VALUES (?, ?, ?) RETURNING id;"
            );
            preparedStatement.setInt(1, sender);
            preparedStatement.setInt(2, receiver);
            preparedStatement.setInt(3, factorAndUser);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return getById(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Invite getInviteByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        FactorAndUser factorAndUser = FactorAndUserDAO.getFactorAndUserDAO().getById(rs.getInt("factor_user"));
        User receiver = UserDAO.getUserDAO().getById(rs.getInt("receiver"));
        User sender = UserDAO.getUserDAO().getById(rs.getInt("sender"));
        return new Invite(id, sender, receiver, factorAndUser);
    }
}

