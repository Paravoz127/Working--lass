package kpfu.itis.group11_801.kilin.workingClass.servlets.registrationServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.Date;
import kpfu.itis.group11_801.kilin.workingClass.database.RegistrationObject;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String birthday = request.getParameter("date");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        RegistrationObject registrationObject = userService.registrate(firstName, secondName, birthday, email, password, password2);

        if (registrationObject.getCode() == 0) {
            User user = registrationObject.getUser();
            Helpers.authenticate(user, request);
            if(request.getParameter("safeMe") != null && request.getParameter("safeMe").equals("true")) {
                Cookie cookie = new Cookie("user_id", user.getId() + "");
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            }
            response.sendRedirect("/WorkingClass_war_exploded/user");
        } else {
            String adress = "/WorkingClass_war_exploded/registration?error=";
            if (registrationObject.getCode() == 1 || registrationObject.getCode() == 2) {
                adress += "Name has 1 word and starts with a capital letter";
            } else if (registrationObject.getCode() == 3) {
                adress += "The user can register only in the year of eighteen or older";
            } else if (registrationObject.getCode() == 4) {
                adress += "Passwords do not match";
            } else if (registrationObject.getCode() == 5) {
                adress += "Password length must be greater than or equal to 8";
            } else if (registrationObject.getCode() == 6){
                adress += "Email is already in use";
            } else if (registrationObject.getCode() == 7) {
                adress += "Email is wrong";
            } else if (registrationObject.getCode() == 8) {
                adress += "Year of birthday should be more than 1900";
            }
            if (firstName != null) {
                adress += "&first_name=" + firstName;
            }
            if (secondName != null) {
                adress += "&second_name=" + secondName;
            }
            if (email != null) {
                adress += "&email=" + email;
            }
            response.sendRedirect(adress);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("first_name", request.getParameter("first_name"));
        root.put("second_name", request.getParameter("second_name"));
        root.put("error", request.getParameter("error"));
        root.put("email", request.getParameter("email"));


        Helpers.render(request, response, "registration.ftl", root);
    }
}
