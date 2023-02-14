package com.example.demo.dao.Commands;

import com.example.demo.dao.Command;
import com.example.demo.model.Order;
import com.example.demo.model.OrderLine;
import javafx.scene.control.TableView;

public class RemoveOrderLineCommand implements Command {
    private final TableView<OrderLine> orderTable2;
    private final TableView<OrderLine> orderTable;
    private final Order currentOrder;
    private OrderLine olToRemove;
    private double priceChange;
    private final int quantity;
    public RemoveOrderLineCommand(TableView<OrderLine> orderTable2, TableView<OrderLine> orderTable, Order currentOrder, OrderLine ol, int quantity, double priceChange) {
        this.orderTable2 = orderTable2;
        this.orderTable = orderTable;
        this.currentOrder = currentOrder;
        this.olToRemove = ol;
        this.quantity = quantity;
        this.priceChange = priceChange;
    }

    @Override
    public void execute() {
        orderTable2.getItems().remove(olToRemove);
        orderTable.getItems().remove(olToRemove);
        currentOrder.removeOrderLine(olToRemove);
        if(olToRemove.getQuantity() > quantity) {
            olToRemove.changeQuantity(olToRemove.getQuantity() - quantity);
            AddNewOrderLineCommand addRemainingItems = new AddNewOrderLineCommand(orderTable2, orderTable, currentOrder, olToRemove);
            addRemainingItems.execute();
        }
    }
}
