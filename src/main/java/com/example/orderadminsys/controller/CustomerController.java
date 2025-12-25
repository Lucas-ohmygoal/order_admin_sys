package com.example.orderadminsys.controller;

import com.example.orderadminsys.entity.Customer;
import com.example.orderadminsys.entity.User;
import com.example.orderadminsys.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listCustomers(@RequestParam(required = false) String keyword, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Customer> customers;
        if (keyword != null && !keyword.isEmpty()) {
            customers = customerService.searchCustomers(keyword);
        } else {
            customers = customerService.getAllCustomers();
        }
        model.addAttribute("customers", customers);
        model.addAttribute("user", user);
        return "customers";
    }

    @GetMapping("/add")
    public String addCustomerForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("customer", new Customer());
        model.addAttribute("user", user);
        return "customer_form";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        customerService.addCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("user", user);
        return "customer_form";
    }

    @PostMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, @ModelAttribute Customer customer, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        customer.setId(id);
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @PostMapping("/deleteBatch")
    public String deleteCustomersBatch(@RequestParam String ids, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        customerService.deleteCustomersBatch(idList);
        return "redirect:/customers";
    }
}
