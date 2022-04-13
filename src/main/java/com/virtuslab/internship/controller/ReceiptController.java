package com.virtuslab.internship.controller;

import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.service.BasketService;
import com.virtuslab.internship.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final BasketService basketService;

    @Autowired
    public ReceiptController(ReceiptService receiptService, BasketService basketService) {
        this.receiptService = receiptService;
        this.basketService = basketService;
    }

    @GetMapping
    public Receipt getReceipt(){
        return receiptService.generateReceipt(basketService.getBasket());
    }
}
