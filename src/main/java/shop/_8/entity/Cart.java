package shop._8.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Column(nullable = false)
    private int orderQuantity;

    public Cart(Member member, int orderQuantity) {
        this.member = member;
        this.orderQuantity = orderQuantity;
    }

    // === 연관 관계 메소드 === //
    public void addCartToOrder(Order order) {
        setOrder(order);
        order.getCarts().add(this);
    }

    public void changeProduct(Product product) {
        setProduct(product);
        product.getCarts().add(this);
    }

    // === ddd === //

    /**
     * 생성 메소드
     */
    public static Cart create(Member member, Product product, int orderQuantity) {
        Cart cart = new Cart(member, orderQuantity);
        member.getCarts().add(cart);

        cart.changeProduct(product);
        return cart;
    }

    public void changeQuantity(int orderQuantity) {
        setOrderQuantity(orderQuantity);
    }
}
