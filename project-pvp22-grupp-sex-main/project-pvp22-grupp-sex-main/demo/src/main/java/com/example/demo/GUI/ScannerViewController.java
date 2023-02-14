package com.example.demo.GUI;

import com.example.demo.api.ProductCatalogAPI;
import com.example.demo.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ScannerViewController {

    @FXML
    public TextField barcodeScan;
    private CashierViewController cashierViewController;

    public ScannerViewController(){
    }

    @FXML
    public void addProductToSale(ActionEvent event) throws IOException {
        ProductCatalogAPI pcAPI = new ProductCatalogAPI();
        Product product = pcAPI.getProductByBarcode(barcodeScan.getText());
        if(product != null) {
            cashierViewController.addProductToSale(product);
        }
    }

    public void registerController(CashierViewController cashierViewController) {
        this.cashierViewController = cashierViewController;
    }

}
