package com.example.guimodule;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CustomerViewController {

    private CustomerApplication ca;

    private Text t=new Text("15");
    private TextFlow toPayField = new TextFlow(t);

    public void registerView(CustomerApplication ca){
        this.ca=ca;
    }

    //test method. Doesn't work
    public void displayToPay(){
        Text t= new Text ("15");
        toPayField.getChildren().add(t);
    }
}
