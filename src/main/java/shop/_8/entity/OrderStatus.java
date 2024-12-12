package shop._8.entity;


import lombok.Getter;

@Getter
public enum OrderStatus {
    READY("ready"), CAMP("camp"), CANCEL("cancel"), ARRIVE("arrive"), DELIVER("deliver");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    // 문자열로 Enum 찾기
    public static OrderStatus fromValue(String value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}