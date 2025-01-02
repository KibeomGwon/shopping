package shop._8.repository.datajpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop._8.entity.Member;
import shop._8.entity.MemberGrade;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager em;

    @Test
    public void memberExistsTest() throws Exception {
        // given
        Member member = Member.create("user1", "email1", "password", "01011111111", LocalDateTime.now(), "nickname1", MemberGrade.NORMAL);
        memberRepository.save(member);

        em.flush();
        em.clear();
        // when

        boolean exists = memberRepository.existsMemberByEmail(member.getEmail());
        // then
        assertThat(exists).isTrue();
    }

}