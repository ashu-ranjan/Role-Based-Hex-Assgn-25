package com.ecom.dao;

import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Customer;

import java.util.List;

public interface CustomerDao {
    void insertCustomer(Customer customer) throws InvalidInputException;
    List<Customer> getAllCustomer();
    Customer getById(int id) throws InvalidIdException;
}
