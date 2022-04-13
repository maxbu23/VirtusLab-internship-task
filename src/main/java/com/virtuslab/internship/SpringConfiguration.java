package com.virtuslab.internship;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public Basket generateBasket(){
        return new Basket();
    }

    @Bean
    public ProductDb generateProductDb(){
        return new ProductDb();
    }

    @Bean
    public ReceiptGenerator generateReceiptGenerator(){
        return new ReceiptGenerator();
    }

    @Bean
    public TenPercentDiscount generateTenPercentDiscount(){
        return new TenPercentDiscount();
    }

    @Bean
    public FifteenPercentDiscount generateFifteenPercentDiscount(){
        return new FifteenPercentDiscount();
    }

}
