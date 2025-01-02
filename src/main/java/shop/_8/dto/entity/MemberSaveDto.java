package shop._8.dto.entity;

import lombok.*;
import org.springframework.data.convert.Jsr310Converters;
import shop._8.entity.Member;
import shop._8.entity.MemberGrade;
import shop._8.util.StringToDatetime;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberSaveDto {
    private String username;
    private String email;
    private String password;
    private String phone;
    private LocalDateTime birth;
    private String nickname;
    private MemberGrade grade;

    public Member toEntity() {
        return Member.create(getUsername(), getEmail(), getPassword(), getPhone(), getBirth(), getNickname(), MemberGrade.NORMAL);
    }
}
