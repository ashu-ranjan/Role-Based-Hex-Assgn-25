package com.ecom.dao.impl;

import com.ecom.dao.PurchaseDao;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Purchase;
import com.ecom.utility.DBUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PurchaseDaoImpl implements PurchaseDao {

    DBUtility dbUtility = DBUtility.getInstance();

    @Override
    public void insertPurchase(Purchase purchase) throws InvalidInputException {
        Connection conn = dbUtility.connect();
        String sql = "INSERT INTO purchase (date, customer_cust_id, product_prod_id, coupon_used, amount_paid) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, Date.valueOf(LocalDate.now().toString()));
            pstmt.setInt(2, purchase.getCustomer().getCustId());
            pstmt.setInt(3, purchase.getProduct().getId());
            pstmt.setString(4, String.valueOf(purchase.getCoupon()));
            pstmt.setDouble(5, purchase.getAmount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            dbUtility.close();
        }
    }
}
