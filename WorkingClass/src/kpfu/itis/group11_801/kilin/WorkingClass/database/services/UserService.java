package kpfu.itis.group11_801.kilin.workingClass.database.services;

import kpfu.itis.group11_801.kilin.workingClass.database.*;
import kpfu.itis.group11_801.kilin.workingClass.database.DAO.UserDAO;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.security.MessageDigest;

public class UserService {
    public User getByEmail(String email) {
        UserDAO userDAO = UserDAO.getUserDAO();
        return userDAO.getByEmail(email);
    }

    public RegistrationObject registrate(String firstName, String secondName, String birthday, String email, String password, String password2) {
        UserDAO userDAO = UserDAO.getUserDAO();
        String namePattern = "[A-Z][a-z]+";



        String pattern = "[a-zA-Z0-9_\\-\\.]+@[a-z]+\\.[a-z]+";
        if (email == null || !email.matches(pattern)) {
            return new RegistrationObject(null, 7);
        }

        if (firstName == null || !firstName.matches(namePattern)) {
            return new RegistrationObject(null, 1);
        }
        if (secondName == null || !secondName.matches(namePattern)) {
            return new RegistrationObject(null, 2);
        }

        Date date = new Date(birthday);

        if (birthday == null || date.getYear() > DateTime.getCurrentDateTime().getYear() - 18) {
            return new RegistrationObject(null, 3);
        } else if (date.getYear() < 1900){
            return new RegistrationObject(null, 8);
        }

        if (password == null || password2 == null || !password.equals(password2)) {
            return new RegistrationObject(null, 4);
        }

        if (password.length() < 8) {
            return new RegistrationObject(null, 5);
        }

        User u = new User(0, firstName, secondName, email, password, date, null, null, null);
        if (getByEmail(u.getLogin()) != null) {
            return new RegistrationObject(null, 6);
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(u.getPassword().getBytes());
            String result = new String(messageDigest.digest());
            u.setPassword(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new RegistrationObject(userDAO.create(u), 0);
    }

    public AuthenticationObject authentication(String login, String password) {
        String pattern = "[a-zA-Z0-9_\\-\\.]+@[a-z]+\\.[a-z]+";
        if (!login.matches(pattern)) {
            return new AuthenticationObject(null, 1);
        }
        User user = getByEmail(login);
        if (user == null) {return new AuthenticationObject(null, -1);}
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            String result = new String(messageDigest.digest());
            if (result.equals(user.getPassword())) {
                return new AuthenticationObject(user, 0);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new AuthenticationObject(null, -1);
    }

    public User getUserById(int id) {
        UserDAO userDAO = UserDAO.getUserDAO();
        return userDAO.getById(id);
    }

    public void update(User user) {
        UserDAO userDAO = UserDAO.getUserDAO();
        userDAO.update(user);
    }

    public List<User> getAllUsers() {
        return UserDAO.getUserDAO().getAll();
    }

    public User getBoss(User u) {
        if (u.getBoss() == null) {
            return null;
        }
        return UserDAO.getUserDAO().getById(u.getBoss().getId());
    }

    public List<User> getEmployees(User u) {
        return UserDAO.getUserDAO().getEmployees(u);
    }

    public boolean isEmployeeOf(User boss, User user) {
        if (user.getBoss() != null) {
            return user.getBoss().equals(boss);
        }
        return false;
    }

}
