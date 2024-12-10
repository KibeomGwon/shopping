package shop._8.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Address extends BaseEntity {
    @Id
    @GeneratedValue
    private Long addressId;
    private String address;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
}
