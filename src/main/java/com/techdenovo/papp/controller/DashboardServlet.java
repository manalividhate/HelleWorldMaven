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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    CustomerDto customerDto = new CustomerDtoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();
        customers = customerDto.getCustomers();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("dashboard.jsp").forward(req,resp);

    }
}
