package kpfu.itis.group11_801.kilin.workingClass.servlets.companyServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.services.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CompanyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/WorkingClass_war_exploded/main");
        } else {
            if (request.getParameter("id") == null) {
                Helpers.render(request, response, "not_found.ftl", null);
            } else {
                try {
                    Company company = new CompanyService().getCompanyById(Integer.parseInt(request.getParameter("id")));
                    if (company == null) {
                        Helpers.render(request, response, "not_found.ftl", null);
                    } else {
                        Map<String, Object> root = new HashMap<>();
                        root.put("company", company);
                        String path = company.getImage().getImagePath();
                        root.put("path", path);
                        Helpers.render(request, response, "company.ftl", root);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Helpers.render(request, response, "not_found.ftl", null);
                }
            }
        }
    }
}
