package com.exercise.first.ex02.controller;

import com.exercise.first.ex02.Book;
import com.exercise.first.ex02.Item;
import com.exercise.first.ex02.repository.ItemRepository;
import com.exercise.first.ex02.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping("/items/new")
    public String createItem(Model model){
        model.addAttribute("item", new BookForm());
        return "/items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm book){
        Book item = new Book(); // setter는 create로 만드는 게 좋음
        item.setName(book.getName());
        item.setPrice(book.getPrice());
        item.setStockQuantity(book.getStockQuantity());
        item.setAuthor(book.getAuthor());
        item.setIsbn(book.getIsbn());
        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping("/items")
    private String getList(Model model){
        List<Item> all = itemService.findAll();
        model.addAttribute("itemList", all);
        return "/items/itemList";
    }
}
