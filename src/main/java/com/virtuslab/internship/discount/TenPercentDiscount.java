package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;
import java.util.List;

public class TenPercentDiscount {

    public static String NAME = "TenPercentDiscount";
    public final FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
    public Receipt apply(Receipt receipt) {

        if (shouldApply(receipt)) {
            BigDecimal totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.9));
            List<String> discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) > 0;
    }
}
