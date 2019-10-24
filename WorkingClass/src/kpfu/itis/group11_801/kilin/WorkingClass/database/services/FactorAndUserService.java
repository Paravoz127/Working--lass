package kpfu.itis.group11_801.kilin.workingClass.database.services;

import kpfu.itis.group11_801.kilin.workingClass.database.DAO.FactorAndUserDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorAndUser;
import kpfu.itis.group11_801.kilin.workingClass.database.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FactorAndUserService {
    public FactorAndUser getOrCreate(FactorAndUser fu) {
        try {
            for (FactorAndUser factorAndUser : FactorAndUserDAO.getFactorAndUserDAO().getAll()) {
                if (factorAndUser.equals(fu)) {
                    return factorAndUser;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return FactorAndUserDAO.getFactorAndUserDAO().create(fu);
    }

    public void deleteById(int id) {
        FactorAndUserDAO.getFactorAndUserDAO().delete(id);
    }

    public List<FactorAndUser> getPositiveByUser(User u) {
        try {
            return FactorAndUserDAO.getFactorAndUserDAO().getPositiveByUser(u.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<FactorAndUser> getNegativeByUser(User u) {
        try {
            return FactorAndUserDAO.getFactorAndUserDAO().getNegativeByUser(u.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void delete(FactorAndUser fu) {
        FactorAndUserDAO.getFactorAndUserDAO().delete(fu);
    }
}
