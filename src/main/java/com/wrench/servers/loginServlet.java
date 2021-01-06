package com.wrench.servers;

import com.wrench.controllers.Authenticator;
import com.wrench.models.LoginCredentials;
import com.wrench.views.Forms;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("form", Forms.LOGIN.getRep());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginCredentials login = new LoginCredentials(request.getParameter("username"), request.getParameter("password"));
        var validation = Authenticator.validate(login);
        if (validation.isValid()) {
            request.setAttribute("message", "<hr><h1>" + validation.getMessage() + "<h1><hr>");
            request.setAttribute("continueButton","""
                    <a href="/display?username=%s"><button>Continue</button></a>
                    """.formatted(login.getUsername()));
            getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            request.setAttribute("form", Forms.LOGIN.getRep());
            request.setAttribute("message", "<br><hr>" + validation.getMessage() + "<br><br>");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        Authenticator.load();
    }

    @Override
    public void destroy() {
        super.destroy();
        Authenticator.save();
    }
}
