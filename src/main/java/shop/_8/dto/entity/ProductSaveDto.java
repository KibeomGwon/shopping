package shop._8.dto.entity;

import lombok.*;
import shop._8.entity.Category;
import shop._8.entity.Product;
import shop._8.util.StringToDatetime;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveDto {
    private String name;
    private String image;
    private String explain;
    private String makingDate;
    private int price;
    private int quantity;
    private List<String> categories;

    public Product toEntity(List<Category> categories) {
        return Product.create(getName(), getImage(), getExplain(), StringToDatetime.stringToDatetime(getMakingDate()), getPrice(), getQuantity(), categories);
    }
}
