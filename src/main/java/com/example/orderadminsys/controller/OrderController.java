package com.example.orderadminsys.controller;

import com.example.orderadminsys.entity.Customer;
import com.example.orderadminsys.entity.Order;
import com.example.orderadminsys.entity.Product;
import com.example.orderadminsys.entity.User;
import com.example.orderadminsys.service.CustomerService;
import com.example.orderadminsys.service.OrderService;
import com.example.orderadminsys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listOrders(@RequestParam(required = false) Long customerId, @RequestParam(required = false) Long productId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Order> orders;
        if (customerId != null || productId != null) {
            orders = orderService.searchOrders(customerId, productId);
        } else {
            orders = orderService.getAllOrders();
        }
        List<Customer> customers = customerService.getAllCustomers();
        List<Product> products = productService.getAllProducts();
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        return "orders";
    }

    @GetMapping("/add")
    public String addOrderForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Customer> customers = customerService.getAllCustomers();
        List<Product> products = productService.getAllProducts();
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        return "order_form";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        orderService.addOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Order order = orderService.getOrderById(id);
        List<Customer> customers = customerService.getAllCustomers();
        List<Product> products = productService.getAllProducts();
        model.addAttribute("order", order);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        return "order_form";
    }

    @PostMapping("/edit/{id}")
    public String editOrder(@PathVariable Long id, @ModelAttribute Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        order.setId(id);
        orderService.updateOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    @PostMapping("/deleteBatch")
    public String deleteOrdersBatch(@RequestParam String ids, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        orderService.deleteOrdersBatch(idList);
        return "redirect:/orders";
    }
}
