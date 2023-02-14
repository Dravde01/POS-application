package com.example.demo.service;

import com.example.demo.api.CashBoxAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class CashBoxService {
    private final CashBoxAPI cashBoxAPI;

    @Autowired
    public CashBoxService(CashBoxAPI cashBoxAPI) {
        this.cashBoxAPI = cashBoxAPI;
    }

    //methods to connect API with UI controller
    @PostMapping
    public void openCashBox() {
        cashBoxAPI.useCashBox("open");
    }

    @GetMapping
    public String getCashBoxStatus() {
        return cashBoxAPI.useCashBox("status");
    }
}
