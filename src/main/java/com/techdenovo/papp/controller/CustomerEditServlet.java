package com.techdenovo.papp.controller;
import com.techdenovo.papp.dto.CustomerDto;
import com.techdenovo.papp.dto.CustomerDtoImpl;
import com.techdenovo.papp.model.Customer;
import com.techdenovo.papp.util.DbUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/customer-edit")
public class CustomerEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id= Integer.parseInt(req.getParameter("id"));
            Customer customer = new Customer();
            CustomerDto customerDto = new CustomerDtoImpl();
            customer=customerDto.getCustomer(id);
            req.setAttribute("customer",customer);
            req.getRequestDispatcher("customer_edit.jsp").forward(req,resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(req.getParameter("id")));
        customer.setFirstName(req.getParameter("first-name"));
        customer.setLastName(req.getParameter("last-name"));
        customer.setMobNumber(req.getParameter("mob-no"));
        customer.setEmail(req.getParameter("email"));
        customer.setCity(req.getParameter("city"));

        CustomerDto customerDto = new CustomerDtoImpl();
        int i =customerDto.updateCustomer(customer);
        if(i==1){
            resp.sendRedirect("dashboard");
        }
    }
}