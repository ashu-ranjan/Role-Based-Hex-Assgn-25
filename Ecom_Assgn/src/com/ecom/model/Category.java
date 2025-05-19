package com.ecom.model;

public class Category {
    private int id;
    private String name;

    // Default Constructor
    public Category() {};

    // Parameterized Constructor
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString()
    @Override
    public String toString() {
        return "Category[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ']';
    }
}
