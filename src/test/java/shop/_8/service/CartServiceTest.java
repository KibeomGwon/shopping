package shop._8.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import shop._8.dto.entity.CartDto;
import shop._8.dto.entity.CategoryDto;
import shop._8.dto.entity.MemberSaveDto;
import shop._8.dto.entity.ProductSaveDto;
import shop._8.entity.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class CartServiceTest {
    @Autowired
    CartService cartService;
    @Autowired
    MemberService memberService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    EntityManager em;
    void init() {
        MemberSaveDto dto = new MemberSaveDto("user1", "email", "string", "1111111", LocalDateTime.of(2000, 10, 2, 00, 00).toString(), "haha", MemberGrade.NORMAL);
        Member savedMember = memberService.save(dto);

        CategoryDto dto1 = new CategoryDto("문구");
        Category savedDto1 = categoryService.save(dto1);
        CategoryDto dto2 = new CategoryDto("볼펜", savedDto1.getCategory());
        CategoryDto dto3 = new CategoryDto("공책", savedDto1.getCategory());
        Category savedDto2 = categoryService.save(dto2);
        Category savedDto3 = categoryService.save(dto3);

        ProductSaveDto productDto = new ProductSaveDto("0.5 pen", "image1", "explain1", LocalDateTime.now().toString(), 1000, 1000, List.of("볼펜"));
        Product savedProduct = productService.save(productDto);

        em.flush();
        em.clear();
    }

    @Test
    public void saveTest() throws Exception {
        init();
        // given
        Member email = memberService.findByEmail("email");

        productService.findAll().stream().forEach(product -> {
            CartDto cartDto = new CartDto(email.getMemberId(), product.getProductId(), 10);
            cartService.save(cartDto);
        });


        // when
        List<Cart> unorderedByMember = cartService.findUnorderedByMemberId(email.getMemberId());
        int sum = unorderedByMember.stream().mapToInt(cart -> cart.getOrderQuantity()).sum();
        // then
        assertThat(unorderedByMember.size()).isEqualTo(1);
        assertThat(sum).isEqualTo(10);
    }

    @Test
    public void fetchTest() throws Exception {
        // given
        Cart findCart = cartService.findOneById(1L);
        // when
        int orderQuantity = findCart.getOrderQuantity();
        cartService.decreaseQuantityById(findCart.getCartId(), 3);
        int changedOrderQuantity = findCart.getOrderQuantity();
        cartService.delete(findCart.getCartId());

        // then
        assertThat(orderQuantity).isEqualTo(10);
        assertThat(changedOrderQuantity).isEqualTo(3);
    }
}
