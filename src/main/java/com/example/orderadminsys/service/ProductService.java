package com.example.orderadminsys.service;

import com.example.orderadminsys.entity.Product;
import com.example.orderadminsys.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productMapper.selectAll();
    }

    public Product getProductById(Long id) {
        return productMapper.selectById(id);
    }

    public void addProduct(Product product) {
        productMapper.insert(product);
    }

    public void updateProduct(Product product) {
        productMapper.update(product);
    }

    public void deleteProduct(Long id) {
        productMapper.deleteById(id);
    }

    public void deleteProductsBatch(List<Long> ids) {
        productMapper.deleteBatch(ids);
    }

    public List<Product> searchProducts(String keyword) {
        return productMapper.selectByKeyword(keyword);
    }
}
