package shop._8.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long categoryId;
    @Column(unique = true)
    private String category;

    @OneToMany(mappedBy = "category")
    private List<CategoryProduct> cpList = new ArrayList<>();

    @JoinColumn(name = "parent")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> children = new ArrayList<>();

    public Category(String category) {
        this.category = category;
    }

    public Category(String category, Category parentCategory) {
        this.category = category;
        this.parentCategory = parentCategory;
    }

    // === 연관관계 메소드 === //
    public void addChild(Category child) {
        getChildren().add(child);
        setParentCategory(this);
    }
}
