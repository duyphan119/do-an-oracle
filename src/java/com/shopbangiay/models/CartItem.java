package com.shopbangiay.models;

public class CartItem {
    private ProductDetail product_detail;
    private Account account;
    private int quantity;

    public void setProductDetail(ProductDetail product_detail) {
        this.product_detail = product_detail;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
