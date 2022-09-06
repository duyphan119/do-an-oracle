package com.shopbangiay.models;

import java.util.List;

public class Product {
    private int product_id;
    private String product_name, product_alias, thumbnail;
    private int price, new_price;
    private String created_at;
    private List<ProductDetail> product_details;

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public void setProductAlias(String product_alias) {
        this.product_alias = product_alias;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNewPrice(int new_price) {
        this.new_price = new_price;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public void setProductDetails(List<ProductDetail> product_details) {
        this.product_details = product_details;
    }

    public int getProductId() {
        return product_id;
    }

    public List<ProductDetail> getProductDetails() {
        return product_details;
    }
    
    
}
