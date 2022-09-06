package com.shopbangiay.services;

import com.shopbangiay.interfaces.IVariantValueService;
import com.shopbangiay.models.Variant;
import com.shopbangiay.models.VariantValue;
import com.shopbangiay.utils.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VariantValueService implements IVariantValueService {
    
    Connection conn;
    
    public VariantValueService(){
        conn = ConnectDB.connect();
    }

    @Override
    public List<VariantValue> findAllVariantValues() {
        List<VariantValue> variant_values = new ArrayList<>();
        String sql = "select variants.variant_name, variant_values.* from (variants inner join variant_values on "
                + "variants.variant_id = variant_values.variant_id)";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int variant_id = rs.getInt("variant_id");
                String variant_name = rs.getString("variant_name");
                int variant_value_id = rs.getInt("variant_value_id");
                String value = rs.getString("value");
                Variant variant = new Variant();
                variant.setVariantId(variant_id);
                variant.setVariantName(variant_name);
                
                VariantValue variant_value = new VariantValue();
                variant_value.setVariantValueId(variant_value_id);
                variant_value.setVariant(variant);
                variant_value.setValue(value);
                variant_values.add(variant_value);
            }
        } catch (SQLException e) {
        }
        
        return variant_values;
    }
    
}
