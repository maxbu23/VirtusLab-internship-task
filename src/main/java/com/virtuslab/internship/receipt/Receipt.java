package com.virtuslab.internship.receipt;

import com.virtuslab.internship.discount.TenPercentDiscount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record Receipt(
        List<ReceiptEntry> entries,
        List<String> discounts,
        BigDecimal totalPrice) {
    private static TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();

    public Receipt(List<ReceiptEntry> entries) {
        this(entries,
                new ArrayList<>(),
                entries.stream()
                        .map(ReceiptEntry::totalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

}
