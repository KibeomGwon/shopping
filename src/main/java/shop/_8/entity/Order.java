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
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private long orderPrice;
    private String request;


    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<Cart> carts = new ArrayList<>();

    private Order(Member member, String request) {
        this.member = member;
        this.request = request;
        this.status = OrderStatus.CAMP;
    }

    // === 연관관계 메소드 === //

    // === ddd === //
    /**
     * 생성 메소드
     */
    public static Order create(Member member, String request, List<Cart> carts) throws IllegalAccessException {
        // 주문 생성
        Order order = new Order(member, request);

        // 장바구니들 추가 -> 총 가격 계산 -> 상품 물량 감소
        order.addCarts(carts);
        order.setOrderPrice(order.getTotalPrice(carts));
        order.reduceProductQuantity();

        // 유저 연관관계
        member.getOrders().add(order);

        return order;
    }

    /**
     * 주문 취소 메소드
     */

    public void cancel() throws IllegalAccessException {
        increaseProductQuantity();
        setStatus(OrderStatus.CANCEL);
    }

    // === private 메소드 === //
    private long getTotalPrice(List<Cart> carts) {
        return carts.stream()
                .mapToLong(cart -> cart.getOrderQuantity() * cart.getProduct().getPrice())
                .sum();
    }

    private void addCarts(List<Cart> carts) {
        carts.stream().forEach(cart -> {
            getCarts().add(cart);
            cart.order(this);
        });
    }

    /**
     * 주문 시 물량을 감소시키는 메소드
     */
    private void reduceProductQuantity() throws IllegalAccessException {
        for (Cart cart : getCarts()) {
            cart.getProduct().changeQuantity(cart.getProduct().getQuantity() - cart.getOrderQuantity());
        }
    }

    /**
     * 주문 취소시 물량을 증가시키는 메소드
     */
    private void increaseProductQuantity() throws IllegalAccessException {
        for (Cart cart : getCarts()) {
            cart.getProduct().changeQuantity(cart.getProduct().getQuantity() + cart.getOrderQuantity());
        }
    }
}
