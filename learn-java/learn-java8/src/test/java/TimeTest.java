import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by mazhibin on 16/11/8
 */
public class TimeTest {

    @Test
    public void instantTest() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(100);
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        System.out.println(between);
        System.out.println(between.toMillis());
    }

    @Test
    public void localDateTest(){
        LocalDate now = LocalDate.now();
        LocalDate aDay = LocalDate.of(2016, 9, 10);
        LocalDate aDay2 = LocalDate.of(2016, Month.MARCH, 10);

        Period until = aDay.until(now);
        System.out.println(until);

        System.out.println(now.until(aDay));

        long days = aDay.until(now, ChronoUnit.DAYS);
        System.out.println(days);

        LocalDate nextTuesday = now.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        System.out.println(nextTuesday);
        LocalDate nextOrSameTuesday = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
        System.out.println(nextOrSameTuesday);
    }
}
