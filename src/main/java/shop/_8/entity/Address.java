package shop._8.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "address")
public class Address extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long addressId;
    private String address;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
