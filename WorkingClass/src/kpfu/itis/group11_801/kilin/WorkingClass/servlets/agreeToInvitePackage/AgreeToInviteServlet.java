package kpfu.itis.group11_801.kilin.workingClass.servlets.agreeToInvitePackage;

import kpfu.itis.group11_801.kilin.workingClass.database.DAO.UserDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorAndUser;
import kpfu.itis.group11_801.kilin.workingClass.database.Invite;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.FactorAndUserService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.InviteService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AgreeToInviteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String arg = request.getParameter("false");
        if (arg != null) {
            int id = Integer.parseInt(arg);
            FactorAndUser factorAndUser = new InviteService().getById(id).getFactorAndUser();
            new InviteService().deleteById(id);
            new FactorAndUserService().delete(factorAndUser);
        } else {
           int id = Integer.parseInt(request.getParameter("true"));
           Invite invite = new InviteService().getById(id);
           User user = (User)request.getSession().getAttribute("user");
           user.setBoss(invite.getSender());
           user.setCompany(invite.getFactorAndUser().getFactorOfSalary().getCompany());
           new InviteService().deleteOnlyById(id);
           new UserService().update(user);
           new InviteService().deleteAllInvites(new InviteService().getByTarget(user));
        }

        response.sendRedirect("/WorkingClass_war_exploded/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/WorkingClass_war_exploded/user");
    }
}
