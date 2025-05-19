package com.ecom.dao;

import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Product;

import java.util.List;

public interface ProductDao {
    void insertProduct(Product product) throws InvalidInputException;
    List<Product> getByCategoryId(int categoryId) throws InvalidIdException;
    Product getById(int productId) throws InvalidIdException;
}
