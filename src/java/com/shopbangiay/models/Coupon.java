package com.shopbangiay.models;

import java.util.List;

public class Coupon {
    private String ocupon_id, start_date, end_date;
    private int percent_discount, price_discount;
    private List<Order> orders;

    public void setOcuponId(String ocupon_id) {
        this.ocupon_id = ocupon_id;
    }

    public void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    public void setPercentDiscount(int percent_discount) {
        this.percent_discount = percent_discount;
    }

    public void setPriceDiscount(int price_discount) {
        this.price_discount = price_discount;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    
}
