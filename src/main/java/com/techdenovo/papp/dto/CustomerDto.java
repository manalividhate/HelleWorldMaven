package com.techdenovo.papp.dto;

import com.techdenovo.papp.model.Customer;

import java.util.List;

public interface CustomerDto {
    public int addCustomer(Customer customer);
    public int updateCustomer(Customer customer);
    public void deleteCustomer( int id);
    public boolean checkLogin(String email, String password);

    public List<Customer> getCustomers();
    public Customer getCustomer(int id);
    public Customer getCustomer(String email);
}
