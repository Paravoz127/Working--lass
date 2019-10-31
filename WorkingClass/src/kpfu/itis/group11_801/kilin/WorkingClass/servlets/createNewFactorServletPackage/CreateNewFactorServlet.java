package kpfu.itis.group11_801.kilin.workingClass.servlets.createNewFactorServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorAndUser;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorOfSalary;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.FactorAndUserService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.FactorOfSalaryService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/*
<div>Name of factor:</div>
<input type="text" name="factor_name"/>
<div>Message:</div>
<input type="text" name="message">
<div>Value:</div>
<input type="number" name="value"/>
<input type="submit" name="user_id" value="${user.getId()}"/>
*/
public class CreateNewFactorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = new UserService().getUserById(Integer.parseInt(request.getParameter("user_id")));
            int value = Integer.parseInt(request.getParameter("value"));
            String factorName = request.getParameter("factor_name");
            String message = request.getParameter("message");
            FactorOfSalary factorOfSalary = new FactorOfSalary(0, factorName, message, user.getCompany());
            factorOfSalary = new FactorOfSalaryService().getOrCreate(factorOfSalary);
            FactorAndUser factorAndUser = new FactorAndUser(0, user, factorOfSalary, value);
            new FactorAndUserService().getOrCreate(factorAndUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/WorkingClass_war_exploded/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/WorkingClass_war_exploded/main");
        } else if (request.getParameter("userId") == null) {
            response.sendRedirect("/WorkingClass_war_exploded/user");
        } else {
            try {
                User user = new UserService().getUserById(Integer.parseInt(request.getParameter("userId")));
                Map<String, Object> root = new HashMap<>();
                root.put("user", user);
                Helpers.render(request, response, "create_factor.ftl", root);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/WorkingClass_war_exploded/user");
            }
        }
    }
}
