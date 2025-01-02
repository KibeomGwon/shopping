package shop._8.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import shop._8.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    boolean existsMemberByEmail(String email);
}
