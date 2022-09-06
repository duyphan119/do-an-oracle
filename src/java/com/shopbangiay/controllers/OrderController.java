package com.shopbangiay.controllers;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shopbangiay.models.Order;
import com.shopbangiay.services.OrderService;
import java.util.List;

@Controller
public class OrderController {
    Gson gson = new Gson();
    
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResponseEntity<String> listAllOrders() {
        OrderService order_service = new OrderService();
        List<Order> orders = order_service.findAllOrders();
        
        return new ResponseEntity<>(gson.toJson(orders), HttpStatus.OK);
    }
}
