package kpfu.itis.group11_801.kilin.workingClass.database.DAO;

import kpfu.itis.group11_801.kilin.workingClass.database.FactorAndUser;
import kpfu.itis.group11_801.kilin.workingClass.database.Invite;
import kpfu.itis.group11_801.kilin.workingClass.database.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.invite;");
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.invite WHERE id=" + id + ";");
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.invite WHERE receiver=" + id + ";");
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.invite WHERE sender=" + id + ";");
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
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE public.invite SET "
                    + "sender=" + elem.getSender().getId() + ", "
                    + "receiver=" + elem.getReceiver().getId() + ", "
                    + "factor_user=" + elem.getFactorAndUser().getId() + " WHERE id=" + id + ";"
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
            statement.executeQuery("DELETE FROM public.invite WHERE id=" + id + ";");
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("INSERT INTO public.invite " +
                    "(sender, receiver, factor_user) "
                    + "VALUES "
                    + "(" + sender + ", " + receiver + ", " + factorAndUser + ") RETURNING id;"
            );
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
