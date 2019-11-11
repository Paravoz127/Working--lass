package kpfu.itis.group11_801.kilin.workingClass.servlets.createCompanyServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.CompanyRegistrationObject;
import kpfu.itis.group11_801.kilin.workingClass.database.Image;
import kpfu.itis.group11_801.kilin.workingClass.database.User;
import kpfu.itis.group11_801.kilin.workingClass.database.services.CompanyService;
import kpfu.itis.group11_801.kilin.workingClass.database.services.InviteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sound.midi.SysexMessage;
import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class CreateCompanyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        String info = request.getParameter("info");
        Part photoPart = request.getPart("photo");
        Image image = Image.CreateImage(photoPart, getServletContext());
        if (image == null) {
            response.sendRedirect("/WorkingClass_war_exploded/create_company?error=Company should have photo");
            return;
        }
        CompanyRegistrationObject companyRegistrationObject = new CompanyService().registrate(new Company(0, name, info, image), user);
        if (companyRegistrationObject.getCode() == -1) {
            response.sendRedirect("/WorkingClass_war_exploded/create_company?error=Company already exists");
        } else if (companyRegistrationObject.getCode() == 1) {
            response.sendRedirect("/WorkingClass_war_exploded/create_company?error=Company should have name");
        } else if (companyRegistrationObject.getCode() == 2) {
            response.sendRedirect("/WorkingClass_war_exploded/create_company?error=Company should have info");
        } else {

            new InviteService().deleteAllInvites(new InviteService().getByTarget(user));
            response.sendRedirect("/WorkingClass_war_exploded/company?id=" + companyRegistrationObject.getCompany().getId());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if (((User) request.getSession().getAttribute("user")).getCompany() != null) {
                response.sendRedirect("/WorkingClass_war_exploded/user");
            } else {
                Map<String, Object> root = new HashMap<>();
                root.put("user", request.getSession().getAttribute("user"));
                root.put("error", request.getParameter("error"));
                Helpers.render(request, response, "create_company.ftl", root);
            }

    }
}
