package shop._8.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductTest {
//
//    @PersistenceContext
//    EntityManager em;
//
//    @BeforeEach
//    void createCategory() {
//        Category category1 = new Category("잡화");
//        Category category2 = new Category("볼펜", category1);
//        Category category3 = new Category("공책", category1);
//        category1.addChild(category2);
//        category1.addChild(category3);
//
//        em.persist(category1);
//        em.persist(category2);
//        em.persist(category3);
//
//        em.flush();
//        em.clear();
//    }
//
//    @Test
//    public void createTest() throws Exception {
//        // given
//        Category c = em.createQuery(
//                "select c from Category c where category =: category", Category.class)
//                .setParameter("category", "볼펜").getSingleResult();
//
//        Product product = Product
//                .create("상품1", "image", "explain", LocalDateTime.now(), 10000, 100, List.of(c));
//        em.persist(product);
//
//        em.flush();
//        em.clear();
//        // when
//
////        Product findProduct = em.find(Product.class, product.getProductId());
//        Product findProduct = em.createQuery("select p from Product p join fetch p.cpList cp join fetch cp.category where p.productId =: productId", Product.class)
//                .setParameter("productId", product.getProductId())
//                .getSingleResult();
//
//        findProduct.getCpList().stream().forEach(cp -> System.out.println(cp.getCategory().getCategory()));
//        // then
//        Assertions.assertThat(product.getName()).isEqualTo(findProduct.getName());
//
//    }

}