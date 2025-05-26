package com.ecom.dao.impl;

import com.ecom.dao.ProductDao;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    DBUtility dbUtility = DBUtility.getInstance();

    @Override
    public void insertProduct(Product product) throws InvalidInputException {
        Connection connection = dbUtility.connect();
        String sql = "INSERT INTO product VALUES (?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getCategory().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbUtility.close();
        }
    }

    @Override
    public List<Product> getByCategoryId(int categoryId) throws InvalidIdException {
        Connection conn = dbUtility.connect();
        String sql = "SELECT * FROM product WHERE category_category_id = ? ";
        List<Product> list = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);

            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                Product product = new Product();
                product.setId(rst.getInt("prod_id"));
                product.setName(rst.getString("name"));
                product.setPrice(rst.getDouble("price"));

                // category object connected to product
                Category category = new Category();
                category.setId(categoryId);
                product.setCategory(category);

                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbUtility.close();
        }

        return list;
    }

    @Override
    public Product getById(int productId) throws InvalidIdException {
        Connection conn = dbUtility.connect();
        String sql = "SELECT * FROM product WHERE prod_id = ? ";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);

            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                Product product = new Product();
                product.setId(rst.getInt("prod_id"));
                product.setName(rst.getString("name"));
                product.setPrice(rst.getDouble("price"));

                return product;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbUtility.close();
        } throw new InvalidIdException("Product id is Invalid.");
    }
}
