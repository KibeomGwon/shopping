package shop._8.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int orderQuantity;

    public Cart(Member member, Product product, int orderQuantity) {
        this.member = member;
        this.orderQuantity = orderQuantity;
        this.product = product;
    }

    // === 연관 관계 메소드 === //
    public void order(Order order) {
        setOrder(order);
    }

    // === ddd === //

    /**
     * 생성 메소드
     */
    public static Cart create(Member member, Product product, int orderQuantity) {
        Cart cart = new Cart(member, product, orderQuantity);

        // 상품과 유저 연관관계 적용
        product.getCarts().add(cart);
        member.getCarts().add(cart);

        return cart;
    }

    /**
     * 장바구니의 상품 수량을 바꿈
     * @param orderQuantity
     */
    public void changeQuantity(int orderQuantity) throws IllegalAccessException {
        if (getProduct().getQuantity() - orderQuantity < 0) {
            throw new IllegalAccessException("수량이 부족합니다.");
        } else {
            setOrderQuantity(orderQuantity);
        }
    }

    /**
     * 장바구니 삭제
     */
    public void delete() throws IllegalAccessException {
        getProduct().changeQuantity(getProduct().getQuantity() + getOrderQuantity());
    }
}
