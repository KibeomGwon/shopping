package shop._8.dto.entity;

import lombok.*;
import shop._8.entity.Member;
import shop._8.entity.MemberGrade;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveDto {
    private String username;
    private String email;
    private String password;
    private String phone;
    private LocalDateTime birth;
    private String nickname;
    private MemberGrade grade;

    public Member toEntity() {
        return Member.create(getUsername(), getEmail(), getPassword(), getPhone(), getBirth(), getNickname(), getGrade());
    }
}
