package com.example.demo.api;

import com.example.demo.HttpController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashBoxAPI {

    private String operation;

    //base code for connecting to cashbox API
    public String useCashBox(String operation){

        String BaseURL="http://localhost:9001/cashbox/";
        return BaseURL+operation;
    }

    public void openCashbox() {
        HttpController.postRequest(useCashBox("open"));
    }

    public void cashboxStatus() {
        HttpController.getRequest(useCashBox("status"));
    }
}
