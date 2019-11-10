package kpfu.itis.group11_801.kilin.workingClass.servlets.ajaxSearchPackage;

import org.json.JSONArray;
import org.json.JSONObject;

import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AjaxSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        User usr = (User)request.getSession().getAttribute("user");

        List<User> users = new UserService().getAllUsers();

        String s = request.getParameter("String");

        if (!s.equals("")) {
            users = users.stream()
                    .filter(x -> x.toString().toLowerCase().contains(s.toLowerCase()))
                    .collect(Collectors.toList());
        }
        users = users.stream()
                .filter(x -> !x.equals(usr))
                .collect(Collectors.toList());

        if (request.getParameter("Filter") != null) {
            int filter = Integer.parseInt(request.getParameter("Filter"));
            if (filter == 0) {
                users = users.stream()
                        .filter(x -> x.getCompany() == null)
                        .collect(Collectors.toList());
            } else if (filter == 1) {
                users = users.stream()
                        .filter(x -> x.getCompany() != null)
                        .collect(Collectors.toList());
            }
        }

        JSONArray ja = new JSONArray();
        for (User user : users) {
            ja.put(new JSONObject(user));
        }
        JSONObject jo = new JSONObject();
        jo.put("objects", ja);

        response.setContentType("text/json");
        response.getWriter().write(jo.toString());
    }
}
