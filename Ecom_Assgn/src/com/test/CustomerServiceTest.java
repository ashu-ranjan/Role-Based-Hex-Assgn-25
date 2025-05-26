package com.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecom.exception.InvalidInputException;
import com.ecom.model.Customer;
import com.ecom.service.CustomerService;

public class CustomerServiceTest {
	
	private CustomerService customerService;
	
	@BeforeEach
	public void init() {
		customerService = new CustomerService();
	}
	
	@Test
	public void testInsertCustomer() {
		// Use Case 1: Insert valid customer (should not throw exception)
		Customer customer = new Customer (0, "John Doe", "Delhi", "7889788989");
		assertDoesNotThrow(() -> customerService.insertCustomer(customer));
		
		// Use case 2: Insert Null customer (should throw InvalidInputException)
		
		InvalidInputException excpException = assertThrows(InvalidInputException.class, () -> {
			customerService.insertCustomer(null);
		});
		assertEquals("Customer should not be null", excpException.getMessage());
	}
	
	@Test
	public void testGetAllCustomer() {
		// Use Case 3: Get all customers - should return a non-null list
		List<Customer> customerList = customerService.getAll();
		assertNotNull(customerList);
		
	}	
}
