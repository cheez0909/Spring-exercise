package com.exercise.first.ex02;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private Member member;

    private List<Orderitem> orderitemList;

    private Delivery delivery;
    private Date orderDate;
    private OrderStatus status;

}
