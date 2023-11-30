package ex02;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    // @Embedded 애너테이션 추가
    @Embedded
    private Address address;

    // 1대m 관계 추가
    // 컬렉션은 필드에서 초기화하는 것이 null 문제에서 안전하다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
