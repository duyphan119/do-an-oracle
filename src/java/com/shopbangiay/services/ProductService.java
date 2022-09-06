package com.shopbangiay.services;

import com.shopbangiay.interfaces.IProductService;
import com.shopbangiay.models.Product;
import com.shopbangiay.models.ProductDetail;
import com.shopbangiay.models.VariantValue;
import com.shopbangiay.utils.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    
    Connection conn;
    
    public ProductService(){
        conn = ConnectDB.connect();
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "select products.*, product_details.title,product_details.sku,product_details.thumbnail "
                + "as p_thumbnail,product_details.inventory,product_details.product_detail_id,product_details.weight "
                + "from (products inner join product_details on products.product_id = product_details.product_id)";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int size = products.size();
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                String product_alias = rs.getString("product_alias");
                int new_price = rs.getInt("new_price");
                String thumbnail = rs.getString("thumbnail");
                String created_at = rs.getString("created_at");
                
                int product_detail_id = rs.getInt("product_detail_id");
                String p_thumbnail = rs.getString("p_thumbnail");
                String sku = rs.getString("sku");
                String title = rs.getString("title");
                int inventory = rs.getInt("inventory");
                int weight = rs.getInt("weight");
                ProductDetail product_detail = new ProductDetail();
                product_detail.setInventory(inventory);
                product_detail.setProductDetailId(product_detail_id);
                product_detail.setThumbnail(p_thumbnail);
                product_detail.setWeight(weight);
                product_detail.setSku(sku);
                product_detail.setTitle(title);
                product_detail.setProductDetailId(product_detail_id);
                
                Product product = new Product();
                product.setProductId(product_id);
                product.setProductName(product_name);
                product.setProductAlias(product_alias);
                product.setPrice(price);
                product.setNewPrice(new_price);
                product.setThumbnail(thumbnail);
                product.setCreatedAt(created_at);
                
                if(size > 0 && products.get(size - 1).getProductId() == product.getProductId()){
                    List<ProductDetail> product_details =  products.get(size - 1).getProductDetails();
                    product_details.add(product_detail);
                    product.setProductDetails(product_details);
                }else{
                    List<ProductDetail> product_details = new ArrayList<>();
                    product_details.add(product_detail);
                    product.setProductDetails(product_details);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
        }
        
        return products;
    }
    
}
