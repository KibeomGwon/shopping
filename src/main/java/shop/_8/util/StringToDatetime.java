package shop._8.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToDatetime {
    public static LocalDateTime stringToDatetime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(date, formatter);
    }
}
