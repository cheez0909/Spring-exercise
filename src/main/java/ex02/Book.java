package ex02;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B")  // 애너테이션 추가
@Getter @Setter
public class Book extends Item{
    private String author;
    private String isbn;
}
