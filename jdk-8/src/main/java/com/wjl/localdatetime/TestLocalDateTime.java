package com.wjl.localdatetime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author wangJiaLun
 * @date 2021-09-17
 **/
public class TestLocalDateTime {

    /**
     *  LocalDate LocalTime LocalDateTime
     */
    @Test
    public void  test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        System.out.println(ldt.getYear());

        LocalDateTime ldt2 = LocalDateTime.of(2015, 10, 10, 13, 22, 33);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.plusDays(2);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt.minusDays(3);
        System.out.println(ldt4);
    }

    /**
     *  Instant: 时间戳(以 Unix 元年: 1970-1-1 00:00:00 到某个时间的毫秒值)
     */
    @Test
    public void test2(){
        // 默认获取 UTC 时区 (世界协调时间)
        Instant ins1 = Instant.now();
        System.out.println(ins1);

        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println(ins1.toEpochMilli());

        Instant ins2 = Instant.ofEpochSecond(1);
        System.out.println(ins2);
    }

    /**
     *  Duration: 计算两个"时间"之间的间隔
     */
    @Test
    public void test3() throws InterruptedException {
        Instant ins1 = Instant.now();
        Thread.sleep(1000);
        Instant ins2 = Instant.now();
        // 计算时间间隔 (开始, 结束)
        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.getSeconds());

        System.out.println("-------------------------------------------");

        LocalTime lt1 = LocalTime.now();
        Thread.sleep(1000);
        LocalTime lt2 = LocalTime.now();
        System.out.println(Duration.between(lt1, lt2).toMillis());
    }

    /**
     *  Period: 计算两个"日期"之间的间隔
     */
    @Test
    public void test4(){
        LocalDate ld1 = LocalDate.of(2020,11,11);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    /**
     *  TemporalAdjuster: 时间校正器
     */
    @Test
    public void test5(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        // 指定天
        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        // 自定义: 下一个工作日
        LocalDateTime ldt5 = ldt.with(x -> {
            LocalDateTime ldt4 = (LocalDateTime) x;
            DayOfWeek dof = ldt4.getDayOfWeek();
            if (dof.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dof.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    /**
     *  DateTimeFormatter: 格式化时间/ 日期
     */
    @Test
    public void test6(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        System.out.println("----------------------------------");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = ldt.format(dtf2);
        System.out.println(strDate2);

        LocalDateTime newDate = ldt.parse(strDate2, dtf2);
        System.out.println(newDate);
    }

    /**
     *  ZonedDate ZonedTime ZonedDateTime
     */
    @Test
    public void test7(){
//        Set<String> set = ZoneId.getAvailableZoneIds();
//        set.forEach(System.out::println);

        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt2);
        ldt2.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt2);
    }
}
