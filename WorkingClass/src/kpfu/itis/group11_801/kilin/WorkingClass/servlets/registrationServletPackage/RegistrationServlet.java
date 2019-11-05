package kpfu.itis.group11_801.kilin.workingClass.servlets.registrationServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.Date;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String birthday = request.getParameter("date");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User u = new User(0, firstName, secondName, email, password, new Date(birthday), null, null, null);
        User user = new UserService().registrate(u);
        if (user != null) {
            Helpers.authenticate(user, request);
            if(request.getParameter("safeMe") != null && request.getParameter("safeMe").equals("true")) {
                Cookie cookie = new Cookie("user_id", user.getId() + "");
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            }
            response.sendRedirect("/WorkingClass_war_exploded/user");
        } else {
            response.sendRedirect("/WorkingClass_war_exploded/registration");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Helpers.render(request, response, "registration.ftl", null);
    }
}
