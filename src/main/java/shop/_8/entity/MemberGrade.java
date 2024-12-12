package shop._8.entity;

import lombok.Getter;

@Getter
public enum MemberGrade {
    NORMAL("normal"), VIP("vip");

    private final String value;

    MemberGrade(String value) {
        this.value = value;
    }

    public static MemberGrade fromValue(String value) {
        for (MemberGrade grade : MemberGrade.values()) {
            if (grade.value.equals(value)) return grade;
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
