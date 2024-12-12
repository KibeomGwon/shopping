package shop._8.dto.entity;

import lombok.*;
import shop._8.entity.Cart;
import shop._8.entity.Member;
import shop._8.entity.Order;

import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaveDto {
    private Long memberId;
    private String request;

    public Order toEntity(Member member, List<Cart> carts) throws IllegalAccessException {
        return Order.create(member, getRequest(), carts);
    }
}
