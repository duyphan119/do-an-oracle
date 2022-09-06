package com.shopbangiay.interfaces;

import com.shopbangiay.models.Product;
import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();
}
