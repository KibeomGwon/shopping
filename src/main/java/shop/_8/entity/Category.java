package shop._8.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity {
    @Id
    @GeneratedValue
    private Long categoryId;
    private String category;

    @OneToMany(mappedBy = "category")
    private List<CategoryProduct> cpList = new ArrayList<>();

    @JoinColumn(name = "parent")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory")
    private List<Category> children = new ArrayList<>();

}
