package shop._8.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop._8.dto.entity.CategoryDto;
import shop._8.entity.Category;
import shop._8.repository.datajpa.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * @param parent
     * parent가 있으면 parent의 자식 리스트를 반환,
     * parent가 없으면 parent 리스트를 반환,
     * 카테고리 데이터가 없으면 빈 리스트를 반환.
     */
    public List<Category> findAll(String parent) {
        List<Category> categories = new ArrayList<>();
        if (parent == null) {
            categories = categoryRepository.findAllParent();
        } else {
            categories = categoryRepository.findAllChildren(parent);
        }
        return categories;
    }

    public Category findByCategoryName(String name) {
        return categoryRepository.findByCategory(name).orElseThrow(() -> new IllegalArgumentException("없는 카테고리 입니다."));
    }

    /**
     * @param dto
     * dto에 부모필드가 있으면 엔티티에 부모를 설정,
     * 없으면 부모필드를 null로 설정.
     */
    public Category save(CategoryDto dto) {
        Category parent = null;
        if (dto.getParent() != null) {
            parent = categoryRepository.findByCategory(dto.getParent()).orElse(null);
        }
        Category category = dto.toEntity(parent);
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
