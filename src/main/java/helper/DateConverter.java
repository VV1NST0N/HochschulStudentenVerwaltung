package helper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

    public static Date convertToDate(LocalDate date) {
        return java.util.Date.from(date.atStartOfDay()
                .atZone(ZoneId.of("Europe/Berlin"))
                .toInstant());
    }

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone( ZoneId.of("Europe/Berlin")).toLocalDate();
    }
}
