package com.example.demo.dao.Commands;

import com.example.demo.dao.Command;
import com.example.demo.model.Order;
import com.example.demo.model.OrderLine;
import javafx.scene.control.TableView;

import java.util.Optional;

public class AddNewOrderLineCommand implements Command {
    private final Order currentOrder;
    private final OrderLine ol;

    private final TableView<OrderLine> orderTable;
    private final TableView<OrderLine> orderTable2;

    public AddNewOrderLineCommand(TableView<OrderLine> orderTable2, TableView<OrderLine> orderTable, Order currentOrder, OrderLine orderLine) {
        this.orderTable2 = orderTable2;
        this.orderTable = orderTable;
        this.currentOrder = currentOrder;
        this.ol = orderLine;
    }

    @Override
    public void execute() {
        Optional<OrderLine> existingProduct = currentOrder.getOrderLineSet().stream().filter(o -> o.getBarcode().equals(ol.getBarcode()) && (Double.toString(o.getDiscount())).equals(Double.toString(ol.getDiscount()))).findFirst();
        if (existingProduct.isPresent()) {
            orderTable2.getItems().remove(existingProduct.get());
            orderTable.getItems().remove(existingProduct.get());
            currentOrder.removeOrderLine(existingProduct.get());
            ol.changeQuantity(ol.getQuantity() + existingProduct.get().getQuantity());
        }
        currentOrder.addOrderLine(ol);
        orderTable.getItems().add(ol);
        orderTable2.getItems().add(ol);
    }
}
