package shop._8.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop._8.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c join fetch c.parentCategory p where p.category = ?1")
    List<Category> findAllChildren(String parent);

    Optional<Category> findByCategory(String category);

    @Query("select c from Category c where c.parentCategory is null")
    List<Category> findAllParent();


}
