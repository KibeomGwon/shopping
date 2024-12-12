package shop._8.dto.entity;

import lombok.*;
import shop._8.entity.Category;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String category;
    private String parent;

    public CategoryDto(String category) {
        this.category = category;
    }

    public Category toEntity(Category parent) {
        Category entity = null;
        if (parent == null) {
            entity = new Category(getCategory());
        } else {
            entity = new Category(getCategory(), parent);
        }
        return entity;
    }
}
