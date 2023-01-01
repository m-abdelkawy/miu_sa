package com.example.webshop.domain.shopping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
    @Id
    private String productNumber;
    private double price;
    private String description;

    public Product() {
    }

    public Product(String productNumber, String description, double price) {
        this.productNumber = productNumber;
        this.description = description;
        this.price = price;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber='" + productNumber + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
