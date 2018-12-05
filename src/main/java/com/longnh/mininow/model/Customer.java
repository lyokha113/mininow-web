package com.longnh.mininow.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "uid", updatable = false, nullable = false)
    private String uid;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "img_url", nullable = false)
    private String imgURL;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

}
