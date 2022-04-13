package com.virtuslab.internship.service;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private final ReceiptGenerator receiptGenerator;
    private final TenPercentDiscount tenPercentDiscount;
    private final FifteenPercentDiscount fifteenPercentDiscount;

    @Autowired
    public ReceiptService(ReceiptGenerator generator, TenPercentDiscount tenPercentDiscount, FifteenPercentDiscount fifteenPercentDiscount) {
        this.receiptGenerator = generator;
        this.tenPercentDiscount = tenPercentDiscount;
        this.fifteenPercentDiscount = fifteenPercentDiscount;
    }

    public Receipt generateReceipt(Basket basket){
        Receipt receiptBeforeDiscounts = receiptGenerator.generate(basket);
        final Receipt receiptAfterFirstDiscount= fifteenPercentDiscount.apply(receiptBeforeDiscounts);
        final Receipt receiptAfterSecondDiscount= tenPercentDiscount.apply(receiptAfterFirstDiscount);

        return receiptAfterSecondDiscount;

    }
}
