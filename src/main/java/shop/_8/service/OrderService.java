package shop._8.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop._8.dto.condition.OrderSearchCondition;
import shop._8.dto.entity.OrderSaveDto;
import shop._8.dto.page.OrderPageDto;
import shop._8.entity.Member;
import shop._8.entity.Order;
import shop._8.repository.datajpa.CartRepository;
import shop._8.repository.datajpa.MemberRepository;
import shop._8.repository.datajpa.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public List<Order> findAllByMemberId(Long id) {
        Member member = getMemberEntity(id);
        return orderRepository.findAllByMember(member);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("주문을 찾지 못했습니다."));
    }

    public OrderPageDto findPageByMemberId(Long id, int pageNum) {
        Member member = getMemberEntity(id);
        Pageable request = PageRequest.of(pageNum, 20, Sort.by("createdDate").descending());
        Page<Order> paged = orderRepository.findPageByMember(member, request);
        return getOrderPageDto(paged, pageNum);
    }

//    public OrderPageDto findPageWithQueryAndMemberId(OrderSearchCondition condition) {
//
//    }

    /**
     * @param dto
     * 주문이 진행안된 카트들을 주문함. ( cart.order == null )
     * order가 생성되면 cart.order에 값이 할당 됨.
     *
     * fetch join을 통해서 product entity를 영속성 컨텍스트에 보관을 함.
     * 이 메소드는 product를 직접 가져오는 동작을 안하기 때문에
     * 만약 fetch join이 없다면 product의 수량이 감소하지 않음.
     */
    public Order save(OrderSaveDto dto) throws IllegalAccessException {
        Member member = getMemberEntity(dto.getMemberId());
        return orderRepository.save(dto.toEntity(member, cartRepository.findAllUnorderedByMember(member)));
    }

    // === private method === //
    private Member getMemberEntity(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
    }

    private OrderPageDto getOrderPageDto(Page<Order> paged, int pageNum) {
        OrderPageDto orderPageDto = new OrderPageDto(++pageNum, paged.getTotalPages());
        paged.getContent().stream().forEach(order -> orderPageDto.getOrders().add(order));
        return orderPageDto;
    }
}
