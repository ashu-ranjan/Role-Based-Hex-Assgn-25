package com.ecom.service;

import com.ecom.dao.CustomerDao;
import com.ecom.dao.ProductDao;
import com.ecom.dao.PurchaseDao;
import com.ecom.dao.impl.CustomerDaoImpl;
import com.ecom.dao.impl.ProductDaoImpl;
import com.ecom.dao.impl.PurchaseDaoImpl;
import com.ecom.enums.Coupon;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Customer;
import com.ecom.model.Product;
import com.ecom.model.Purchase;

import java.time.LocalDate;
import java.util.Scanner;

public class PurchaseService {

    CustomerDao customerDao = new CustomerDaoImpl();
    ProductDao productDao = new ProductDaoImpl();
    PurchaseDao purchaseDao = new PurchaseDaoImpl();

    public void purchases(int cID, int pID, Scanner sc) throws InvalidIdException, InvalidInputException {
        Purchase purchase = new Purchase();
        /*
         * Step 1: Validate customer ID
         * return customers object
         * */

        Customer customer = customerDao.getById(cID);
        purchase.setCustomer(customer);

        /*
         * Step 3: Validate course ID
         * return course object
         * */

        Product product = productDao.getById(pID);
        purchase.setProduct(product);

        System.out.print("Do you have a coupon? (Y/N) : ");
        String ans = sc.next();
        if (ans.equalsIgnoreCase("Y")){
            System.out.print("Enter the code : ");
            String couponCode = sc.next().toUpperCase();

            /* Check : if the coupon code is valid*/
            Coupon coupon = Coupon.valueOf(couponCode);

            double discount = coupon.getDiscount();
            System.out.println("Discount (%) = " + discount);
            double discountedPrice = product.getPrice() - (product.getPrice() * (discount /100));
            System.out.println("Discounted Price is : " + discountedPrice);
            purchase.setCoupon(coupon);
            purchase.setAmount(discountedPrice);
            System.out.println("\nSuccessfully Purchased...");
        }else{
            System.out.println("No coupon applied....");
            System.out.println("\nSuccessfully Purchased...");
            purchase.setAmount(product.getPrice());
        }
        purchase.setDate(LocalDate.now());
        purchaseDao.insertPurchase(purchase);
    }
}
