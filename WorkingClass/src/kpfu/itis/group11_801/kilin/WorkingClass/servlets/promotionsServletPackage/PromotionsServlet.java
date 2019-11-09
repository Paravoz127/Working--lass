package kpfu.itis.group11_801.kilin.workingClass.servlets.promotionsServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.PromotionRequest;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.PromotionRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PromotionsServlet")
public class PromotionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        new PromotionRequestService().delete(id);
        response.sendRedirect("/WorkingClass_war_exploded/promotions");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Map<String, Object> root = new HashMap<>();
        List<PromotionRequest> promotionRequests = new PromotionRequestService().getAllToUser(user);
        root.put("requests", promotionRequests);
        root.put("user", request.getSession().getAttribute("user"));
        Helpers.render(request, response, "promotions.ftl", root);
    }
}
