package com.techdenovo.papp.dto;

import com.techdenovo.papp.model.Customer;
import com.techdenovo.papp.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDtoImpl implements CustomerDto{
    DbUtil dbUtil = new DbUtil();
    @Override
    public Customer addCustomer(Customer customer) {
        Customer newCustomer = customer;
        if(customer!=null){
            String sql = "INSERT INTO customer(FIRST_NAME,LAST_NAME,MOB_NUMBER,EMAIL,CITY,PASSWORD) VALUES(?,?,?,?,?,?)";
            try {
                Connection conn=dbUtil.dbConnect();
                if(conn!=null){
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
}
