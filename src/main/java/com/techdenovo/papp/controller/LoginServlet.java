package com.techdenovo.papp.controller;

import com.techdenovo.papp.dto.CustomerDto;
import com.techdenovo.papp.dto.CustomerDtoImpl;
import com.techdenovo.papp.model.Customer;
import com.techdenovo.papp.util.DbUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    CustomerDto customerDto = new CustomerDtoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login-form.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password =req.getParameter("password");
        System.out.println(email+password);
        boolean flag = customerDto.checkLogin(email,password);
        String destPage = "/login.jsp";
        if(flag){
            HttpSession oldHttpSession = req.getSession(false);

                if (oldHttpSession != null) {
                    oldHttpSession.invalidate();
                }
            HttpSession newHttpSession = req.getSession(true);
            newHttpSession.setAttribute("message","Login Success");
            destPage = "/dashboard";

            newHttpSession.setMaxInactiveInterval(5 * 60);
            resp.sendRedirect("dashboard");
        } else {
            String message = "Invalid email/password";
            req.setAttribute("message",message);
        }
        System.out.println(destPage);
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destPage);
//        dispatcher.forward(req,resp);
    }
}
