package com.shopbangiay.controllers;

import com.google.gson.Gson;
import com.shopbangiay.models.Variant;
import com.shopbangiay.services.VariantService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VariantController {
    Gson gson = new Gson(); 
    
    VariantService variant_service = new VariantService();
    
    @RequestMapping(value = "/variant", method = RequestMethod.GET)
    public ResponseEntity<String> fillAllVariants() {
        List<Variant> variants = variant_service.findAllVariants();
        return new ResponseEntity<>(gson.toJson(variants), HttpStatus.OK);
    }
}
