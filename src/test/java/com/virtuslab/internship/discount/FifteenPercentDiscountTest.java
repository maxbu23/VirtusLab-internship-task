package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FifteenPercentDiscountTest {
    private ProductDb productDb;
    private FifteenPercentDiscount fifteenPercentDiscount;
    private TenPercentDiscount tenPercentDiscount;
    @BeforeEach
    void setup(){
        productDb = new ProductDb();
        fifteenPercentDiscount = new FifteenPercentDiscount();
        tenPercentDiscount = new TenPercentDiscount();
    }

    @Test
    void shouldApply15PercentDiscountWhenInBasketAreAtLeast3GrainProductsAndPriceIsBelow50After15PercentDiscount(){
        //given
        final Product cheese = productDb.getProduct("Cheese");
        final Product bread = productDb.getProduct("Bread");
        final Product cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese,1));
        receiptEntries.add(new ReceiptEntry(bread,2));
        receiptEntries.add(new ReceiptEntry(cereals,1));

        final Receipt receipt = new Receipt(receiptEntries);
        final FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();

        var expectedTotalPrice = cheese.price().add(bread.price().multiply(BigDecimal.valueOf(2))).add(cereals.price()).multiply(BigDecimal.valueOf(0.85));

        //when
        final Receipt receiptAfterDiscount = fifteenPercentDiscount.apply(receipt);

        //then
        assertEquals(expectedTotalPrice,receiptAfterDiscount.totalPrice());
        assertEquals(1,receiptAfterDiscount.discounts().size());
    }

    @Test
    public void shouldApply15PercentDiscountWhenInBasketAreLessThan3GrainProductsAndPriceIsBelow50After15PercentDiscount(){

        //given
        final Product cheese = productDb.getProduct("Cheese");
        final Product bread = productDb.getProduct("Bread");
        final Product cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese,1));
        receiptEntries.add(new ReceiptEntry(bread,1));
        receiptEntries.add(new ReceiptEntry(cereals,1));

        final Receipt receipt = new Receipt(receiptEntries);
        final FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();

        var expectedTotalPrice = cheese.price().add(bread.price()).add(cereals.price());

        //when
        final Receipt receiptAfterDiscount = fifteenPercentDiscount.apply(receipt);

        //then
        assertEquals(expectedTotalPrice,receiptAfterDiscount.totalPrice());
        assertEquals(0,receiptAfterDiscount.discounts().size());
    }

    @Test
    public void shouldApply15PercentDiscountAndThen10PercentDiscountWhenInBasketAreAtLeast3GrainProductsAndPriceIsAbove50After15PercentDiscount(){
        //given
        TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();
        final Product cheese = productDb.getProduct("Cheese");
        final Product bread = productDb.getProduct("Bread");
        final Product cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese,10));
        receiptEntries.add(new ReceiptEntry(bread,2));
        receiptEntries.add(new ReceiptEntry(cereals,1));

        final Receipt receipt = new Receipt(receiptEntries);
        var expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(10)).add(bread.price().multiply(BigDecimal.valueOf(2))).add(cereals.price()).multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.9));

        //when
        final Receipt afterFirstDiscount = fifteenPercentDiscount.apply(receipt);
        final Receipt afterSecondDiscount = tenPercentDiscount.apply(afterFirstDiscount);

        //then
        assertEquals(expectedTotalPrice,afterSecondDiscount.totalPrice());
        assertEquals(2,afterSecondDiscount.discounts().size());
    }

    @Test
    public void shouldApply15PercentDiscountAndThen10PercentDiscountWhenInBasketAreLessThan3GrainProductsAndPriceIsAbove50After15PercentDiscount(){
        //given
        final Product cheese = productDb.getProduct("Cheese");
        final Product bread = productDb.getProduct("Bread");
        final Product cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese,10));
        receiptEntries.add(new ReceiptEntry(bread,1));
        receiptEntries.add(new ReceiptEntry(cereals,1));

        final Receipt receipt = new Receipt(receiptEntries);
        final FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();

        var expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(10)).add(bread.price()).add(cereals.price()).multiply(BigDecimal.valueOf(0.9));

        //when
        final Receipt afterFirstDiscount = fifteenPercentDiscount.apply(receipt);
        final Receipt afterSecondDiscount = tenPercentDiscount.apply(afterFirstDiscount);
        //then
        assertEquals(expectedTotalPrice,afterSecondDiscount.totalPrice());
        assertEquals(1,afterSecondDiscount.discounts().size());
    }
}
