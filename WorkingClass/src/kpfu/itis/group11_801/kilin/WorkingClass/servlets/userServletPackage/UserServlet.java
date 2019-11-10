package kpfu.itis.group11_801.kilin.workingClass.servlets.userServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorAndUser;
import kpfu.itis.group11_801.kilin.workingClass.database.Invite;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.FactorAndUserService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.InviteService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private InviteService inviteService;
    private UserService userService;
    private FactorAndUserService factorAndUserService;

    @Override
    public void init() throws ServletException {
        inviteService = new InviteService();
        userService = new UserService();
        factorAndUserService = new FactorAndUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logout = request.getParameter("logout");
        if(logout != null && logout.equals("true")) {
            Cookie cookie = new Cookie("user_id", null);
            response.addCookie(cookie);
            request.getSession().removeAttribute("user");
            response.sendRedirect("/WorkingClass_war_exploded/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean showInfo = true;
        int id = ((User) request.getSession().getAttribute("user")).getId();
        int myId = id;
        if (request.getParameter("id") != null) {
            try {
                id = Integer.parseInt(request.getParameter("id"));
                showInfo = myId == id;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/WorkingClass_war_exploded/user");
            }
        }
        User user = userService.getUserById(id);
        if (user != null) {
            Map<String, Object> root = new HashMap<>();
            root.put("show_info", showInfo);

            List<Invite> invites = inviteService.getByTarget(user);
            root.put("invites", invites);

            List<Invite> invitesFrom = inviteService.getBySender(user);
            root.put("invites_from", invitesFrom);

            User boss = userService.getBoss(user);
            root.put("boss", boss);

            List<User> employees = userService.getEmployees(user);
            root.put("employees", employees);

            boolean isEmployee = userService.isEmployeeOf(userService.getUserById(myId), user);
            root.put("is_employee", isEmployee);
            root.put("user", user);

            List<FactorAndUser> positiveFactorAndUsers = factorAndUserService.getPositiveByUser(user);
            root.put("positive", positiveFactorAndUsers);
            List<FactorAndUser> negativeFactorAndUsers = factorAndUserService.getNegativeByUser(user);
            root.put("negative", negativeFactorAndUsers);

            int positiveSum = 0;
            int negativeSum = 0;
            for (FactorAndUser factorAndUser : positiveFactorAndUsers) {
                positiveSum += factorAndUser.getValue();
            }
            for (FactorAndUser factorAndUser : negativeFactorAndUsers) {
                negativeSum += factorAndUser.getValue();
            }
            root.put("positive_sum", positiveSum);
            root.put("negative_sum", negativeSum);
            Helpers.render(request, response, "user.ftl", root);
        } else {
            Helpers.render(request, response, "not_found.ftl", null);
        }
    }
}
