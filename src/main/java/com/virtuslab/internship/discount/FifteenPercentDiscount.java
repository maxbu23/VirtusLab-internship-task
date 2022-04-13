package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;
import java.util.List;

import static com.virtuslab.internship.product.Product.Type.GRAINS;

public class FifteenPercentDiscount {

    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            BigDecimal totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            List<String> discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt) {
        if(receipt.discounts().contains(TenPercentDiscount.NAME)){
            throw new IllegalStateException("FifteenPercentDiscount must be added previously");
        }

        int count=0;
        for(ReceiptEntry r:receipt.entries()){
            if(r.product().type().equals(GRAINS)){
                count+= r.quantity();
            }
        }
        return count >= 3;
    }
}
