package com.exercise.first.ex02;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 애너테이션 추가
@DiscriminatorColumn(name = "dtype") // 애너테이션 추가
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name; // 상품명 필드 추가
    private int price;
    private int stockQuantity; // 재고 필드 추가

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
