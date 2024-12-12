package shop._8.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import shop._8.dto.entity.CategoryDto;
import shop._8.entity.Category;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CategoryServiceTest {

//    @Autowired
//    CategoryService categoryService;
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void saveTest() throws Exception {
//        // given
//        CategoryDto dto1 = new CategoryDto("문구");
//        Category savedDto1 = categoryService.save(dto1);
//        CategoryDto dto2 = new CategoryDto("볼펜", savedDto1.getCategory());
//        CategoryDto dto3 = new CategoryDto("공책", savedDto1.getCategory());
//        Category savedDto2 = categoryService.save(dto2);
//        Category savedDto3 = categoryService.save(dto3);
//
//        em.flush();
//        em.clear();
//
//        // when
//
//        List<Category> all = categoryService.findAll(null);
//        List<Category> all1 = categoryService.findAll(all.get(0).getCategory());
//
//        // then
//
//        Assertions.assertThat(all.size()).isEqualTo(1);
//        Assertions.assertThat(all1.size()).isEqualTo(2);
//    }

}