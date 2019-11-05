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

@MultipartConfig
public class MessageToUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        User receiver = new UserService().getUserById(Integer.parseInt(request.getParameter("id")));
        String text = request.getParameter("message");
        Part photoPart = request.getPart("photo1");
        List<Image> images = new ArrayList<>();
        images.add(Image.CreateImage(photoPart, getServletContext()));
        photoPart = request.getPart("photo2");
        images.add(Image.CreateImage(photoPart, getServletContext()));
        photoPart = request.getPart("photo3");
        images.add(Image.CreateImage(photoPart, getServletContext()));
        photoPart = request.getPart("photo4");
        images.add(Image.CreateImage(photoPart, getServletContext()));
        photoPart = request.getPart("photo5");
        images.add(Image.CreateImage(photoPart, getServletContext()));
        MessageToUser message = new MessageToUser(0, user, text, images, receiver, null);
        new MessageToUserService().create(message);
        response.sendRedirect("/WorkingClass_war_exploded/messages?id=" + receiver.getId());
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
        User receiver = new UserService().getUserById(id);
        List<MessageToUser> messages = new MessageToUserService().getMessages(user, receiver);
        Map<String, Object> root = new HashMap<>();
        root.put("messages", messages);
        root.put("user", user);
        root.put("receiver", receiver);
        Helpers.render(request, response, "messages_to_user.ftl", root);
    }
}
