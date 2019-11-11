package kpfu.itis.group11_801.kilin.workingClass.servlets.mainPageServletPackage;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.AuthenticationObject;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainPageServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email == null) {
            response.sendRedirect("/WorkingClass_war_exploded/main?error=Email is empty");
        } else {
            AuthenticationObject authenticationObject = userService.authentication(email, request.getParameter("password"));
            if (authenticationObject.getCode() == 0) {
                User user = authenticationObject.getUser();
                Helpers.authenticate(user, request);
                if (request.getParameter("safeMe") != null && request.getParameter("safeMe").equals("true")) {
                    Cookie cookie = new Cookie("user_id", user.getId() + "");
                    cookie.setMaxAge(24 * 60 * 60);
                    response.addCookie(cookie);
                }
                response.sendRedirect("/WorkingClass_war_exploded/user");
            } else if (authenticationObject.getCode() == 1) {
                response.sendRedirect("/WorkingClass_war_exploded/main?error=Wrong email pattern");
            } else {
                response.sendRedirect("/WorkingClass_war_exploded/main?error=Wrong login or password&email=" + email);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("error", request.getParameter("error"));
        root.put("email", request.getParameter("email"));
        Helpers.render(request, response, "main.ftl", root);
    }
}
