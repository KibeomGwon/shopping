package shop._8.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CategoryProduct extends BaseEntity {
    @Id
    @GeneratedValue
    private Long categoryProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    public CategoryProduct(Category category, Product product) {
        this.category = category;
        this.product = product;
        category.getCpList().add(this);
    }
}
