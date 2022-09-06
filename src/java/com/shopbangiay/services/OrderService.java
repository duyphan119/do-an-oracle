package com.shopbangiay.services;

import com.shopbangiay.interfaces.IOrderService;
import com.shopbangiay.models.Coupon;
import com.shopbangiay.models.Order;
import com.shopbangiay.models.OrderItem;
import com.shopbangiay.models.OrderStatus;
import com.shopbangiay.models.Product;
import com.shopbangiay.models.ProductDetail;
import com.shopbangiay.utils.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    private Connection conn;
    
    public OrderService(){
        conn = ConnectDB.connect();
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "select orders.*, order_statuses.*,coupons.*,order_items.*,order_items.price as item_price "
                    + "product_details.*, products.*, products.thumbnail as p_thumbnail "
                    + "from ((orders inner join (order_items inner join (product_details inner join products "
                    + "on product_details.product_id = products.product_id) on order_items.product_detail_id ="
                    + "product_details.product_detail_id) on orders.order_id = order_items.order_id) "
                    + "inner join coupons on orders.coupon_id = coupons.coupon_id) inner join "
                    + "order_statuses on orders.order_status_id = order_statuses.order_status_id";
           
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int size = orders.size();
      
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductAlias(rs.getString("product_alias"));
                product.setThumbnail(rs.getString("p_thumbnail"));
                product.setPrice(rs.getInt("price"));
                product.setNewPrice(rs.getInt("new_price"));
                 
                ProductDetail product_detail = new ProductDetail();
                product_detail.setProductDetailId(rs.getInt("product_detail_id"));
                product_detail.setThumbnail(rs.getString("thumbnail"));
                product_detail.setTitle(rs.getString("title"));
                product_detail.setSku(rs.getString("sku"));
                product_detail.setWeight(rs.getInt("weight"));
                product_detail.setProduct(product);

                Coupon coupon = new Coupon();
                coupon.setOcuponId(rs.getString("coupon_id"));
                coupon.setStartDate(rs.getString("start_date"));
                coupon.setEndDate(rs.getString("end_date"));
                coupon.setPercentDiscount(rs.getInt("percent_discount"));
                coupon.setPriceDiscount(rs.getInt("price_discount"));
                
                OrderStatus order_status = new OrderStatus();
                order_status.setOrderStatusId(rs.getInt("order_status_id"));
                order_status.setOrderStatusName(rs.getString("order_status_name"));
                
                OrderItem order_item = new OrderItem();
                order_item.setProductDetail(product_detail);
                order_item.setQuantity(rs.getInt("quantity"));
                order_item.setPrice(rs.getInt("item_price"));
                
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setProvince(rs.getString("province"));
                order.setDistrict(rs.getString("district"));
                order.setWard(rs.getString("ward"));
                order.setAddress(rs.getString("address"));
                order.setShippingPrice(rs.getInt("shipping_price"));
                order.setTotalPrice(rs.getInt("total_price"));
                order.setOrderStatus(order_status);
                order.setCoupon(coupon);
                
                if(size > 0 && orders.get(size - 1).getOrderId() == order.getOrderId()){
                    orders.get(size - 1).getOrderItems().add(order_item);
                }else{
                    List<OrderItem> order_items = new ArrayList<>();
                    order_items.add(order_item);
                    order.setOrderItems(order_items);
                    orders.add(order);
                }
            }                  

        } catch (SQLException e) {
            
        }
        return orders;
    }
}
