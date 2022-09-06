package com.shopbangiay.services;

import com.shopbangiay.interfaces.IVariantService;
import com.shopbangiay.models.Variant;
import com.shopbangiay.utils.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VariantService implements IVariantService {
    
    Connection conn;
    
    public VariantService(){
        conn = ConnectDB.connect();
    }

    @Override
    public List<Variant> findAllVariants() {
        List<Variant> variants = new ArrayList<>();
        String sql = "select * from variants";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int variant_id = rs.getInt("variant_id");
                String variant_name = rs.getString("variant_name");
                Variant variant = new Variant();
                variant.setVariantId(variant_id);
                variant.setVariantName(variant_name);
                variants.add(variant);
            }
        } catch (SQLException e) {
        }
        
        return variants;
    }
    
}
