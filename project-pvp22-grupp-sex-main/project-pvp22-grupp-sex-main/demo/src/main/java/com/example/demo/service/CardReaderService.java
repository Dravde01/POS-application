package com.example.demo.service;

import com.example.demo.api.CardReaderAPI;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

public class CardReaderService {

    private final CardReaderAPI cardReaderAPI;

    public CardReaderService() {
        this.cardReaderAPI = new CardReaderAPI();
    }

    //methods to connect API with UI controller

    public String getStatus() {
        return cardReaderAPI.getCardReaderStatus();
    }

    public Map<String, Object> getResult() {
        return cardReaderAPI.getCardReaderResult("result");
    }


    public void resetCardReader() {
        cardReaderAPI.resetCardReader();
    }

    public void abortPayment() {
        cardReaderAPI.abortPayment();
    }

    public void waitForPayment(String amount) {
        cardReaderAPI.waitForPayment(amount);
        getStatus();
    }
}
