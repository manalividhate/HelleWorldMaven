package com.techdenovo.papp.dto;

import com.techdenovo.papp.model.Customer;

import java.util.List;

public interface CustomerDto {
    public Customer addCustomer(Customer customer);
    public boolean checkLogin(String email, String password);

    public List<Customer> getCustomers();
}
