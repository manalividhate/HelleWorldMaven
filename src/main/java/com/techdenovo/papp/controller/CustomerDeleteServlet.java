package com.techdenovo.papp.controller;

import com.techdenovo.papp.dto.CustomerDto;
import com.techdenovo.papp.dto.CustomerDtoImpl;
import com.techdenovo.papp.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer-delete")
public class CustomerDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CustomerDto customerDto = new CustomerDtoImpl();
        customerDto.deleteCustomer(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("dashboard");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
