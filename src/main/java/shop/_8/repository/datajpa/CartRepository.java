package shop._8.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop._8.entity.Cart;
import shop._8.entity.Member;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select c from Cart c join fetch c.product p where c.member = ?1 and c.order is null")
    List<Cart> findAllUnorderedByMember(Member member);

    @Query("select c from Cart c join fetch c.product p where c.member = ?1 and c.order is not null")
    List<Cart> findAllOrderedByMember(Member member);

    @Query("select c from Cart c where c.member = ?1 and c.order is not null")
    List<Cart> findAllUnorderByMember(Member member);
}
