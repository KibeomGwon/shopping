package shop._8.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop._8.dto.entity.*;
import shop._8.dto.paging.OrderPagingDto;
import shop._8.entity.*;
import shop._8.repository.datajpa.CartRepository;
import shop._8.repository.datajpa.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @Autowired
    MemberService memberService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    EntityManager em;


    void init() {
        MemberSaveDto dto = new MemberSaveDto("user1", "email", "string", "1111111", LocalDateTime.of(2000, 10, 2, 00, 00), "haha", MemberGrade.NORMAL);
        Member savedMember = memberService.save(dto);

        CategoryDto dto1 = new CategoryDto("문구");
        Category savedDto1 = categoryService.save(dto1);
        CategoryDto dto2 = new CategoryDto("볼펜", savedDto1.getCategory());
        CategoryDto dto3 = new CategoryDto("공책", savedDto1.getCategory());
        Category savedDto2 = categoryService.save(dto2);
        Category savedDto3 = categoryService.save(dto3);

        ProductSaveDto productDto = new ProductSaveDto("0.5 pen", "image1", "explain1", LocalDateTime.now(), 1000, 1000, List.of("볼펜"));
        Product savedProduct = productService.save(productDto);


        productService.findAll().stream().forEach(product -> {
            CartDto cartDto = new CartDto(savedMember.getMemberId(), product.getProductId(), 10);
            cartService.save(cartDto);
        });

        em.flush();
        em.clear();
    }

    @Test
    public void saveTest() throws Exception {
        init();
        Member email = memberService.findByEmail("email");
        // given
        OrderSaveDto dto = new OrderSaveDto(email.getMemberId(), "빠른 배달 부탁합니다.");

        Order savedOrder = orderService.save(dto);

        em.flush();
        em.clear();

        // when

        Order findOrder = orderService.findById(savedOrder.getOrderId());

        // then

        assertThat(findOrder.getOrderPrice()).isEqualTo(10000);
        assertThat(findOrder.getCarts().size()).isEqualTo(1);

        findOrder.getCarts().stream().forEach(cart -> {
            assertThat(cart.getProduct().getQuantity()).isEqualTo(990);
        });

    }

    @Test
    public void saveTest2() throws Exception {
        // given
        init();
        Member email = memberService.findByEmail("email");

        OrderSaveDto dto = new OrderSaveDto(email.getMemberId(), "빠른 배달 부탁합니다.");

        Order order = dto.toEntity(email, cartRepository.findAllUnorderByMember(email));

        Order savedOrder = orderRepository.save(order);

        em.flush();
        em.clear();

        // when

        Order findOrder = orderService.findById(savedOrder.getOrderId());

        // then

        findOrder.getCarts().stream().forEach(cart -> {
            assertThat(cart.getProduct().getQuantity()).isEqualTo(990);
        });
    }

    @Test
    public void pageTest() throws Exception {
        // given
        // when

        OrderPagingDto pageDto = orderService.findPageByMemberId(1L, 0);
        // then
        assertThat(pageDto.getOrders().size()).isEqualTo(3);
        assertThat(pageDto.getNow()).isEqualTo(1);
        assertThat(pageDto.getTotal()).isEqualTo(1);
    }

    @Test
    public void deleteTest() throws Exception {
        // given
        init();

        Member email = memberService.findByEmail("email");
        // given
        OrderSaveDto dto = new OrderSaveDto(email.getMemberId(), "빠른 배달 부탁합니다.");

        Order savedOrder = orderService.save(dto);

        em.flush();
        em.clear();
        // when
//        Order findOrder = orderService.findById(savedOrder.getOrderId());
//        findOrder.cancel();
//
//        findOrder.getCarts().stream().forEach(cart -> assertThat(cart.getProduct().getQuantity()).isEqualTo(1000));
//        // then
        Order findOrder = orderService.cancelOrder(savedOrder.getOrderId());

        findOrder.getCarts().stream().forEach(cart -> assertThat(cart.getProduct().getQuantity()).isEqualTo(1000));
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }

}