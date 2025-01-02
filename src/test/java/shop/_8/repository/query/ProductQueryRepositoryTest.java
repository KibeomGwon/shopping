package shop._8.repository.query;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import shop._8.dto.entity.CategoryDto;
import shop._8.dto.entity.ProductSaveDto;
import shop._8.entity.Category;
import shop._8.entity.Product;
import shop._8.service.CategoryService;
import shop._8.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = false)
class ProductQueryRepositoryTest {

    @Autowired
    ProductQueryRepository queryRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;


    @Test
    public void fetchTest() throws Exception {
        List<Category> all = categoryService.findAll("문구");
        Category 볼펜 = categoryService.findByCategoryName("볼펜");

        // given
//        ProductSaveDto product;
//        for (int i = 0; i < 100; i++) {
//            product = new ProductSaveDto(
//                    "공책" + i,
//                    "공책" + i,
//                    "설명", LocalDateTime.of(2024, 12, 12, i / 24, i / 24),
//                    2000, 1000, List.of("공책"));
//            productService.save(product);
//        }
//
//        for (int i = 0; i < 100; i++) {
//            product = new ProductSaveDto(
//                    "볼펜" + i,
//                    "볼펜" + i,
//                    "설명", LocalDateTime.of(2024, 12, 12, i / 24, i / 24),
//                    2000, 1000, List.of("볼펜"));
//            productService.save(product);
//        }

        // when
//        Page<Product> page =
//                queryRepository.findPageByMemberAndCategory(List.of(볼펜), PageRequest.of(0, 20));
//        // then
//        for (Product findProduct : page.getContent()) {
//            System.out.println(findProduct.getName() + ": " + findProduct.getMakingDate());
//        }
//        assertThat(page.getTotalPages()).isEqualTo(11);
//        assertThat(page.getContent().size()).isEqualTo(20);
//        assertThat(page.getTotalElements()).isEqualTo(201);
    }

}