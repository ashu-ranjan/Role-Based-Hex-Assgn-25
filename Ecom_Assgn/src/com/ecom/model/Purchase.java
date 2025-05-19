package com.ecom.model;

import com.ecom.enums.Coupon;

import java.time.LocalDate;

public class Purchase {
    private int id;
    private LocalDate date;
    private double amount;
    private Coupon Coupon; // Has-a-relation
    private Product product; // Has-a-relation
    private Customer customer; // Has-a-relation

    // Default Constructor
    public Purchase() {};

    // Parameterized Constructor
    public Purchase(int id, LocalDate date, double amount, Coupon coupon, Product product, Customer customer) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        Coupon = coupon;
        this.product = product;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Coupon getCoupon() {
        return Coupon;
    }

    public void setCoupon(Coupon coupon) {
        Coupon = coupon;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // toString()
    @Override
    public String toString() {
        return "Purchase[" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", Coupon=" + Coupon +
                ", product=" + product +
                ", customer=" + customer +
                ']';
    }
}
