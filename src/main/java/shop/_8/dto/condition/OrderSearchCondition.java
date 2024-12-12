package shop._8.dto.condition;

import lombok.*;
import shop._8.entity.MemberGrade;
import shop._8.entity.OrderStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderSearchCondition {
    private Long memberId;
    private MemberGrade grade;
    private String orderStatus;

    public OrderStatus getOrderStatus() {
        return OrderStatus.fromValue(orderStatus.toLowerCase());
    }
}
