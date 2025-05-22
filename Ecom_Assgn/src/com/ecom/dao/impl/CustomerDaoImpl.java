package com.ecom.dao.impl;

import com.ecom.dao.CustomerDao;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Customer;
import com.ecom.utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    DBUtility dbUtility = DBUtility.getInstance();

    @Override
    public void insertCustomer(Customer customer) throws InvalidInputException {
        Connection conn = dbUtility.connect();
        String sql = "INSERT INTO customer (name, address, phone) VALUES (?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbUtility.close();
        }

    }
    @Override
    public List<Customer> getAllCustomer() {
        Connection conn = dbUtility.connect();
        String sql = "SELECT * FROM customer";
        List<Customer> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                Customer customer = new Customer(
                        rst.getInt("cust_id"),
                        rst.getString("name"),
                        rst.getString("address"),
                        rst.getString("phone")
                );
                list.add(customer);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbUtility.close();
        }
        return list;
    }

    @Override
    public Customer getById(int id) throws InvalidIdException {
        Connection conn = dbUtility.connect();
        String sql = "SELECT * FROM customer WHERE cust_id = ? ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rst = pstmt.executeQuery();

            if (rst.next()){
                Customer customer = new Customer();
                customer.setCustId(rst.getInt("cust_id"));
                customer.setName(rst.getString("name"));
                customer.setAddress(rst.getString("address"));
                customer.setPhone(rst.getString("phone"));
                return customer;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbUtility.close();
        } throw new InvalidIdException("Customer id is invalid.");
    }
}
