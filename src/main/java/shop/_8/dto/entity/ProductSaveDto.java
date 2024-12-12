package shop._8.dto.entity;

import lombok.*;
import shop._8.entity.Category;
import shop._8.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveDto {
    private String name;
    private String image;
    private String explain;
    private LocalDateTime makingDate;
    private int price;
    private int quantity;
    private List<String> categories;

    public Product toEntity(List<Category> categories) {
        return Product.create(getName(), getImage(), getExplain(), getMakingDate(), getPrice(), getQuantity(), categories);
    }
}
