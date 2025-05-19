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
        productDao.insertProduct(product);
    }

    public List<Product> getProdByCatId(int catId) throws InvalidIdException {
        return productDao.getByCategoryId(catId);
    }
}
