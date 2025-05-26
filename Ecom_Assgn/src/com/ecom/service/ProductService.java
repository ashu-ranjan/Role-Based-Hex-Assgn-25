package com.ecom.service;

import com.ecom.dao.ProductDao;
import com.ecom.dao.impl.ProductDaoImpl;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Product;

import java.util.List;

public class ProductService {

    ProductDao productDao = new ProductDaoImpl();

    public void insertProduct(Product product) throws InvalidInputException {
    	
    	if (product == null) {
            throw new InvalidInputException("Product should not be null");
        }
        if (product.getName() == null || product.getName().isBlank()) {
            throw new InvalidInputException("Product name is required");
        }
        if (product.getPrice() <= 0) {
            throw new InvalidInputException("Product price must be greater than zero");
        }
        if (product.getCategory() == null || product.getCategory().getId() <= 0) {
            throw new InvalidInputException("Valid category is required");
        }
        productDao.insertProduct(product);
    }

    public List<Product> getProdByCatId(int catId) throws InvalidIdException {
    	if(catId <= 0)
    		throw new InvalidIdException("Invalid Id given");
        return productDao.getByCategoryId(catId);
    }
}
