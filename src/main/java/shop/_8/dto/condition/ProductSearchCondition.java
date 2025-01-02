package shop._8.dto.condition;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop._8.entity.Category;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchCondition {
    private String productName;
    private List<Category> categories;
}
