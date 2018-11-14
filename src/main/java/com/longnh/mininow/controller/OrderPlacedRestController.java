package com.longnh.mininow.controller;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.longnh.mininow.model.OrderPlaced;
import com.longnh.mininow.service.OrderPlacedService;
import com.longnh.mininow.util.ConstainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class OrderPlacedRestController {

    @Autowired
    OrderPlacedService orderPlacedService;

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable("id") long id) {
        OrderPlaced order = orderPlacedService.getOrder(id);
        if (order == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @RequestMapping(value = "/order/ongoing/{id}", method = RequestMethod.GET)
    public ResponseEntity getOngoingOrder(@PathVariable("id") long id) {
        List<OrderPlaced> orders = orderPlacedService.getOngoingOrder(ConstainManager.ORDER_DONE, id);
        if (orders == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @RequestMapping(value = "/order/finished/{id}", method = RequestMethod.GET)
    public ResponseEntity getFinishedOrder(@PathVariable("id") long id) {
        List<OrderPlaced> orders = orderPlacedService.getFinishedOrder(ConstainManager.ORDER_PICKED, id);
        if (orders == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order isn't existed");
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody OrderPlaced order) {
        if (order == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is empty");
        OrderPlaced result = orderPlacedService.createOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/order/{id}/status/{status}", method = RequestMethod.GET)
    public ResponseEntity changeOrderStatus(@PathVariable("id") long id, @PathVariable("status") int status) {
        orderPlacedService.changeOrderStatus(status, id);
        return ResponseEntity.status(HttpStatus.OK).body(ConstainManager.RESPONSE_SUCCESS);
    }
}
