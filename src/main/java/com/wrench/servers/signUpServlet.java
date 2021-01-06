package com.wrench.servers;

import com.wrench.controllers.Authenticator;
import com.wrench.views.Forms;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "signUpServlet", value = "/signup")
public class signUpServlet extends HttpServlet {
    private static int count = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("form", Forms.SIGNUP.getRep());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int age = Integer.parseInt(request.getParameter("age"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dobString = request.getParameter("dateOfBirth");
        LocalDate dateOfBirth = LocalDate.parse(dobString.equals("")? LocalDate.now().toString() : dobString, DateTimeFormatter.ISO_LOCAL_DATE);

        var validation = Authenticator.validateRegistration(firstname, lastname, age, username, password, dateOfBirth);

        if(validation.isValid()) {
            request.setAttribute("message", "<hr><h1>" + validation.getMessage() + "<h1><hr>");
            request.setAttribute("continueButton", """
                    <a href="/login"><button>Continue</button></a>
                    """);
            getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            request.setAttribute("form", Forms.SIGNUP.getRep());
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