package kpfu.itis.group11_801.kilin.workingClass.servlets.promotionServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.PromotionRequest;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.PromotionRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PromotionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        PromotionRequest pr = new PromotionRequest(0, (User)request.getSession().getAttribute("user"), message, false);
        new PromotionRequestService().create(pr);
        response.sendRedirect("/WorkingClass_war_exploded/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (((User)request.getSession().getAttribute("user")).getBoss()==null) {
            response.sendRedirect("/WorkingClass_war_exploded/user");
        } else {
            Helpers.render(request, response, "promotion_request.ftl", null);
        }
    }
}
