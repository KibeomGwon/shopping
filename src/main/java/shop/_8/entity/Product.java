package shop._8.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Product extends BaseEntity {
    @Id
    @GeneratedValue
    private Long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String explain;
    @Column(nullable = false)
    private LocalDateTime makingDate;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int quantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<CategoryProduct> cpList = new ArrayList<>();
}