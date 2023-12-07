package com.exercise.first.ex02.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {
    private Long id; // 수정이 있기 때문에 id값을 받아야 함

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
