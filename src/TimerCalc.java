import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TimerCalc {

    /* return LocalDateTime type */
    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }
    public static LocalDateTime getOf(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    /* return String type with selected format */
    public static String getNowToString(String format) {
        return getNow().format(DateTimeFormatter.ofPattern(format));
    }
    public static String getOfToString(LocalDateTime time, String format) {
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    /* these gaps that return long type */
    public static long gapYears(LocalDateTime now, LocalDateTime end) {
        return ChronoUnit.YEARS.between(now, end);
    }
    public static long gapMonths(LocalDateTime now, LocalDateTime end) {
        return ChronoUnit.MONTHS.between(now, end);
    }
    public static long gapDays(LocalDateTime now, LocalDateTime end) {
        return ChronoUnit.DAYS.between(now, end);
    }
    public static long gapHours(LocalDateTime now, LocalDateTime end) {
        return ChronoUnit.HOURS.between(now, end);
    }
    public static long gapMinutes(LocalDateTime now, LocalDateTime end) {
        return ChronoUnit.MINUTES.between(now, end);
    }

    /* these gaps that return String type */
    public static String gapYearsToString(LocalDateTime now, LocalDateTime end) {
        return Long.toString(gapYears(now, end));
    }
    public static String gapMonthsToString(LocalDateTime now, LocalDateTime end) {
        return Long.toString(gapMonths(now, end));
    }
    public static String gapDaysToString(LocalDateTime now, LocalDateTime end) {
        return Long.toString(gapDays(now, end));
    }
    public static String gapHoursToString(LocalDateTime now, LocalDateTime end) {
        return Long.toString(gapHours(now, end));
    }
    public static String gapMinutesToString(LocalDateTime now, LocalDateTime end) {
        return Long.toString(gapMinutes(now, end));
    }
}