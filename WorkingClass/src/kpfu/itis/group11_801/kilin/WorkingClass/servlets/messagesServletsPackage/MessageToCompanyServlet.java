package kpfu.itis.group11_801.kilin.workingClass.servlets.messagesServletsPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.Image;
import kpfu.itis.group11_801.kilin.workingClass.database.MessageToCompany;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.MessageToCompanyService;
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
public class MessageToCompanyServlet extends HttpServlet {
    private MessageToCompanyService messageToCompanyService;

    @Override
    public void init() throws ServletException {
        messageToCompanyService = new MessageToCompanyService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Company receiver = user.getCompany();
        String text = request.getParameter("message");
        List<Image> images = request.getParts().stream()
                .filter(x -> x.getName().equals("images"))
                .map(x -> Image.CreateImage(x, getServletContext()))
                .collect(Collectors.toList());
        if (images.get(0) == null && text.equals("")) {
            response.sendRedirect("/WorkingClass_war_exploded/com_messages?error=Message should not be empty");
        } else if (images.size() <= 5) {
            MessageToCompany message = new MessageToCompany(0, user, text, images, receiver, null);
            messageToCompanyService.create(message);
            response.sendRedirect("/WorkingClass_war_exploded/com_messages");
        } else {
            response.sendRedirect("/WorkingClass_war_exploded/com_messages?error=You can not use more than 5 photos");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Company receiver = user.getCompany();
        List<MessageToCompany> messages = messageToCompanyService.getMessages(receiver);
        Map<String, Object> root = new HashMap<>();
        root.put("messages", messages);
        root.put("receiver", receiver);
        root.put("error", request.getParameter("error"));
        Helpers.render(request, response, "messages_to_company.ftl", root);
    }
}
