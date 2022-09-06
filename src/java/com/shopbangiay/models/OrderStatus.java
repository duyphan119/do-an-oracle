package com.shopbangiay.models;

import java.util.List;

public class OrderStatus {
    private int order_status_id;
    private String order_status_name;
    private List<Order> orders;

    public void setOrderStatusId(int order_status_id) {
        this.order_status_id = order_status_id;
    }

    public void setOrderStatusName(String order_status_name) {
        this.order_status_name = order_status_name;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    
}   
