package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.service.ProductService;

public class ProductServiceTest {

    ProductService productService;

    @BeforeEach
    public void init() {
        productService = new ProductService();
    }

    @Test
    public void testInsertProduct() {
        // Use Case 1: Valid product
        Product p = new Product();
        p.setName("Charger");
        p.setPrice(499.0);
        Category category = new Category();
        category.setId(1); 
        p.setCategory(category);

        assertDoesNotThrow(() -> productService.insertProduct(p));

        // Use Case 2: Null product (should throw exception)
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> {
            productService.insertProduct(null);
        });
        assertEquals("Product should not be null", ex.getMessage());
    }

    @Test
    public void testGetByCategoryId_Valid() {
        int validCategoryId = 1; 
        assertDoesNotThrow(() -> {
            List<Product> products = productService.getProdByCatId(validCategoryId);
            assertNotNull(products);
        });
    }

    @Test
    public void testGetByCategoryId_Invalid() {
        int invalidCategoryId = -1;
        assertThrows(InvalidIdException.class, () -> {
            productService.getProdByCatId(invalidCategoryId);
        });
    }
}
