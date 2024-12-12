package shop._8.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop._8.dto.entity.MemberSaveDto;
import shop._8.entity.Member;
import shop._8.repository.datajpa.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id에 해당하는 유저가 없습니다."));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("email에 해당하는 유저가 업습니다."));
    }

    public Member save(MemberSaveDto dto) {
        return memberRepository.save(dto.toEntity());
    }
}
