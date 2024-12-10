package shop._8.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.management.ConstructorParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    @Id
    @GeneratedValue
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member member;

    private long orderPrice;
    private String request;


    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<Cart> carts = new ArrayList<>();

    private Order(Member member, String request, OrderStatus status) {
        this.member = member;
        this.orderPrice = orderPrice;
        this.request = request;
        this.status = status;
    }

    // === ddd === //
    /**
     * 생성 메소드
     */
    public static Order create(Member member, String request, OrderStatus status, Cart... carts) {
        Order order = new Order(member, request, status);
        order.addCarts(carts);
        order.setOrderPrice(order.getTotalPrice(carts));
        return order;
    }

    // === private 메소드 === //
    private long getTotalPrice(Cart[] carts) {
        return Arrays.stream(carts)
                .mapToLong(cart -> cart.getOrderQuantity() * cart.getProduct().getPrice())
                .sum();
    }

    private void addCarts(Cart[] carts) {
        Arrays.stream(carts).forEach(cart -> cart.addCartToOrder(this));
    }
}
