package com.techdenovo.papp.controller;
import com.techdenovo.papp.dto.CustomerDto;
import com.techdenovo.papp.dto.CustomerDtoImpl;
import com.techdenovo.papp.model.Customer;
import com.techdenovo.papp.util.CustomerPortalHashing;
import org.mariadb.jdbc.internal.logging.LoggerFactory;

import javax.print.attribute.standard.DateTimeAtProcessing;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet{

    CustomerPortalHashing customerPortalHashing = new CustomerPortalHashing();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("registration.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = new Customer();
        customer.setFirstName(req.getParameter("first-name"));
        customer.setLastName(req.getParameter("last-name"));
        customer.setMobNumber(req.getParameter("mob-no"));
        customer.setEmail(req.getParameter("email"));
        customer.setCity(req.getParameter("city"));
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm-password");

        if(password.contentEquals(confirm_password)){
            System.out.println("inside confirm password");
           customer.setPassword(customerPortalHashing.hash(password));;

            CustomerDto customerDto = new CustomerDtoImpl();
            if(customerDto.addCustomer(customer)==1){
                Customer savedCustomer = customerDto.getCustomer(customer.getEmail());
                req.setAttribute("message", "Registration Successful");
                req.setAttribute("customer",savedCustomer);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/registration_success.jsp");
                requestDispatcher.forward(req,resp);
            }

        } else {
            req.setAttribute("message","Password and confirm password don't match");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
            requestDispatcher.forward(req,resp);
        }
    }
}
