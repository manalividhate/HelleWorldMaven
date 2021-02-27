package com.techdenovo.papp.controller;
import com.techdenovo.papp.dto.CustomerDto;
import com.techdenovo.papp.dto.CustomerDtoImpl;
import com.techdenovo.papp.model.Customer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet{

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
            customer.setPassword(password);
            CustomerDto customerDto = new CustomerDtoImpl();
            Customer savedCustomer = customerDto.addCustomer(customer);

            req.setAttribute("message", "Registration Successful");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/registration_success.jsp");
            requestDispatcher.forward(req,resp);
        } else {
            req.setAttribute("message","Password and confirm password don't match");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
            requestDispatcher.forward(req,resp);
        }



    }
}
