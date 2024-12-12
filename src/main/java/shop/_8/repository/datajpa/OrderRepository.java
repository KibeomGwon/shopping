package shop._8.repository.datajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import shop._8.entity.Member;
import shop._8.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByMember(Member member);

    Page<Order> findPageByMember(Member member, Pageable pageable);
}
