package com.longnh.mininow.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order_placed")
public class OrderPlaced {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_store_id", nullable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "order_customer_id", nullable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "order_shipper_id")
    private Shipper shipper;

    @Column(name = "product_price", nullable = false)
    private long productPrice;

    @Column(name = "ship_price", nullable = false)
    private long shipPrice;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "expected_time", nullable = false)
    private LocalDateTime expectedTime;

    @Column(name = "finished_time")
    private LocalDateTime finishedTime;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private int status;
}
