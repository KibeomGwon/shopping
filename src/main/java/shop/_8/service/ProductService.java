package shop._8.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop._8.dto.entity.ProductSaveDto;
import shop._8.entity.Category;
import shop._8.entity.Product;
import shop._8.repository.datajpa.CategoryRepository;
import shop._8.repository.datajpa.ProductRepository;
import shop._8.repository.query.ProductQueryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductQueryRepository productQueryRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByName() {
        return null;
    }

    public Product findOneById(Long id) {
        return getProductEntity(id);
    }

    public Product save(ProductSaveDto dto) {
        return productRepository.save(dto.toEntity(getCategoryEntities(dto.getCategories())));
    }

    public void increaseQuantityById(Long id, int quantity) throws IllegalAccessException {
        Product product = getProductEntity(id);
        product.changeQuantity(product.getQuantity() + quantity);
    }

    public void decreaseQuantityById(Long id, int quantity) throws IllegalAccessException {
        Product product = getProductEntity(id);
        product.changeQuantity(product.getQuantity() - quantity);
    }

    private List<Category> getCategoryEntities(List<String> categories) {
        return categories.stream().map(category -> categoryRepository.findByCategory(category).orElseThrow(
                () -> new IllegalArgumentException("카테고리를 찾지 못하였습니다.")
        )).toList();
    }

    private Product getProductEntity(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상품을 찾지 못했습니다."));
    }
}
