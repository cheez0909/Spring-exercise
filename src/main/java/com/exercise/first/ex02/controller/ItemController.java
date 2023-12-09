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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String getList(Model model){
        List<Item> all = itemService.findAll();
        model.addAttribute("itemList", all);
        return "/items/itemList";
    }

    /*
    * 상품 수정 폼_231209
    */
    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long id, Model model){
        // id로 해당 상품 조회하기
        Book book = (Book) itemService.findOne(id);

        // 폼 데이터 객체 새로 생성
        BookForm bookForm = new BookForm();

        // 조회한 상품을 폼데이터에 저장
        bookForm.setId(book.getId());
        bookForm.setName(book.getName());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setIsbn(book.getIsbn());
        bookForm.setPrice(book.getPrice());
        bookForm.setStockQuantity(book.getStockQuantity());
        model.addAttribute("bookFrom", bookForm);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long id, @ModelAttribute("bookFrom") BookForm bookForm){
//        엔티티를 생성하지 말 것
//        Book book = new Book();
//
//        book.setId(bookForm.getId());
//        book.setName(bookForm.getName());
//        book.setAuthor(bookForm.getAuthor());
//        book.setIsbn(bookForm.getIsbn());
//        book.setPrice(bookForm.getPrice());
//        book.setStockQuantity(bookForm.getStockQuantity());

        itemService.updateItem(id, bookForm.getName(), bookForm.getPrice(), bookForm.getStockQuantity());

        return "redirect:/items";
    }
}
