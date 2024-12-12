package shop._8.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop._8.entity.*;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartRepositoryTest {
//
//    @Autowired
//    CartRepository cartRepository;
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void test() throws Exception {
//        // given
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
//        Product product1 = Product
//                .create("상품1", "image", "explain", LocalDateTime.now(), 10000, 100, List.of(category2));
//        Product product2 = Product.create("상품2", "image", "explain", LocalDateTime.now(), 9000, 140, List.of(category3));
//        Product product3 = Product.create("상품3", "image", "explain", LocalDateTime.now(), 15000, 110, List.of(category3));
//        Product product4 = Product.create("상품4", "image", "explain", LocalDateTime.now(), 12000, 200, List.of(category3));
//
//        em.persist(product1);
//        em.persist(product2);
//        em.persist(product3);
//        em.persist(product4);
//
//        Member member = Member.create("member1", "email1", "1111", "01011111111", LocalDateTime.of(2000, 2, 11, 0, 0), "member1", MemberGrade.NORMAL);
//
//        em.persist(member);
//
//        Cart cart = Cart.create(member, product1, 2);
//        Cart cart2 = Cart.create(member, product2, 3);
//        Cart cart3 = Cart.create(member, product3, 4);
//
//        em.persist(cart);
//        em.persist(cart2);
//        em.persist(cart3);
//
//        em.flush();
//        em.clear();
//
//
//
//        // when
//        Member findMember = memberRepository.findByEmail("email").get();
//        List<Cart> allByMember = cartRepository.findAllOrderedByMember(member);
//
//        // then
//        int totalQuantity = 0;
//        for (Cart cart1 : allByMember) {
//            totalQuantity += cart1.getOrderQuantity();
//        }
//        Assertions.assertThat(totalQuantity).isEqualTo(9);
//    }

}