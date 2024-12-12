package shop._8.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop._8.dto.entity.CartDto;
import shop._8.entity.Cart;
import shop._8.entity.Member;
import shop._8.entity.Product;
import shop._8.repository.datajpa.CartRepository;
import shop._8.repository.datajpa.MemberRepository;
import shop._8.repository.datajpa.ProductRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public List<Cart> findUnorderedByMemberId(Long id) {
        Member member = getMemberEntity(id);
        return cartRepository.findAllUnorderedByMember(member);
    }

    public List<Cart> findOrderedByMemberId(Long id) {
        Member member = getMemberEntity(id);
        return cartRepository.findAllOrderedByMember(member);
    }

    public Cart findOneById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("장바구니를 찾지 못했습니다."));
    }

    public Cart save(CartDto dto) {
        Member member = getMemberEntity(dto.getMemberId());
        Product product = productRepository.findById(dto.getProductId()).orElseThrow(() -> new IllegalArgumentException("상품을 찾지 못했습니다."));
        return cartRepository.save(dto.toEntity(member, product));
    }

    public void delete(Long id) throws IllegalAccessException {
        Cart cart = getCartEntity(id);
        cart.delete();
        cartRepository.delete(cart);
    }

    /**
     * @param id
     * @param quantity
     * @throws IllegalAccessException
     * 상품의 수량은 주문을 해야만 감소하게 되어있음.
     */
    public void increaseQuantityById(Long id, int quantity) throws IllegalAccessException {
        Cart cart = getCartEntity(id);
        cart.changeQuantity(cart.getOrderQuantity() + quantity);
    }

    /**
     * @param id
     * @param quantity
     * @throws IllegalAccessException
     * 상품의 수량은 주문을 해야만 감소하게 되어있음.
     */
    public void decreaseQuantityById(Long id, int quantity) throws IllegalAccessException {
        Cart cart = getCartEntity(id);
        cart.changeQuantity(cart.getOrderQuantity() - quantity);
    }

    // === private method === //

    private Member getMemberEntity(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
    }

    private Cart getCartEntity(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("장바구니를 찾지 못했습니다."));
    }
}
