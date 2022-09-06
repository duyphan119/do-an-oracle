package com.shopbangiay.services;

import com.shopbangiay.interfaces.ICartItemService;
import com.shopbangiay.models.CartItem;
import com.shopbangiay.models.Product;
import com.shopbangiay.models.ProductDetail;
import com.shopbangiay.utils.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartItemService implements ICartItemService {

    Connection conn;

    public CartItemService() {
        conn = ConnectDB.connect();
    }

    public CartItem getCartItem(int account_id, int product_detial_id) {
        try {
            String sql = "select product_details.*, products.*,products.thumbnail as p_thumbnail, "
                    + "cart_items.quantity  from "
                    + "(cart_items inner join (product_details inner join products on "
                    + "products.product_id = product_details.product_id) on "
                    + "cart_items.product_detail_id = product_details.product_detail_id "
                    + "and cart_items.account_id = " + account_id + " and cart_items.product_detial_id = "
                    + product_detial_id + ")";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return getCartItemResultSet(rs);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public CartItem getCartItemResultSet(ResultSet rs) {
        try {
            Product product = new Product();
            product.setProductId(rs.getInt("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setProductAlias(rs.getString("product_alias"));
            product.setThumbnail(rs.getString("p_thumbnail"));
            product.setPrice(rs.getInt("price"));
            product.setNewPrice(rs.getInt("new_price"));
            product.setCreatedAt(rs.getString("created_at"));

            ProductDetail product_detail = new ProductDetail();
            product_detail.setProductDetailId(rs.getInt("product_detail_id"));
            product_detail.setTitle(rs.getString("title"));
            product_detail.setSku(rs.getString("sku"));
            product_detail.setInventory(rs.getInt("inventory"));
            product_detail.setWeight(rs.getInt("weight"));
            product_detail.setThumbnail(rs.getString("thumbnail"));
            product_detail.setProduct(product);

            CartItem cart_item = new CartItem();
            cart_item.setProductDetail(product_detail);
            cart_item.setQuantity(rs.getInt("quantity"));
            return cart_item;
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public List<CartItem> findAllCartItemsByAccountId(int account_id) {
        List<CartItem> cart_items = new ArrayList<>();
        String sql = "select product_details.*, products.*,products.thumbnail as p_thumbnail, cart_items.quantity  from "
                + "(cart_items inner join (product_details inner join products on "
                + "products.product_id = product_details.product_id) on "
                + "cart_items.product_detail_id = product_details.product_detail_id "
                + "and cart_items.account_id = " + account_id + ")";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CartItem cart_item = getCartItemResultSet(rs);
                if (cart_item != null) {
                    cart_items.add(cart_item);
                }
            }
        } catch (SQLException e) {
        }
        return cart_items;
    }

    @Override
    public int deleteCartItem(int account_id, int product_detial_id) {
        try {
            String sql = "delete from cart_items where account_id = ? and product_detail_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, account_id);
            pstmt.setInt(2, product_detial_id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
        }
        return 0;
    }

    @Override
    public int updateCartItem(int account_id, int product_detial_id, int new_quantity) {
        try {
            String sql = "exec UpdateCartItemProc(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, account_id);
            pstmt.setInt(2, product_detial_id);
            pstmt.setInt(3, new_quantity);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows;
        } catch (SQLException e) {
        }
        return 0;
    }

    @Override
    public CartItem createCartItem(int account_id, int product_detial_id, int quantity) {
        try {
            CartItem check_cart_item = getCartItem(account_id, product_detial_id);
            if (check_cart_item == null) {
                String sql = "exec CreateCartItemProc(?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, account_id);
                pstmt.setInt(2, product_detial_id);
                pstmt.setInt(3, quantity);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    return getCartItem(account_id, product_detial_id);
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }

}
