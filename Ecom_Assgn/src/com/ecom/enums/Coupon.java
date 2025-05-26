package com.ecom.enums;

public enum Coupon {
    DIWALI20(20),
    BLACK_FRIDAY(25),
    SUMMER_SALE(10),
    NEW_YEAR_SALE(15);

    private int discount;

    Coupon(int discount){
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
