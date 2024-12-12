package shop._8.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import shop._8.dto.entity.CartDto;
import shop._8.dto.entity.MemberSaveDto;
import shop._8.dto.entity.OrderSaveDto;
import shop._8.dto.page.OrderPageDto;
import shop._8.entity.Member;
import shop._8.entity.MemberGrade;
import shop._8.entity.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class OrderServiceTest {
//
//    @Autowired
//    OrderService orderService;
//    @Autowired
//    CartService cartService;
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void saveTest() throws Exception {
//        // given
//        OrderSaveDto dto = new OrderSaveDto(1L, "빠른 배달 부탁합니다.");
//
//        Order savedOrder = orderService.save(dto);
//
//        em.flush();
//        em.clear();
//
//        // when
//
//        Order findOrder = orderService.findById(savedOrder.getOrderId());
//
//        // then
//
//        assertThat(findOrder.getOrderPrice()).isEqualTo(10000);
//        assertThat(findOrder.getCarts().size()).isEqualTo(1);
//
//    }
//
//    @Test
//    public void pageTest() throws Exception {
//        // given
//        // when
//
//        OrderPageDto pageDto = orderService.findPageByMemberId(1L, 0);
//        // then
//        assertThat(pageDto.getOrders().size()).isEqualTo(3);
//        assertThat(pageDto.getNow()).isEqualTo(1);
//        assertThat(pageDto.getTotal()).isEqualTo(1);
//    }

}