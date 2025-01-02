package shop._8.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop._8.dto.condition.ProductSearchCondition;
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

    /**
     * @param productName
     * @param categories
     * @param pageable
     * @return
     * ProductSearchCondition의 필드들은 더 생각을 해봐야 할 것 같다.
     */
    public Page<Product> findBySearch(String productName, List<Category> categories, Pageable pageable) {
        ProductSearchCondition condition = new ProductSearchCondition(productName, categories);
        Page<Product> pageByMemberAndCategory = productQueryRepository.findPageByMemberAndCategory(condition, pageable);
        return pageByMemberAndCategory;
    }

    public Product save(ProductSaveDto dto) {
        return productRepository.save(dto.toEntity(getCategoryEntities(dto.getCategories())));
    }

    /**
     * @param id
     * @param quantity
     * 상품의 재고량을 증가시킴.
     * cartService와 orderService랑 무관함.
     */
    public void increaseQuantityById(Long id, int quantity) throws IllegalAccessException {
        Product product = getProductEntity(id);
        product.changeQuantity(product.getQuantity() + quantity);
    }

    /**
     * @param id
     * @param quantity
     * 상품의 재고량을 감소시킴
     * cartService와 orderService랑 무관함.
     */
    public void decreaseQuantityById(Long id, int quantity) throws IllegalAccessException {
        Product product = getProductEntity(id);
        product.changeQuantity(product.getQuantity() - quantity);
    }

    // === private method === //

    private List<Category> getCategoryEntities(List<String> categories) {
        return categories.stream().map(category -> categoryRepository.findByCategory(category).orElseThrow(
                () -> new IllegalArgumentException("카테고리를 찾지 못하였습니다.")
        )).toList();
    }

    private Product getProductEntity(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상품을 찾지 못했습니다."));
    }
}
