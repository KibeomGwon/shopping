package shop._8.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter(value = AccessLevel.PRIVATE)
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String image;
    @Column(name = "`explain`", nullable = false)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<CategoryProduct> cpList = new ArrayList<>();

    public Product(String name, String image, String explain, LocalDateTime makingDate, int price, int quantity) {
        this.name = name;
        this.image = image;
        this.explain = explain;
        this.makingDate = makingDate;
        this.price = price;
        this.quantity = quantity;
    }

    // === ddd === //

    /**
     * 생성 메소드
     */
    public static Product create(String name,
                                 String image,
                                 String explain,
                                 LocalDateTime makingDate,
                                 int price,
                                 int quantity,
                                 List<Category> categories) {
        Product product = new Product(name, image, explain, makingDate, price, quantity);
        product.addCps(categories);
        return product;
    }

    /**
     * 카테고리 추가
     *
     * @param categories
     */
    public void addCps(List<Category> categories) {
        categories.stream().forEach(category -> getCpList().add(new CategoryProduct(category, this)));
    }

    /**
     * 물품 수량 변경
     */
    public void changeQuantity(int quantity) throws IllegalAccessException {
        if (quantity < 0) throw new IllegalAccessException("수량이 부족합니다.");
        setQuantity(quantity);
    }
}