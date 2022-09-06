package com.shopbangiay.models;

public class OrderItem {
    private Order order;
    private ProductDetail product_detail;
    private int price, quantity;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProductDetail(ProductDetail product_detail) {
        this.product_detail = product_detail;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
