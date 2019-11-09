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

    private UserService userService;
    private FactorOfSalaryService factorOfSalaryService;
    private FactorAndUserService factorAndUserService;
    private InviteService inviteService;

    @Override
    public void init() throws ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sender = (User)request.getSession().getAttribute("user");
        User target;
        try {
            target = userService.getUserById(Integer.parseInt(request.getParameter("target")));
        } catch (Exception e) {
            response.sendRedirect("/WorkingClass_war_exploded/invite?error=You should choose target");
            return;
        }
        String post = request.getParameter("post");
        if (post == null || post.equals("")) {
            response.sendRedirect("/WorkingClass_war_exploded/invite?error=You should choose post");
            return;
        }
        int value = 0;
        try {
            value = Integer.parseInt(request.getParameter("value"));
            if (value <= 0) {
                response.sendRedirect("/WorkingClass_war_exploded/invite?error=You should choose positive salary value");
                return;
            }
        } catch (Exception e) {
            response.sendRedirect("/WorkingClass_war_exploded/invite?error=You should choose positive salary value");
            return;
        }
        FactorOfSalary fs = new FactorOfSalary(0, post, "the main of this post`s factors", sender.getCompany());
        fs = factorOfSalaryService.getOrCreate(fs);
        FactorAndUser factorAndUser = new FactorAndUser(0, target, fs, value);
        factorAndUser = factorAndUserService.getOrCreate(factorAndUser);
        Invite invite = new Invite(0, sender, target, factorAndUser);
        invite = inviteService.getOrCreate(invite);
        response.sendRedirect("/WorkingClass_war_exploded/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (((User) request.getSession().getAttribute("user")).getCompany() == null) {
            response.sendRedirect("/WorkingClass_war_exploded/create_company");
        } else {
            Map<String, Object> root = new HashMap<>();
            List<User> users = userService.getAllUsers().stream()
                    .filter(x -> x.getCompany() == null)
                    .collect(Collectors.toList());
            root.put("users", users);
            root.put("error", request.getParameter("error"));
            Helpers.render(request, response, "invite.ftl", root);
        }
    }
}
