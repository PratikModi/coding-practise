package com.java.conding.service;

import com.java.conding.enums.Coins;
import com.java.conding.enums.Product;
import com.java.conding.inventory.VendingMachineInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Pratik1 on 09-03-2020.
 */
public class VendingMachineImpl implements VendingMachine {
    private VendingMachineInventory<Coins> coinsInv = new VendingMachineInventory<>();
    private VendingMachineInventory<Product> productInventory = new VendingMachineInventory<>();
    private long totalSales;
    private Product selected_product;
    private long coins_entered;

    public VendingMachineImpl() {
        initialize();
    }

    private void initialize(){
        for(Coins coin :Coins.values()){
            coinsInv.put(coin,10);
        }
        for(Product product:Product.values()){
            productInventory.put(product,10);
        }
    }


    @Override
    public long selectProductGetPrice(Product product) {
        if(productInventory.hasItem(product)){
            selected_product = product;
            return product.getValue();
        }else{
            throw new RuntimeException("Product Not Available!!");
        }
    }

    @Override
    public void insertCoin(Coins coins) {
        coins_entered = coins_entered+coins.getValue();
        coinsInv.addItem(coins);
    }

    @Override
    public List<Coins> refund() {
        return null;
    }

    @Override
    public Map<Product, List<Coins>> getProductWithChange() {
        return null;
    }

    @Override
    public void reset() {
        coinsInv.clear();
        productInventory.clear();
    }

    private List<Coins> getChange(long value){
        if(value > 0){
            List<Coins> change = new ArrayList<>();
            long current_balance=value;
            while(current_balance>0){
                if(current_balance>=Coins.QUATER.getValue() &&
                        coinsInv.hasItem(Coins.QUATER)){
                    change.add(Coins.QUATER);
                }else if(current_balance>=Coins.DIME.getValue() &&
                        coinsInv.hasItem(Coins.DIME)){
                    change.add(Coins.DIME);
                }else if(current_balance>=Coins.NICKEL.getValue() &&
                        )
            }
        }
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachineImpl();
        vendingMachine.selectProductGetPrice(Product.COKE);
    }
}
