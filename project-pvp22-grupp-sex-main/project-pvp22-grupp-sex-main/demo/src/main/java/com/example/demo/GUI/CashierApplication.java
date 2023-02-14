package com.example.demo.GUI;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage cashierStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cashier-view.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/customer-view.fxml"));
        FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("/scanner-view.fxml"));

        Scene cashierScene = new Scene(fxmlLoader.load(), 623, 404);
        Scene customerScene = new Scene(fxmlLoader2.load(), 600, 440);
        Scene scannerScene = new Scene(fxmlLoader3.load(), 300, 150);

        CashierViewController cashvc = fxmlLoader.getController();
        CustomerViewController custvc = fxmlLoader2.getController();
        ScannerViewController scanvc = fxmlLoader3.getController();

        // cashier <-> customer
        cashvc.registerController(custvc);
        custvc.registerController(cashvc);

        // cashier <-> scanner
        scanvc.registerController(cashvc);
        cashvc.registerController(scanvc);

        try {
            // Cashier view
            cashierStage.setTitle("Cashier View");
            cashierStage.setScene(cashierScene);
            cashierStage.setX(600);
            cashierStage.setY(0);
            cashierStage.show();

            // Customer view
            Stage customerStage=new Stage();
            customerStage.setTitle("Customer View");
            customerStage.setScene(customerScene);
            customerStage.setX(0);
            customerStage.setY(0);
            customerStage.show();

            // Barcode scanner view
            Stage scannerStage=new Stage();
            scannerStage.setTitle("Barcode Scanner");
            scannerStage.setScene(scannerScene);
            scannerStage.setX(600);
            scannerStage.setY(440);
            scannerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
