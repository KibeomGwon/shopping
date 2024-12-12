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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderTest {

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
//        Product product1 = Product
//                .create("상품1", "image", "explain", LocalDateTime.now(), 10000, 100, List.of(category2));
//        Product product2 = Product.create("상품2", "image", "explain", LocalDateTime.now(), 9000, 140, List.of(category2));
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
//
//        em.flush();
//        em.clear();
//    }
//
//    @Test
//    public void orderTest() throws Exception {
//        // @Before
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
//        Product product2 = Product.create("상품2", "image", "explain", LocalDateTime.now(), 9000, 140, List.of(category2));
//        Product product3 = Product.create("상품3", "image", "explain", LocalDateTime.now(), 15000, 110, List.of(category3));
//        Product product4 = Product.create("상품4", "image", "explain", LocalDateTime.now(), 12000, 200, List.of(category3));
//
//        em.persist(product1);
//        em.persist(product2);
//        em.persist(product3);
//        em.persist(product4);
//
//        Member member = Member.create("member1", "email1", "1111", "01011111111", LocalDateTime.of(2000, 2, 11, 0, 0), "member1", MemberGrade.NORMAL);
//        em.persist(member);
//
//        em.flush();
//        // given
//        // 카트 생성
//        Cart cart1 = Cart.create(member, product2, 3);
//        Cart cart2 = Cart.create(member, product3, 1);
//        Cart cart3 = Cart.create(member, product1, 2);
//
//        em.persist(cart1);
//        em.persist(cart2);
//        em.persist(cart3);
//
//        Order order = Order.create(member, "배달 시 문 앞에 나둬 주세요", List.of(cart1, cart2, cart3));
//        em.persist(order);
//
//        em.flush();
//        em.clear();
//        // when
//        Order findOrder = em.find(Order.class, order.getOrderId());
//        Cart findCart2 = findOrder.getCarts().get(1);
//        int quantity = findCart2.getProduct().getQuantity();
//
//        // then
//        assertThat(findOrder.getOrderPrice()).isEqualTo(62000);
//        assertThat(quantity).isEqualTo(109);
//
//        // when2
//
//        findOrder.cancel();
//        findCart2 = findOrder.getCarts().get(1);
//        quantity = findCart2.getProduct().getQuantity();
//        // then2
//        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
//        assertThat(quantity).isEqualTo(110);
//    }

}