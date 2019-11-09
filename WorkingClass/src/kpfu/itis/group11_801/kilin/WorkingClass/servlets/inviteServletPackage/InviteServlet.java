package kpfu.itis.group11_801.kilin.workingClass.servlets.inviteServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorAndUser;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorOfSalary;
import kpfu.itis.group11_801.kilin.workingClass.database.Invite;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.FactorAndUserService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.FactorOfSalaryService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.InviteService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InviteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sender = (User)request.getSession().getAttribute("user");
        User target = new UserService().getUserById(Integer.parseInt(request.getParameter("target")));
        String post = request.getParameter("post");
        int value = Integer.parseInt(request.getParameter("value"));
        FactorOfSalary fs = new FactorOfSalary(0, post, "the main of this post`s factors", sender.getCompany());
        fs = new FactorOfSalaryService().getOrCreate(fs);
        FactorAndUser factorAndUser = new FactorAndUser(0, target, fs, value);
        factorAndUser = new FactorAndUserService().getOrCreate(factorAndUser);
        Invite invite = new Invite(0, sender, target, factorAndUser);
        invite = new InviteService().getOrCreate(invite);
        response.sendRedirect("/WorkingClass_war_exploded/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (((User) request.getSession().getAttribute("user")).getCompany() == null) {
            response.sendRedirect("/WorkingClass_war_exploded/create_company");
        } else {
            Map<String, Object> root = new HashMap<>();
            List<User> users = new UserService().getAllUsers().stream()
                    .filter(x -> x.getCompany() == null)
                    .collect(Collectors.toList());
            root.put("users", users);
            root.put("user", request.getSession().getAttribute("user"));
            Helpers.render(request, response, "invite.ftl", root);
        }
    }
}
