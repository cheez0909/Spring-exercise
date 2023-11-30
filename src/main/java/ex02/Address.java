package ex02;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    private String city;
    private String street;
    private String zipcode;

}
