package kpfu.itis.group11_801.kilin.workingClass.servlets.createFactorByPromotionServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorAndUser;
import kpfu.itis.group11_801.kilin.workingClass.database.FactorOfSalary;
import kpfu.itis.group11_801.kilin.workingClass.database.PromotionRequest;
import kpfu.itis.group11_801.kilin.workingClass.database.services.FactorAndUserService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.FactorOfSalaryService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.PromotionRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CreateNewFactorByPromotionServlet")
public class CreateNewFactorByPromotionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PromotionRequest promotionRequest = new PromotionRequestService().getById(Integer.parseInt(request.getParameter("promotion")));
            int value = Integer.parseInt(request.getParameter("value"));
            String factorName = request.getParameter("factor_name");
            String message = request.getParameter("message");
            FactorOfSalary factorOfSalary = new FactorOfSalary(0, factorName, message, promotionRequest.getSender().getCompany());
            factorOfSalary = new FactorOfSalaryService().getOrCreate(factorOfSalary);
            FactorAndUser factorAndUser = new FactorAndUser(0, promotionRequest.getSender(), factorOfSalary, value);
            new FactorAndUserService().getOrCreate(factorAndUser);
            new PromotionRequestService().delete(promotionRequest.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/WorkingClass_war_exploded/promotions");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PromotionRequest promotionRequest = new PromotionRequestService().getById(Integer.parseInt(request.getParameter("id")));
            Map<String, Object> root = new HashMap<>();
            root.put("promotion", promotionRequest);
            root.put("user", request.getSession().getAttribute("user"));
            Helpers.render(request, response, "create_new_factor_by_promotion.ftl", root);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/WorkingClass_war_exploded/promotions");
        }
    }
}