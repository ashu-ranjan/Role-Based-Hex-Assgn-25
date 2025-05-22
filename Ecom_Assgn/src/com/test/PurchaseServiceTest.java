package com.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecom.exception.InvalidIdException;
import com.ecom.service.PurchaseService;


public class PurchaseServiceTest {
	
	private PurchaseService purchaseService;

    @BeforeEach
    public void setup() {
        purchaseService = new PurchaseService();
    }

    @Test
    public void testPurchaseWithoutCoupon() {
        // Simulate user input: No coupon
        String simulatedInput = "N\n";
        Scanner scanner = new Scanner(simulatedInput);

        assertDoesNotThrow(() -> purchaseService.purchases(1, 1, scanner));
    }

    @Test
    public void testPurchaseWithValidCoupon() {
        // Simulate user input: Has coupon -> DISCOUNT10
        String simulatedInput = "Y\nBLACK_FRIDAY\n";
        Scanner scanner = new Scanner(simulatedInput);

        assertDoesNotThrow(() -> purchaseService.purchases(1, 1, scanner));
    }

    @Test
    public void testPurchaseWithInvalidCoupon() {
        // Simulate user input: Has coupon -> INVALIDCODE
        String simulatedInput = "Y\nBLACK_MONDAY\n";
        Scanner scanner = new Scanner(simulatedInput);

        assertThrows(IllegalArgumentException.class, () -> {
            purchaseService.purchases(1, 1, scanner); // Coupon.valueOf will fail
        });
    }

    @Test
    public void testPurchaseWithInvalidCustomerId() {
        Scanner scanner = new Scanner("N\n");

        assertThrows(InvalidIdException.class, () -> {
            purchaseService.purchases(-1, 1, scanner); // Invalid customer ID
        });
    }

    @Test
    public void testPurchaseWithInvalidProductId() {
        Scanner scanner = new Scanner("N\n");

        assertThrows(InvalidIdException.class, () -> {
            purchaseService.purchases(1, -1, scanner); // Invalid product ID
        });
    }

}
