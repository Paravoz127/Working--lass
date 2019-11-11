package kpfu.itis.group11_801.kilin.workingClass.filters;

import kpfu.itis.group11_801.kilin.workingClass.Helpers;
import kpfu.itis.group11_801.kilin.workingClass.database.services.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        boolean flag = false;
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        if (((HttpServletRequest)req).getSession().getAttribute("user") == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user_id") && cookie.getValue() != "") {
                        Helpers.authenticate(new UserService().getUserById(Integer.parseInt(cookie.getValue())), request);
                        flag = true;
                    }
                }
            }
        } else {
            flag = true;
        }

        if (flag) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect("/WorkingClass_war_exploded/main");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
