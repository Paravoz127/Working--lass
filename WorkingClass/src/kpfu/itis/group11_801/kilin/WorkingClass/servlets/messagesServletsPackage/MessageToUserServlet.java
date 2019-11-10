package kpfu.itis.group11_801.kilin.workingClass.servlets.messagesServletsPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.DAO.UserDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.Image;
import kpfu.itis.group11_801.kilin.workingClass.database.Message;
import kpfu.itis.group11_801.kilin.workingClass.database.MessageToUser;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.MessageToUserService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@MultipartConfig
public class MessageToUserServlet extends HttpServlet {

    private UserService userService;
    private MessageToUserService messageToUserService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        messageToUserService = new MessageToUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        User receiver = userService.getUserById(Integer.parseInt(request.getParameter("id")));
        String text = request.getParameter("message");
        List<Image> images = request.getParts().stream()
                .filter(x -> x.getName().equals("images"))
                .map(x -> Image.CreateImage(x, getServletContext()))
                .collect(Collectors.toList());
        if (text.length() > 300) {
            response.sendRedirect("/WorkingClass_war_exploded/messages?id=" + receiver.getId() + "&error=Message should not have more than 300 symbols");
        } else if (images.get(0) == null && text.equals("")) {
            response.sendRedirect("/WorkingClass_war_exploded/messages?id=" + receiver.getId() + "&error=Message should not be empty");
        } else if (images.size() <= 5) {
            MessageToUser message = new MessageToUser(0, user, text, images, receiver, null);
            messageToUserService.create(message);
            response.sendRedirect("/WorkingClass_war_exploded/messages?id=" + receiver.getId());
        } else {
            response.sendRedirect("/WorkingClass_war_exploded/messages?id=" + receiver.getId() + "&error=You can not use more than 5 photos");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            response.sendRedirect("/WorkingClass_war_exploded/dialogs");
        }
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/WorkingClass_war_exploded/dialogs");
        }
        User user = (User)request.getSession().getAttribute("user");
        User receiver = userService.getUserById(id);
        List<MessageToUser> messages = messageToUserService.getMessages(user, receiver);
        Map<String, Object> root = new HashMap<>();
        root.put("messages", messages);
        root.put("receiver", receiver);
        root.put("error", request.getParameter("error"));
        Helpers.render(request, response, "messages_to_user.ftl", root);
    }
}
