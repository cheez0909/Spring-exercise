package com.exercise.first.ex02;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Orderitem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private Order order;
    private Item item;

    private int orderprice;
    private int count;
}
