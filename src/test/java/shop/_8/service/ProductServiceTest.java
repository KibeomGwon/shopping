package shop._8.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import shop._8.dto.entity.CategoryDto;
import shop._8.dto.entity.ProductSaveDto;
import shop._8.entity.Category;
import shop._8.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    EntityManager em;


    void init() {
        CategoryDto dto1 = new CategoryDto("문구");
        Category savedDto1 = categoryService.save(dto1);
        CategoryDto dto2 = new CategoryDto("볼펜", savedDto1.getCategory());
        CategoryDto dto3 = new CategoryDto("공책", savedDto1.getCategory());
        Category savedDto2 = categoryService.save(dto2);
        Category savedDto3 = categoryService.save(dto3);

        em.flush();
        em.clear();
    }

    @Test
    public void saveTest() throws Exception {
        init();
        // given
        ProductSaveDto dto = new ProductSaveDto("0.5 pen", "image1", "explain1", LocalDateTime.now(), 1000, 1000, List.of("볼펜"));
        Product savedProduct = productService.save(dto);

        em.flush();
        em.clear();

        // when
        Product findProduct = productService.findOneById(savedProduct.getProductId());
        // then

        System.out.println(findProduct.getMakingDate());
        assertThat(savedProduct.getProductId()).isEqualTo(findProduct.getProductId());
    }

    @Test
    public void findByQueryTest() throws Exception {
        // given
        init();
        ProductSaveDto dto = new ProductSaveDto("0.5 pen", "image1", "explain1", LocalDateTime.now(), 1000, 1000, List.of("볼펜"));
        Product savedProduct = productService.save(dto);

        em.flush();
        em.clear();
        // when
        Page<Product> pen = productService.findBySearch("pen", null, PageRequest.of(0, 20));
        // then
        assertThat(pen.getTotalPages()).isEqualTo(1);
        assertThat(pen.getContent()).extracting("name").containsExactly("0.5 pen");
    }

}