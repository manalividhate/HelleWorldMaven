package com.techdenovo.papp.dto;

import com.techdenovo.papp.model.Customer;
import com.techdenovo.papp.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDtoImpl implements CustomerDto {
    DbUtil dbUtil = new DbUtil();


    @Override
    public Customer addCustomer(Customer customer) {
        Customer newCustomer = customer;
        if (customer != null) {
            String sql = "INSERT INTO customer(FIRST_NAME,LAST_NAME,MOB_NUMBER,EMAIL,CITY,PASSWORD) VALUES(?,?,?,?,?,?)";
            try {
                Connection conn = dbUtil.dbConnect();
                if (conn != null) {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, customer.getFirstName());
                    pstmt.setString(2, customer.getLastName());
                    pstmt.setString(3, customer.getMobNumber());
                    pstmt.setString(4, customer.getEmail());
                    pstmt.setString(5, customer.getCity());
                    pstmt.setString(6, customer.getPassword());
                    int i = pstmt.executeUpdate();
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean checkLogin(String email, String password) {
        System.out.println(email+" "+ password);
            boolean flag=false;
        try {
            Connection connection = dbUtil.dbConnect();
            if(connection!=null){

                String sql = "SELECT * FROM customer WHERE email = ? and password = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet result = statement.executeQuery();
                if(result.next())
                {
                    System.out.println(result.getString("FIRST_NAME"));
                    flag=true;
//                    System.out.println("window.alert('Login Successfully,)");
                }
                else
                {
                    flag=false;
//                    System.out.println("window.alert('Invalid input')");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
            Connection conn = dbUtil.dbConnect();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("Select *From customer");
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("ID"));
                    customer.setFirstName(resultSet.getString("FIRST_NAME"));
                    customer.setLastName(resultSet.getString("LAST_NAME"));
                    customer.setMobNumber(resultSet.getString("MOB_NUMBER"));
                    customer.setEmail(resultSet.getString("EMAIL"));
                    customer.setCity(resultSet.getString("CITY"));
                    customers.add(customer);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }
}


