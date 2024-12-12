package shop._8.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import shop._8.dto.entity.MemberSaveDto;
import shop._8.entity.Member;
import shop._8.entity.MemberGrade;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberServiceTest {
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void saveTest() throws Exception {
//        // given
//        MemberSaveDto dto = new MemberSaveDto("user1", "email", "string", "1111111", LocalDateTime.of(2000, 10, 2, 00, 00), "haha", MemberGrade.NORMAL);
//        Member savedMember = memberService.save(dto);
//
//        em.flush();
//        em.clear();
//        // when
//        Member findMember = memberService.findByEmail("email");
//        List<Member> all = memberService.findAll();
//        // then
//        Assertions.assertThat(savedMember.getMemberId()).isEqualTo(findMember.getMemberId());
//        Assertions.assertThat(all.size()).isEqualTo(1);
//
//    }
}
