package com.ecom.dao;

import com.ecom.exception.InvalidInputException;
import com.ecom.model.Purchase;

public interface PurchaseDao {
    void insertPurchase(Purchase purchase) throws InvalidInputException;
}
