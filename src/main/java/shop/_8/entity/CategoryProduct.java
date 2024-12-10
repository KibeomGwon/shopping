package shop._8.entity;

import jakarta.persistence.*;

@Entity
public class CategoryProduct extends BaseEntity {
    @Id
    @GeneratedValue
    private Long categoryProductId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Product product;
}
