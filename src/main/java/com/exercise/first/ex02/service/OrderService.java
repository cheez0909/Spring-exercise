package com.exercise.first.ex02.service;


import com.exercise.first.ex02.*;
import com.exercise.first.ex02.repository.ItemRepository;
import com.exercise.first.ex02.repository.MemberRepository;
import com.exercise.first.ex02.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        log.info("서비스내의 오더 -1 ");
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);
        log.info("서비스내의 오더 -2 ");
        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        log.info("서비스내의 오더 -3 ");
        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        log.info("서비스내의 오더 -4 ");
        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        log.info("서비스내의 오더 -5 ");
        // 주문저장
        orderRepository.save(order);
        log.info("서비스내의 오더 -6 ");
        return order.getId();
    }



    /**
    * 취소
    * */

    @Transactional
    public void cancel(Long orderId){
        // 주문 엔티티 조회
        Order one = orderRepository.findOne(orderId);

        // 주문 취소
        one.cancel();
    }

    /**
     * 검색
     * */
    public List<Order> findAll(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }


}
