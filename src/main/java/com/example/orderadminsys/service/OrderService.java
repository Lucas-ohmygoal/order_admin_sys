package com.example.orderadminsys.service;

import com.example.orderadminsys.entity.Order;
import com.example.orderadminsys.entity.Product;
import com.example.orderadminsys.mapper.OrderMapper;
import com.example.orderadminsys.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    public List<Order> getAllOrders() {
        return orderMapper.selectAllWithDetails();
    }

    public Order getOrderById(Long id) {
        return orderMapper.selectById(id);
    }

    public void addOrder(Order order) {
        // Calculate total price
        Product product = productMapper.selectById(order.getProductId());
        if (product != null) {
            BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            order.setTotalPrice(totalPrice);
        }
        orderMapper.insert(order);
    }

    public void updateOrder(Order order) {
        // Recalculate total price
        Product product = productMapper.selectById(order.getProductId());
        if (product != null) {
            BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            order.setTotalPrice(totalPrice);
        }
        orderMapper.update(order);
    }

    public void deleteOrder(Long id) {
        orderMapper.deleteById(id);
    }

    public void deleteOrdersBatch(List<Long> ids) {
        orderMapper.deleteBatch(ids);
    }

    // For search, simple
    public List<Order> searchOrders(Long customerId, Long productId) {
        return orderMapper.selectByCustomerOrProduct(customerId, productId);
    }
}
