package com.example.demo.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerApplication extends Application {

    private static CustomerViewController custvc;

    public static void main(String[] args) {
        custvc=new CustomerViewController();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerApplication.class.getResource("/customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 440);
        try{
            primaryStage.setTitle("Customer View");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
