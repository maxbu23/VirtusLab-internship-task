package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        Map<Product,Integer> productsMap = new HashMap<>();

        for(Product p: basket.getProducts()){

            if(!productsMap.containsKey(p)) {
                productsMap.put(p,1);
            }
            else {
                productsMap.put(p, productsMap.get(p) + 1);
            }
        }
        productsMap.forEach((product, integer) -> receiptEntries
                .add(new ReceiptEntry(product,integer)));

        return new Receipt(receiptEntries);
    }
}
