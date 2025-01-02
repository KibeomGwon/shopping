package shop._8.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop._8.dto.entity.OrderSaveDto;
import shop._8.dto.paging.OrderPagingDto;
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

    public OrderPagingDto findPageByMemberId(Long id, int pageNum) {
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
     * order가 생성되면 cart들의 .order에 값이 할당 됨.
     *
     * fetch join을 통해서 product entity를 영속성 컨텍스트에 보관을 함.
     * 이 메소드는 product를 직접 가져오는 동작을 안하기 때문에
     * 만약 fetch join이 없다면 product의 수량이 감소하지 않음.
     * -> 아닌 것 같음.
     * 저번엔 fetch join을 안 했을 때 수량 변화가 없었는데, 다시 해보니 수량이 변화가 됨.
     * 왜 저번엔 안됐을까
     */
    public Order save(OrderSaveDto dto) throws IllegalAccessException {
        Member member = getMemberEntity(dto.getMemberId());
        return orderRepository.save(dto.toEntity(member, cartRepository.findAllUnorderedByMember(member)));
    }

    /**
     * @param id
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * 주문이 취소될 때 상품의 재고량이 취소된 만큼 증가.
     */
    public Order cancelOrder(Long id) throws IllegalAccessException, IllegalArgumentException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾지 못했습니다."));
        order.cancel();
        return order;
    }

    // === private method === //
    private Member getMemberEntity(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
    }

    private OrderPagingDto getOrderPageDto(Page<Order> paged, int pageNum) {
        OrderPagingDto orderPagingDto = new OrderPagingDto(++pageNum, paged.getTotalPages());
        paged.getContent().stream().forEach(order -> orderPagingDto.getOrders().add(order));
        return orderPagingDto;
    }
}
