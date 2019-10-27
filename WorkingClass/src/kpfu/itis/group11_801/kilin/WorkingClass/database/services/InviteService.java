package kpfu.itis.group11_801.kilin.workingClass.database.services;

import kpfu.itis.group11_801.kilin.workingClass.database.DAO.FactorAndUserDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.DAO.InviteDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorAndUser;
import kpfu.itis.group11_801.kilin.workingClass.database.Invite;
import kpfu.itis.group11_801.kilin.workingClass.database.User;

import javax.crypto.spec.IvParameterSpec;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InviteService {
    public Invite getOrCreate(Invite inv) {
        try {
            for (Invite invite : InviteDAO.getInviteDAO().getAll()) {
                if (invite.equals(inv)) {
                    return invite;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return InviteDAO.getInviteDAO().create(inv);
    }

    public List<Invite> getByTarget(User u) {
        return InviteDAO.getInviteDAO().getByTarget(u.getId());
    }

    public List<Invite> getBySender(User u) {
        return InviteDAO.getInviteDAO().getBySender(u.getId());
    }

    public void deleteById(int id) {
        Invite invite = InviteDAO.getInviteDAO().getById(id);
        FactorAndUser factorAndUser = invite.getFactorAndUser();
        InviteDAO.getInviteDAO().delete(invite);
        FactorAndUserDAO.getFactorAndUserDAO().delete(factorAndUser);
    }

    public void deleteOnlyById(int id) {
        Invite invite = InviteDAO.getInviteDAO().getById(id);
        InviteDAO.getInviteDAO().delete(invite);
    }

    public void deleteAllInvites(List<Invite> invites) {
        for (Invite elem : invites) {
            deleteById(elem.getId());
        }
    }

    public Invite getById(int id) {
        return InviteDAO.getInviteDAO().getById(id);
    }
}
