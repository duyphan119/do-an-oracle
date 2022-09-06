package com.shopbangiay.models;

import java.util.List;

public class Order {
    private int order_id;
    private String province, district, ward, address;
    private int shipping_price, total_price;
    private String created_at;
    private OrderStatus order_status;
    private Account account;
    private Coupon coupon;
    private List<OrderItem> order_items;

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setShippingPrice(int shipping_price) {
        this.shipping_price = shipping_price;
    }

    public void setTotalPrice(int total_price) {
        this.total_price = total_price;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public void setOrderStatus(OrderStatus order_status) {
        this.order_status = order_status;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public void setOrderItems(List<OrderItem> order_items) {
        this.order_items = order_items;
    }

    public int getOrderId() {
        return order_id;
    }

    public List<OrderItem> getOrderItems() {
        return order_items;
    }
    
    
}
