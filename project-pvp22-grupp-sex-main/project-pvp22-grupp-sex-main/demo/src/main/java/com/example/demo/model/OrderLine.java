package com.example.demo.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class OrderLine {
    private final String name;
    private final String barcode;
    private int quantity;
    private double price;
    private double totalPrice;
    private double discount;
    private final String orderNumber;

    private SimpleIntegerProperty tableQuantity;
    private final SimpleStringProperty tableName;
    private final SimpleDoubleProperty tablePrice;
    public OrderLine(String orderNumber, OrderLine ol) {
        this.orderNumber = orderNumber;
        this.name = ol.name;
        this.barcode = ol.getBarcode();
        this.quantity = ol.getQuantity();
        this.price = ol.getPrice();
        this.discount = 0;
        this.tableName = new SimpleStringProperty(name);
        this.tablePrice = new SimpleDoubleProperty(totalPrice);
        this.tableQuantity = new SimpleIntegerProperty(quantity);
        totalPrice = totalPrice + this.price;
    }

    public OrderLine(String orderNumber, Product product) {
        this.orderNumber = orderNumber;
        this.name = product.getName();
        this.barcode = product.getBarcode();
        this.price = product.getPrice();
        this.quantity = 1;
        this.discount = 0;
        this.tableName = new SimpleStringProperty(name);
        this.tablePrice = new SimpleDoubleProperty(price);
        this.tableQuantity = new SimpleIntegerProperty(quantity);
        totalPrice = totalPrice + price;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getQuantity() {
        return quantity;
    }
    public void changeQuantity(int i) {
        this.quantity = i;
        this.tableQuantity = new SimpleIntegerProperty(i);
        this.totalPrice = (1-discount)*quantity*price;
        tablePrice.set(Math.round(totalPrice*100.0)/100.0);
    }

    public SimpleDoubleProperty priceProperty() {
        return tablePrice;
    }

    public SimpleStringProperty nameProperty() {
        return tableName;
    }
    public SimpleIntegerProperty quantityProperty() {
        return tableQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setDiscount(double discount){
        this.discount = discount;
        totalPrice = quantity*price*(1-discount);
        tablePrice.set(Math.round(totalPrice*100.0)/100.0);
    }

    public double getDiscount() {
        return discount;
    }
}
