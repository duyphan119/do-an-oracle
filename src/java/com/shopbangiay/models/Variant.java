package com.shopbangiay.models;

import java.util.List;

public class Variant {
    private int variant_id;
    private String variant_name;
    private List<VariantValue> variant_values;

    public void setVariantId(int variant_id) {
        this.variant_id = variant_id;
    }

    public void setVariantName(String variant_name) {
        this.variant_name = variant_name;
    }

    public void setVariantValues(List<VariantValue> variant_values) {
        this.variant_values = variant_values;
    }
}
