package com.ecom.controller;

import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.model.Customer;
import com.ecom.model.Product;
import com.ecom.service.CategoryService;
import com.ecom.service.CustomerService;
import com.ecom.service.ProductService;
import com.ecom.service.PurchaseService;

import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        CategoryService categoryService = new CategoryService();
        PurchaseService purchaseService = new PurchaseService();

        Customer customer = new Customer();
        Product product = new Product();
        Category category = new Category();

        while (true){
            System.out.println("\n==========MAIN MENU==========");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Add Product");
            System.out.println("4. Get Product by Category ID");
            System.out.println("5. Add Category");
            System.out.println("6. View Category");
            System.out.println("7. Make Purchase");
            System.out.println("0. Exit");
            System.out.println("==========*********==========");
            System.out.print("\nEnter your choice : ");
            int input = sc.nextInt();
            if (input == 0){
                System.out.println("Exiting... Thankyou");
                break;
            }

            switch (input){
                case 1:
                    System.out.print("Enter Name : ");
                    customer.setName(sc.nextLine());
                    System.out.print("Enter Address : ");
                    customer.setAddress(sc.nextLine());
                    System.out.print("Enter Phone No. : ");
                    customer.setPhone(sc.nextLine());

                    try {
                        customerService.insertCustomer(customer);
                        System.out.println("Customer inserted to DB");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    List<Customer> list = customerService.getAll();
                    list.stream().forEach(l -> System.out.println(l));
                    break;

                case 3:
                    System.out.print("Enter Product Name : ");
                    product.setName(sc.nextLine());
                    System.out.println("Enter Price : ");
                    product.setPrice(sc.nextDouble());
                    System.out.println("Enter Category ID : ");
                    int catID = sc.nextInt();
                    category.setId(catID);
                    product.setCategory(category);

                    try {
                        productService.insertProduct(product);
                        System.out.println("Product added to DB");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:
                    System.out.print("Enter Category ID : ");
                    int catId = sc.nextInt();

                    try {
                        List<Product> prodList = productService.getProdByCatId(catId);
                        if (prodList.isEmpty()){
                            System.out.println("No product found");
                        } else{
                            System.out.println("Product under Category " + catId + " is : ");
                            prodList.forEach(p -> System.out.println(
                                    p.getId() + "\t" + p.getName() + "\t" + p.getPrice()
                            ));
                        }

                    } catch (InvalidIdException e) {
                        System.out.println("Invalid Id " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter Category Name : ");
                    category.setName(sc.next());

                    try {
                        categoryService.insertCategory(category);
                        System.out.println("Category inserted to DB");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 6:
                    List<Category> catList = categoryService.getAllCat();
                    catList.stream().forEach(l -> System.out.println(l));
                    break;

                case 7:
                    System.out.print("Enter Customer ID : ");
                    int cID = sc.nextInt();
                    System.out.print("Enter Product ID : ");
                    int pID = sc.nextInt();

                    try {
                        purchaseService.purchases(cID, pID, sc);
                    } catch (InvalidIdException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidInputException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    break;

                default:
                    System.out.println("Invalid Input!!!!");
                    break;
            }
        }
        sc.close();
    }
}
