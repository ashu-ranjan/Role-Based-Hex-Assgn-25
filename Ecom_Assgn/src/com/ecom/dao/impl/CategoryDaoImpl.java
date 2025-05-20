package com.ecom.dao.impl;

import com.ecom.dao.CategoryDao;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    DBUtility dbUtility = DBUtility.getInstance();

    @Override
    public void insertCategory(Category category) throws InvalidInputException {
        Connection conn = dbUtility.connect();
        String sql = "INSERT INTO category(category_name) VALUES (?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbUtility.close();
        }
    }

    @Override
    public List<Category> getAllCat() {
        Connection conn = dbUtility.connect();
        String sql = "SELECT * FROM category";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                Category category = new Category(
                        rst.getInt("category_id"),
                        rst.getString("category_name")
                );
                list.add(category);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbUtility.close();
        }
        return list;
    }

}
