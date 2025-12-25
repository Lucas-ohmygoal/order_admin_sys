package com.example.orderadminsys.service;

import com.example.orderadminsys.entity.Customer;
import com.example.orderadminsys.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> getAllCustomers() {
        return customerMapper.selectAll();
    }

    public Customer getCustomerById(Long id) {
        return customerMapper.selectById(id);
    }

    public void addCustomer(Customer customer) {
        customerMapper.insert(customer);
    }

    public void updateCustomer(Customer customer) {
        customerMapper.update(customer);
    }

    public void deleteCustomer(Long id) {
        customerMapper.deleteById(id);
    }

    public void deleteCustomersBatch(List<Long> ids) {
        customerMapper.deleteBatch(ids);
    }

    public List<Customer> searchCustomers(String keyword) {
        return customerMapper.selectByKeyword(keyword);
    }
}
