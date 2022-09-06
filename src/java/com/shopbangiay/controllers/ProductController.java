package com.shopbangiay.controllers;

import com.google.gson.Gson;
import com.shopbangiay.models.Product;
import com.shopbangiay.services.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
    Gson gson = new Gson(); 
    
    ProductService product_service = new ProductService();
    
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<String> fillAllVariants() {
        List<Product> products = product_service.findAllProducts();
        return new ResponseEntity<>(gson.toJson(products), HttpStatus.OK);
    }
}
