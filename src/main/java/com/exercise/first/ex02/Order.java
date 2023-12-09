package com.exercise.first.ex02;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders") // 테이블 애너테이션 추가
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // m대1 관계 추가
    // 연관관계는 지연로딩으로 설정
    // join으로 멤버와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 1대 M 관계 추가
    // 지연로딩 설정
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderitemList = new ArrayList<>();

    // 1대 1관계추가
    // 지연로딩 설정
    // cascade = CascadeType.ALL ????
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;


    // private Date orderDate;
    private LocalDateTime orderDate; // 주문시간

    // @Enumerated 추가
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderitemList.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    /*
    *  주문 생성 메서드
    * */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem or : orderItems){
            order.addOrderItem(or);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    /*
    * 비즈니스 로직
    * */
    /* 주문 취소 */
    public void cancel(){
        if(delivery.getStatus()==DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송이 시작되었습니다");
        } else{
            this.setStatus(OrderStatus.CANCEL);
            for(OrderItem orderItem : orderitemList){
                orderItem.cancel();
            }
        }
    }

    /* 전체 주문가격 조회 로직  */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderitemList){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
