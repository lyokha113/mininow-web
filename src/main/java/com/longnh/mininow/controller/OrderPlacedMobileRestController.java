package com.longnh.mininow.controller;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.longnh.mininow.model.OrderItem;
import com.longnh.mininow.model.OrderPlaced;
import com.longnh.mininow.model.Product;
import com.longnh.mininow.model.ProductExtra;
import com.longnh.mininow.service.OrderPlacedService;
import com.longnh.mininow.util.ConstainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/mobile/api")
public class OrderPlacedMobileRestController {

    @Autowired
    OrderPlacedService orderPlacedService;

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable("id") long id) {
        OrderPlaced order = orderPlacedService.getOrder(id);
        if (order == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @RequestMapping(value = "/order/ongoing/{id}", method = RequestMethod.GET)
    public ResponseEntity getOngoingOrder(@PathVariable("id") long id) {
        List<OrderPlaced> orders = orderPlacedService.getOngoingOrder(ConstainManager.ORDER_DONE, id);
        if (orders == null || orders.size() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @RequestMapping(value = "/order/finished/{id}", method = RequestMethod.GET)
    public ResponseEntity getFinishedOrder(@PathVariable("id") long id) {
        List<OrderPlaced> orders = orderPlacedService.getFinishedOrder(ConstainManager.ORDER_PICKED, id);
        if (orders == null || orders.size() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody Map<String, Object> request) throws IOException {
        if (request == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is empty");
        OrderPlaced result = orderPlacedService.createOrder(request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/order/{id}/status/{status}", method = RequestMethod.GET)
    public ResponseEntity changeOrderStatus(@PathVariable("id") long id, @PathVariable("status") int status) {
        orderPlacedService.changeOrderStatus(status, id);
        return ResponseEntity.status(HttpStatus.OK).body(ConstainManager.RESPONSE_SUCCESS);
    }
}
