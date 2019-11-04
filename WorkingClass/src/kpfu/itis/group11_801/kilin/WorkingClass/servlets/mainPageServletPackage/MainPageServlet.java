package kpfu.itis.group11_801.kilin.workingClass.servlets.mainPageServletPackage;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new UserService().authentication(request.getParameter("email"), request.getParameter("password"));
        if (user != null) {
            authenticate(user, request);
            if(request.getParameter("safeMe") != null && request.getParameter("safeMe").equals("true")) {
                Cookie cookie = new Cookie("user_id", user.getId() + "");
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            }
            response.sendRedirect("/WorkingClass_war_exploded/user");
        } else {
            request.getSession().setAttribute("loginError", false);
            response.sendRedirect("/WorkingClass_war_exploded/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs = request.getSession();
        if (hs.getAttribute("user") != null) {
            response.sendRedirect("/WorkingClass_war_exploded/user");
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user_id") && cookie.getValue() != "") {
                        authenticate(new UserService().getUserById(Integer.parseInt(cookie.getValue())), request);
                        response.sendRedirect("/WorkingClass_war_exploded/user");
                    }
                }
            }

            Map<String, Object> root = new HashMap<>();
            root.put("login_error", request.getSession().getAttribute("loginError"));
            request.getSession().removeAttribute("loginError");
            Helpers.render(request, response, "main.ftl", root);
        }
    }

    private void authenticate(User user, HttpServletRequest request) {
        HttpSession hs = request.getSession();
        hs.setAttribute("user", user);
    }
}
