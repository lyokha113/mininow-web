package com.longnh.mininow.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private OrderPlaced order;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total", nullable = false)
    private int total;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "order_item_extra",
            joinColumns = @JoinColumn(name = "order_item_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_id")
    )
    private Set<ProductExtra> extras = new HashSet<>();

}
