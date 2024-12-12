package shop._8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long memberId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private LocalDateTime birth;
    private String nickname;
    @Enumerated(value = EnumType.STRING)
    private MemberGrade grade;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    private Member(String username, String email, String password, String phone, LocalDateTime birth, String nickname, MemberGrade grade) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birth = birth;
        this.nickname = nickname;
        this.grade = grade;
    }


    // === ddd === //

    /**
     * 생성 메소드
     */
    public static Member create(String username, String email, String password, String phone, LocalDateTime birth, String nickname, MemberGrade grade) {
        return new Member(username, email, password, phone, birth, nickname, grade);
    }

}
