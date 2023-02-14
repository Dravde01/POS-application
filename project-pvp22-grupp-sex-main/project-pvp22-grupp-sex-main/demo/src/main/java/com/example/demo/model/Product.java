package com.example.demo.model;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.List;

public class Product {

    private final String barcode;
    private final String name;
    private int VAT;
    private double price;
    //private List<String> keyWords;
    private final SimpleStringProperty tableName;


    public SimpleStringProperty result_nameProperty(){
        return tableName;
    }

    public Product(String barcode, String name, int VAT, List<String> keyWords) {
        this.barcode = barcode;
        this.name = name;
        this.VAT = VAT;
        this.tableName = new SimpleStringProperty(name);
        //this.keyWords = keyWords;

        setPrice();
    }

    public Product(String barcode, String name) {
        this.barcode = barcode;
        this.name = name;
        this.tableName = new SimpleStringProperty(name);
        setPrice();
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        switch (barcode) {
            case "111" -> this.price = 9.99;
            case "222" -> this.price = 19.99;
            case "333" -> this.price = 29.99;
            case "420" -> this.price = 39.99;
        }
    }

    //Barcode, Price
    /*Map<String, Double> pricemap;

    pricemap.get("111")
    pricemap.put("111", 29.0)
     */
}