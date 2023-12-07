package com.exercise.first.ex02.service;

import com.exercise.first.ex02.*;
import com.exercise.first.ex02.exception.NotEnoughStockException;
import com.exercise.first.ex02.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {


    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    // 상품주문
    @Test
    public void 상품주문() {
        Member member = new Member();
        member.setName("회원");
        member.setAddress(new Address("서울", "한강대로", "04332"));
        em.persist(member);

        Book book = new Book();
        book.setName("상실의 시대");
        book.setPrice(10000);
        book.setStockQuantity(20);
        em.persist(book);

        Album album = new Album();
        album.setName("BTS");
        album.setPrice(50000);
        album.setStockQuantity(5);
        em.persist(album);

        int orderCount = 2;

        OrderItem[] orderItem = new OrderItem[2];
        orderItem[0] = OrderItem.createOrderItem(album, album.getPrice() * orderCount, orderCount);
        orderItem[1] = OrderItem.createOrderItem(book, book.getPrice() * orderCount, orderCount);
        Order order1 = Order.createOrder(member, new Delivery(), orderItem);

        Long order = orderService.order(member.getId(), book.getId(), orderCount);
        Order one = orderRepository.findOne(order);

        assertEquals(OrderStatus.ORDER, one.getStatus(), "상품 주문시 상태는 ORDER");
//        one.getOrderitemList().size(); 상품 종류 수
        assertEquals(order1.getOrderitemList().size(), 2);
//        주문가격
        assertEquals(one.getTotalPrice(), book.getPrice() * orderCount);
//        재고
        assertEquals(book.getStockQuantity(), 16);
    }

    // 주문취소
    @Test
    public void 주문취소(){
        Member member = createMember("홍길동", new Address("서울", "강가", "12343"));
        Book book = createBook("상실의시대", 10000, 10);

        int orderCount = 2;

        Long order = orderService.order(member.getId(), book.getId(), orderCount);
        orderService.cancel(order);

        Order one = orderRepository.findOne(order);
        assertEquals(one.getStatus(), OrderStatus.CANCEL);
        assertEquals(book.getStockQuantity(), 10);
    }


    // 상품 주문 시 재고수량 초과
    @Test
    public void 재고수량초과(){
        try {
            Member member = createMember("홍길동", new Address("서울", "강가", "12343"));
            Book book = createBook("상실의시대", 10000, 10);

            int orderCount = 11;

            Long order = orderService.order(member.getId(), book.getId(), orderCount);
        } catch (NotEnoughStockException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("need more stock");
//            assertThrows(NotEnoughStockException.class, () -> System.out.println("예외 발생"));
        }

    }


    private Member createMember(String name, Address address) {

        Member member = new Member();
        member.setName(name);
        member.setAddress(address);
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity){
    Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }
}