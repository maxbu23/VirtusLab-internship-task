package com.virtuslab.internship.controller;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.service.BasketService;
import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService service;
    private final ProductDb productDb;

    @Autowired
    public BasketController(BasketService service, ProductDb productDb) {
        this.service = service;
        this.productDb = productDb;
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<Product> getAllProduct(){
        return service.getAllProducts();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addProductToBasket(@RequestBody Product product){
        service.addToBasket(productDb.getProduct(product.name()));
    }
}
