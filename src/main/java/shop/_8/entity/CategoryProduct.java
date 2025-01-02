package shop._8.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "category_product")
public class CategoryProduct extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_product_id")
    private Long categoryProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public CategoryProduct(Category category, Product product) {
        this.category = category;
        this.product = product;
        category.getCpList().add(this);
    }
}
