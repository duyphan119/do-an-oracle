package com.shopbangiay.models;

public class VariantValue {
    private int variant_value_id;
    private String value;
    private Variant variant;

    public void setVariantValueId(int variant_value_id) {
        this.variant_value_id = variant_value_id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }
    
    
}
