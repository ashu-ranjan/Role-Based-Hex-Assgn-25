package com.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.service.CategoryService;

public class CategoryServiceTest {
	
	CategoryService categoryService;
	
	@BeforeEach
	public void init() {
		categoryService = new CategoryService();
	}
	
	@Test
	public void testInsertCategory() {
		// Use Case 1: Insert valid category (no exception expected)
		Category category = new Category(4, "Electonics");
		assertDoesNotThrow(() -> categoryService.insertCategory(category));
		
		// Use Case 2: Insert null category (should throw InvalidInputException)
		InvalidInputException excp = assertThrows(InvalidInputException.class, () -> {
			categoryService.insertCategory(null);
		});
		assertEquals("Category should not be null", excp.getMessage());
	}
	
	@Test
	public void testGetAll() {
		List<Category> categoriesList = categoryService.getAllCat();
		assertNotNull(categoriesList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
