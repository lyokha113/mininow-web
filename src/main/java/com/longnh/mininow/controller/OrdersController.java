package com.longnh.mininow.controller;


import com.longnh.mininow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/orders")
public class OrdersController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView productPage() {
        return new ModelAndView("orders_list");
    }

}
