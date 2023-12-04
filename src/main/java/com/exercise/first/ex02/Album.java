package com.exercise.first.ex02;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("A") // 애너테이션 추가
public class Album extends Item{
    private String artist;
    private String etc;

}
