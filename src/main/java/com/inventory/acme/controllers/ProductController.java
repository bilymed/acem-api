package com.inventory.acme.controllers;

import com.inventory.acme.models.Order;
import com.inventory.acme.models.Product;
import com.inventory.acme.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("server/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    @GetMapping()
    public List<Product> list() {
        return productRepository.findAll();
    }

    @PostMapping("/{order}")
    @ResponseStatus(HttpStatus.OK)
    public void proceedOrder(@RequestBody Order order) {
        String email = order.get_email();
        List<Product> products = productRepository.findAll();
        order.get_items().forEach(item -> {
            for (Product product : products) {
                if (product.getId() == item.getId()) {
                    product.setQuantity(product.getQuantity() - item.getQuantity());
                    productRepository.save(product);
                }
            }
        });
        this.sendEmail(email);
    }

    void sendEmail(String email) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Order");
        msg.setText("Your order has been processed");

        javaMailSender.send(msg);
    }
}
