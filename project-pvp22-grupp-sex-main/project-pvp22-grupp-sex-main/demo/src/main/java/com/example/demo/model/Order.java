package com.example.demo.model;


import java.util.*;

//class to represent one transaction, order or payment
public class Order {

    private final String orderNumber;
    //price basically
    private double orderTotal;
    private Set<OrderLine> orderLineSet;

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
        this.orderLineSet = new HashSet<>();
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void addOrderLine(OrderLine ol) {
        orderLineSet.add(ol);
    }

    public void removeOrderLine(OrderLine ol) {
        orderLineSet.remove(ol);
    }
    public void removeOrderLineByBarcode(String barcode) {
        Optional<OrderLine> olToRemove = orderLineSet.stream().filter(o -> o.getBarcode().equals(barcode)).findFirst();
        if(olToRemove.isPresent()) {
            orderTotal -= olToRemove.get().getTotalPrice();
            orderLineSet.remove(olToRemove.get());
        }
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Set<OrderLine> getOrderLineSet() {
        return orderLineSet;
    }

    public void setOrderLineSet(Set<OrderLine> orderLineSet) {
        this.orderLineSet = orderLineSet;
    }
}
