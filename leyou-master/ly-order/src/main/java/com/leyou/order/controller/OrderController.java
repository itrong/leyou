package com.leyou.order.controller;

import com.leyou.order.dto.OrderDTO;
import com.leyou.order.pojo.Order;
import com.leyou.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lujianrong
 */
@RestController
@RequestMapping()
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> queryOrderById(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.queryOrderById(id));
    }

    @GetMapping("url/{id}")
    public ResponseEntity<String> createPayUrl(@PathVariable("id") Long orderId){
        String url = orderService.createPayUrl(orderId);
        return ResponseEntity.ok(url);
    }

    @GetMapping("state/{id}")
    public ResponseEntity<Integer> queryOrderState(@PathVariable("id")Long orederId){
        return ResponseEntity.ok(orderService.queryOrderState(orederId).getValue());
    }



}