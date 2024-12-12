package shop._8.dto.entity;

import lombok.*;
import shop._8.entity.Cart;
import shop._8.entity.Member;
import shop._8.entity.Product;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long memberId;
    private Long orderId;
    private Long productId;
    private int orderQuantity;

    public CartDto(Long memberId, Long productId, int orderQuantity) {
        this.memberId = memberId;
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }

    public Cart toEntity(Member member, Product product) {
        return Cart.create(member, product, orderQuantity);
    }
}
