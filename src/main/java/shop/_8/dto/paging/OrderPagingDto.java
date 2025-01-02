package shop._8.dto.paging;


import lombok.*;
import shop._8.entity.Order;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPagingDto {
    private int now;
    private int total;
    private List<Order> orders = new ArrayList<>();

    public OrderPagingDto(int now, int total) {
        this.now = now;
        this.total = total;
    }
}
