package kpfu.itis.group11_801.kilin.workingClass.servlets.dialogsServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.MessageToUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DialogsServlet extends HttpServlet {

    private MessageToUserService messageToUserService;

    @Override
    public void init() throws ServletException {
        messageToUserService = new MessageToUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Set<User> users = messageToUserService.getUsersWhoHaveDialogsWithUser(user);
        System.out.println(users);
        Map<String, Object> root = new HashMap<>();
        root.put("users", users);
        Helpers.render(request, response, "dialogs.ftl", root);
    }
}
