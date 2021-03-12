package com.techdenovo.papp.dto;

import com.techdenovo.papp.model.Customer;
import com.techdenovo.papp.util.CustomerPortalHashing;
import com.techdenovo.papp.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDtoImpl implements CustomerDto {
    DbUtil dbUtil = new DbUtil();

    public void deleteCustomer( int id){
        Connection conn = dbUtil.dbConnect();
        try
        {

                String query = "delete from customer WHERE ID=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1,id);
                ps.execute();
            conn.close();
        }
          catch(SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

     }
    @Override
    public int addCustomer(Customer customer) {
        Customer newCustomer = customer;
        int flag=0;
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
                    if(i==1){
                       flag =1;
                    }
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public int updateCustomer(Customer customer) {
        int flag=0;

        String sql= "Update Customer SET FIRST_NAME=?,LAST_NAME=?,MOB_NUMBER=?,EMAIL=?,CITY=? where id=?";
        Connection conn = dbUtil.dbConnect();
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, customer.getFirstName());
                ps.setString(2, customer.getLastName());
                ps.setString(3, customer.getMobNumber());
                ps.setString(4, customer.getEmail());
                ps.setString(5, customer.getCity());
                ps.setInt(6,customer.getId());
                if(ps.executeUpdate()==1){
                    flag=1;
                }
                else {
                    flag=0;
                }
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        return flag;
    }

    public boolean checkLogin(String email, String password) {
        System.out.println(email+" "+ password);
        String sql = "SELECT * FROM customer WHERE email = ?";
        CustomerPortalHashing customerPortalHashing = new CustomerPortalHashing();
        boolean flag=false;
//            if (BCrypt.checkpw(email, hashedPassword))
//                System.out.println("The password matches.");
//            else
//                System.out.println("The password does not match.");
      //  verifyHash(password,)
        try {
            Connection connection = dbUtil.dbConnect();
            if(connection!=null){
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, email);
                ResultSet result = statement.executeQuery();
                if(result.next())
                {
                    System.out.println(result.getString("FIRST_NAME"));
                     String hashedPassword = result.getString("password");
                     if(customerPortalHashing.verifyHash(password,hashedPassword)){
                         flag=true;
                         System.out.println(flag);
                     }
                     else {
                         System.out.println(flag);
                     }
//
                }
                else
                {
                    flag=false;
//                    System.out.println("window.alert('Invalid input')");
                }
                connection.close();
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
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomer(int id) {
        Customer customer = new Customer();
        Connection conn = dbUtil.dbConnect();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer where ID=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                customer.setId(rs.getInt("ID"));
                customer.setFirstName(rs.getString("FIRST_NAME"));
                customer.setLastName(rs.getString("LAST_NAME"));
                customer.setMobNumber(rs.getString("MOB_NUMBER"));
                customer.setEmail(rs.getString("EMAIL"));
                customer.setCity(rs.getString("CITY"));
            }
            else {
                System.out.println("no data found");
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customer;
    }

    @Override
    public Customer getCustomer(String email) {
        Customer customer = new Customer();
        Connection conn = dbUtil.dbConnect();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer where EMAIL=?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                customer.setId(rs.getInt("ID"));
                customer.setFirstName(rs.getString("FIRST_NAME"));
                customer.setLastName(rs.getString("LAST_NAME"));
                customer.setMobNumber(rs.getString("MOB_NUMBER"));
                customer.setEmail(rs.getString("EMAIL"));
                customer.setCity(rs.getString("CITY"));
            }
            else {
                System.out.println("no data found");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customer;
    }
}


