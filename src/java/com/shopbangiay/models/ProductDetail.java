package com.shopbangiay.models;

public class ProductDetail {
    private int product_detail_id;
    private String title, sku, thumbnail;
    private int inventory, weight;
    private Product product;

    public void setProductDetailId(int product_detail_id) {
        this.product_detail_id = product_detail_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
