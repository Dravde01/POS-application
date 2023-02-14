package com.example.demo.GUI;

import com.example.demo.api.CashBoxAPI;
import com.example.demo.api.ProductCatalogAPI;
import com.example.demo.api.ProductController;
import com.example.demo.dao.Command;
import com.example.demo.dao.Commands.AddDiscountCommand;
import com.example.demo.dao.Commands.AddNewOrderLineCommand;
import com.example.demo.dao.Commands.RemoveOrderLineCommand;
import com.example.demo.dao.Command;
import com.example.demo.dao.Commands.AddNewOrderLineCommand;
import com.example.demo.model.Order;
import com.example.demo.model.OrderLine;
import com.example.demo.model.Product;
import com.example.demo.service.CardReaderService;
import com.example.demo.service.CashBoxService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class CashierViewController implements Initializable {

    @FXML
    public AnchorPane scannerView;
    @FXML
    public ScannerViewController scannerViewController;
    @FXML
    public Label toPayLabel;
    @FXML
    public Label statusLabel;
    @FXML
    public Label bonusCardLabel;
    @FXML
    public TextField cashInput;
    private CashierApplication ca;
    private CustomerViewController customerViewController;
    private Order currentOrder;

    private CashBoxService cashBoxService;
    private CardReaderService cardReaderService;
    @FXML
    public TextFlow searchResultField;
    @FXML
    public TableView<OrderLine> ca_orderTable;
    @FXML
    public TableView<Product> searchResultTable;
    @FXML
    public TableColumn<Product, String> result_name;
    @FXML
    public TableColumn<OrderLine, Integer> ca_quantity;
    @FXML
    public TableColumn<OrderLine, String> ca_name;
    @FXML
    public TableColumn<OrderLine, Double> ca_price;
    @FXML
    public TextField enterBar;
    @FXML
    public TextField scanBar;
    @FXML
    public TextField searchForProduct;
    @FXML
    public TextField productQuantity;
    private double cashPayed;
    private double tempTotal;

    private double totalPrice1;


    public CashierViewController() {
        this.scannerViewController = new ScannerViewController();
        this.cardReaderService = new CardReaderService();
        this.currentOrder = new Order("1");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ca_quantity.setCellValueFactory( new PropertyValueFactory<OrderLine, Integer>("quantity"));
        ca_name.setCellValueFactory( new PropertyValueFactory<OrderLine, String>("name"));
        ca_price.setCellValueFactory( new PropertyValueFactory<OrderLine, Double>("price"));
    }

    public void registerView(CashierApplication ca){
        this.ca=ca;
    }
    public Order getCurrentOrder() {
        return currentOrder;
    }
    public void executeCommand(Command command) {
        command.execute();
    }

    public boolean orderLineExist(OrderLine orderLine){
        if (ca_orderTable.getItems().contains(orderLine)){
            return true;
        }
        return false;
    }

    public void addThirtyDiscount(MouseEvent mouseEvent) {
        addDiscount(0.3);
    }

    public void addFiftyDiscount(MouseEvent mouseEvent) {
        addDiscount(0.5);
    }

    public void addSeventyDiscount(MouseEvent mouseEvent) {
        addDiscount(0.7);
    }

    private void addDiscount(double discount) {
        OrderLine selectedProduct = ca_orderTable.getSelectionModel().getSelectedItem();
        int quantityToDiscount = productQuantity.getText().isBlank() ? selectedProduct.getQuantity() : Integer.parseInt(productQuantity.getText());
        TableView<OrderLine> cu = customerViewController.getCustomerTable();
        executeCommand(new AddDiscountCommand(cu, ca_orderTable, currentOrder,selectedProduct, quantityToDiscount, discount));
        updateTotalPrice(quantityToDiscount * selectedProduct.getPrice() * discount, false);
    }

    public void updateTotalPrice(double priceChange, boolean positive) {
        Label cu_label = customerViewController.getToPayField();
        if(positive) {
            totalPrice1 = round(totalPrice1 + priceChange, 2);
        }
        else {
            totalPrice1 = round(sub(totalPrice1, priceChange), 2);
        }
        String price = totalPrice1 == 0.0 ? "" : Double.toString(totalPrice1);
        toPayLabel.setText(price);
        cu_label.setText(price);
    }

    public double sub(double one, double two) {
        BigDecimal b1 = new BigDecimal(Double.toString(one));
        BigDecimal b2 = new BigDecimal(Double.toString(two));
        return b1.subtract(b2).doubleValue();
    }

    public void displayShoppingCart(){
        //gets all data about products to both GUI:s
    }

    public void productSearchResults(KeyEvent keyEvent) {
        //input from GUI: Manual product search field
    }

    public void withdrawAmountFromTotal(MouseEvent mouseEvent) {
        //return Payment.calculateToPay()- InputScanner.keyboardInput();
        //substitutes the keyboard input from the total fee of the checkout.
        // if the input doesnt equal the fee, theres either change to be given back or more to pay
        // should return double
    }

    //start by resetting card reader to idle
    //then call waitforpayment
    //wait until customer has completed payment (or failed to do so) and return the result
    public void cardPayment(MouseEvent mouseEvent) throws InterruptedException {
        cardReaderService.resetCardReader();
        statusLabel.setText("Waiting for payment");
        String amountToPay = cashInput.getText().isBlank() ? toPayLabel.getText() : cashInput.getText();
        cardReaderService.waitForPayment(amountToPay);
        do {} while(!cardReaderService.getStatus().equals("DONE"));
        Map<String, Object> result = cardReaderService.getResult();
        String paymentStatus = String.valueOf(result.get("paymentState")).toLowerCase();
        System.out.println(paymentStatus);

        String text = "";
        double priceChange = 0;
        switch (paymentStatus) {
            case "accepted" -> {
                text = "Accepted";
                System.out.println("yes");
                priceChange = Double.parseDouble(toPayLabel.getText());
            }
            case "insufficient_funds" -> {
                statusLabel.setText("Insufficient funds");
            }
            case "unsupported_card" -> statusLabel.setText("Unsupported card");
            case "invalid_pin" -> statusLabel.setText("Invalid PIN");
            case "network_error" -> statusLabel.setText("Network Error");
        }
        statusLabel.setText(text);
        updateTotalPrice(priceChange, false);
    }

    public void addProductToTable(MouseEvent mouseEvent) {
    }

    public void barcodeSearchResults(KeyEvent keyEvent) {
    }

    //if cashbox is closed, open it. Else, do nothing.
    public void openCashbox(MouseEvent mouseEvent) {
        CashBoxAPI cashAPI = new CashBoxAPI();
        cashAPI.openCashbox();
    }

    public void cashPayed(ActionEvent event){
        cashPayed = Double.parseDouble(cashInput.getText());
        tempTotal = Double.parseDouble(toPayLabel.getText());
        toPayLabel.setText(Double.toString(round(tempTotal-cashPayed,2)));
        Label cu_label = customerViewController.getToPayField();
        cu_label.setText(Double.toString(round(tempTotal-cashPayed,2)));
        CashBoxAPI cashAPI = new CashBoxAPI();
        cashAPI.openCashbox();

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    public double getCash(){
        return cashPayed;
    }


    public void abortPayment(MouseEvent mouseEvent) {
        if (cardReaderService.getStatus()=="WAITING_FOR_PAYMENT") {
            cardReaderService.abortPayment();
        }
    }

    //input from GUI: Manually typed barcode
    //Takes a (list) from product catalog and returns matching product(s)
    //adds product(s) to currentOrder and updates GUI table
    @FXML
    public void getBarcode(ActionEvent event) throws IOException{
        ProductCatalogAPI pcAPI = new ProductCatalogAPI();
        Product product = pcAPI.getProductByBarcode(enterBar.getText());
        if(product != null) {
            addProductToSale(product);
        }
    }

    /*
    private void addToCustomer(OrderLine ol) {
        customerViewController.addLineToCustomerTable(ol);
    }
    private void removeFromCustomer(OrderLine ol) {
        customerViewController.removeLineFromCustomerTable(ol);
    }
     */

    public void getProductByName(ActionEvent event) throws IOException {
        ProductCatalogAPI pcAPI = new ProductCatalogAPI();
        Product product = pcAPI.getProductByName(searchForProduct.getText());
        if (product != null) {
            addProductToSale(product);
        }
    }
    public void getProductByKeyword(ActionEvent event) throws IOException {
        ProductCatalogAPI pcAPI = new ProductCatalogAPI();
        Product product = pcAPI.getProductByName(scanBar.getText());
        if (product != null) {
            addProductToSearchTable(product);
        }
    }

    public void addProductToSale(Product product) {
        String orderNum = currentOrder.getOrderNumber();
        OrderLine ol = new OrderLine(orderNum, product);
        setProductQuantity(ol);
        updateTotalPrice(ol.getTotalPrice(), true);
        TableView<OrderLine> cu = customerViewController.getCustomerTable();
        executeCommand(new AddNewOrderLineCommand(cu, ca_orderTable, currentOrder, ol));
    }
    public void addProductToSearchTable(Product product) {
        searchResultTable.getItems().add(product);
        //Displayar inte nånting i GUI
    }

    @FXML
    public void removeProductFromSale(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.DELETE) {
            OrderLine ol = ca_orderTable.getSelectionModel().getSelectedItem();
            int quantityToRemove = Integer.parseInt(productQuantity.getText());
            double priceChange = quantityToRemove >= ol.getQuantity() ? ol.getTotalPrice() : quantityToRemove*ol.getTotalPrice()/ol.getQuantity();
            TableView<OrderLine> cu = customerViewController.getCustomerTable();
            executeCommand(new RemoveOrderLineCommand(cu, ca_orderTable, currentOrder, ol, quantityToRemove, priceChange));
            updateTotalPrice(priceChange, false);
        }
    }

    private void setProductQuantity(OrderLine ol) {
        if(!productQuantity.getText().isEmpty()) {
            ol.changeQuantity(Integer.parseInt(productQuantity.getText()));
        }
    }

    public void registerController(CustomerViewController customerViewController){
        this.customerViewController = customerViewController;
    }
    public void registerController(ScannerViewController scannerViewController){
        this.scannerViewController = scannerViewController;
    }

    public void completePurchase() {
        //print receipt
        //send data to DB
        //wipe orderTable & update currentOrders orderNr
        clearTableForNextOrder();
    }

    public  void clearTableForNextOrder() {
        ca_orderTable.getItems().clear();
        customerViewController.getCustomerTable().getItems().clear();
        currentOrder = new Order(currentOrder.getOrderNumber() + 1);
    }
}
