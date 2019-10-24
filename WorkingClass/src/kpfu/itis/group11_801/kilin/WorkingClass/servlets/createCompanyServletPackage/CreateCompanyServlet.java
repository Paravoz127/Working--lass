package kpfu.itis.group11_801.kilin.workingClass.servlets.createCompanyServletPackage;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.Company;
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

@MultipartConfig
public class CreateCompanyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        String pathName = getServletContext().getRealPath("") + File.separator + "files" + File.separator + "companies" + File.separator;
        String smallPath = "files" + File.separator + "companies"+ File.separator;
        File dir = new File(pathName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String name = request.getParameter("name");
        String info = request.getParameter("info");
        Part photoPart = request.getPart("photo");
        BufferedInputStream fileContent = new BufferedInputStream(photoPart.getInputStream());
        String fileName = System.currentTimeMillis() + ".png";
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(pathName + fileName));
        while (fileContent.available() != 0) {
            outputStream.write(fileContent.read());
        }
        outputStream.close();
        fileContent.close();
        Company company = new CompanyService().registrate(new Company(0, name, info, new Image(smallPath + fileName)), user);
        if (company == null) {
            response.sendRedirect("/WorkingClass_war_exploded/create_company");
        } else {
            new InviteService().deleteAllInvites(new InviteService().getByTarget(user));
            response.sendRedirect("/WorkingClass_war_exploded/company?id=" + company.getId());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/WorkingClass_war_exploded/main");
        } else {
            if (((User) request.getSession().getAttribute("user")).getCompany() != null) {
                response.sendRedirect("/WorkingClass_war_exploded/user");
            } else {
                Helpers.render(request, response, "create_company.ftl", null);
            }
        }
    }
}
