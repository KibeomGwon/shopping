package shop._8.dto.page;


import lombok.*;
import shop._8.entity.Order;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class OrderPageDto {
    private int now;
    private int total;
    private List<Order> orders = new ArrayList<>();

    public OrderPageDto(int now, int total) {
        this.now = now;
        this.total = total;
    }
}
