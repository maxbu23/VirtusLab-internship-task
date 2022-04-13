package com.virtuslab.internship.service;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock private Basket basket;
    private ProductDb productDb;
    private BasketService underTest;


    @BeforeEach
    public void setUp(){
        underTest = new BasketService(basket);
        productDb = new ProductDb();

    }

    @Test
    void canGetAllProducts(){
        //when
        underTest.getAllProducts();
        //then
        verify(basket).getProducts();
    }

    @Test
    public void canAddToBasket(){
        //given
        Product bread = productDb.getProduct("Bread");

        //when
        underTest.addToBasket(bread);

        //then
        ArgumentCaptor<Product> productArgumentCaptor =
                ArgumentCaptor.forClass(Product.class);

        verify(basket)
                .addProduct(productArgumentCaptor.capture());

        final Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(bread);
    }
}
