package com.shopbangiay.interfaces;

import com.shopbangiay.models.Order;
import java.util.List;

public interface IOrderService {
    List<Order> findAllOrders();
}
