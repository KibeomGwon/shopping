package shop._8.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import shop._8.dto.entity.CartDto;
import shop._8.entity.Cart;
import shop._8.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
public class CartServiceTest {
//    @Autowired
//    CartService cartService;
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    ProductService productService;
//    @Autowired
//    CategoryService categoryService;
//
//    @Test
//    public void saveTest() throws Exception {
//        // given
//        Member email = memberService.findByEmail("email");
//        productService.findAll().stream().forEach(product -> {
//            CartDto cartDto = new CartDto(email.getMemberId(), product.getProductId(), 10);
//            cartService.save(cartDto);
//        });
//        // when
//        List<Cart> unorderedByMember = cartService.findUnorderedByMemberId(email.getMemberId());
//        int sum = unorderedByMember.stream().mapToInt(cart -> cart.getOrderQuantity()).sum();
//        // then
//        assertThat(unorderedByMember.size()).isEqualTo(1);
//        assertThat(sum).isEqualTo(10);
//    }
//
//    @Test
//    public void fetchTest() throws Exception {
//        // given
//        Cart findCart = cartService.findOneById(1L);
//        // when
//        int orderQuantity = findCart.getOrderQuantity();
//        cartService.decreaseQuantityById(findCart.getCartId(), 3);
//        int changedOrderQuantity = findCart.getOrderQuantity();
//        cartService.delete(findCart.getCartId());
//
//        // then
//        assertThat(orderQuantity).isEqualTo(10);
//        assertThat(changedOrderQuantity).isEqualTo(3);
//    }
}
