package com.shopbangiay.interfaces;

import com.shopbangiay.models.CartItem;
import java.util.List;

public interface ICartItemService {
    List<CartItem> findAllCartItemsByAccountId(int account_id);
    
    int updateCartItem(int account_id, int product_detial_id, int new_quantity);
    
    CartItem createCartItem(int account_id, int product_detial_id, int quantity);
    
    int deleteCartItem(int account_id, int product_detial_id);
}
