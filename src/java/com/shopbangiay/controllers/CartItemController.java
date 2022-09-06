package com.shopbangiay.controllers;

import com.google.gson.Gson;
import com.shopbangiay.models.CartItem;
import com.shopbangiay.services.CartItemService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartItemController {
    Gson gson = new Gson(); 
    
    CartItemService cart_item_service = new CartItemService();
    
    @RequestMapping(value = "/cart-item", method = RequestMethod.GET)
    public ResponseEntity<String> fillAllCartItems() {
        List<CartItem> cart_items = cart_item_service.findAllCartItemsByAccountId(2);
        return new ResponseEntity<>(gson.toJson(cart_items), HttpStatus.OK);
    }
}
