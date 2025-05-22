package com.ecom.model;

public class Customer {
    private int custId;
    private String name;
    private String address;
    private String phone;

    // Default constructor
    public Customer() {};

    // Parameterized constructor
    public Customer(int custId, String name, String address, String phone) {
        this.custId = custId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // Getters and setters

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // toString()

    @Override
    public String toString() {
        return "Customer[" +
                "custId=" + custId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ']';
    }
}
