package com.virtuslab.internship.service;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    private final Basket basket;

    @Autowired
    public BasketService(Basket basket) {
        this.basket = basket;
    }


    public void addToBasket(Product product){
        basket.addProduct(product);
    }

    public List<Product> getAllProducts(){
        return basket.getProducts();
    }

    public Basket getBasket(){
        return this.basket;
    }


}
