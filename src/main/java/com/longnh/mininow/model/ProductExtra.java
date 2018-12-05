package com.longnh.mininow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@JsonIgnoreProperties(value={"product", "orderItems"})
@Table(name = "product_extra")
public class ProductExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private int value;

    @Column(name = "is_required", nullable = false)
    private boolean required;

    @ManyToMany(mappedBy = "extras")
    private Set<OrderItem> orderItems = new HashSet<>();

}
