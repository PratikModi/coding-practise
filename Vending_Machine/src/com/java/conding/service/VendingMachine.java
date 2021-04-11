package com.java.conding.service;

import com.java.conding.enums.Coins;
import com.java.conding.enums.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by Pratik1 on 09-03-2020.
 */
public interface VendingMachine {

    long selectProductGetPrice(Product product);
    void insertCoin(Coins coins);
    List<Coins> refund();
    Map<Product,List<Coins>> getProductWithChange();
    void reset();

}
