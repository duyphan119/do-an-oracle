package com.shopbangiay.controllers;

import com.google.gson.Gson;
import com.shopbangiay.models.VariantValue;
import com.shopbangiay.services.VariantValueService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VariantValueController {
    Gson gson = new Gson(); 
    
    VariantValueService variant_value_service = new VariantValueService();
    
    @RequestMapping(value = "/variant-value", method = RequestMethod.GET)
    public ResponseEntity<String> fillAllVariants() {
        List<VariantValue> variants = variant_value_service.findAllVariantValues();
        return new ResponseEntity<>(gson.toJson(variants), HttpStatus.OK);
    }
}
