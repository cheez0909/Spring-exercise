package com.exercise.first.ex02.controller;


import com.exercise.first.ex02.Item;
import com.exercise.first.ex02.Member;
import com.exercise.first.ex02.Order;
import com.exercise.first.ex02.OrderSearch;
import com.exercise.first.ex02.service.ItemService;
import com.exercise.first.ex02.service.MemberService;
import com.exercise.first.ex02.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;
    @GetMapping(value = "/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findAll();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }
    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        log.info("오더 메서드 -1");
        orderService.order(memberId, itemId, count);
        log.info("오더 메서드 -2");
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        log.info("리스트 -1 ");
        List<Order> order = orderService.findAll(orderSearch);
        log.info("리스트 -2 ");
        model.addAttribute("orders", order);
        log.info("리스트 -3 ");
        return "order/orderList";
    }


    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancel(orderId);
        return "redirect:/orders";
    }
}
