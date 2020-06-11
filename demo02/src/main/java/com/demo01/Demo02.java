package com.demo01;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @ClassName Demo02
 * @Description java 8 日期类学习
 * @Author lktbz
 * @Date 2020/5/27
 */
public class Demo02 {
    public static void main(String[] args) {
        //本地日期类学习 年月日
       // localDateExample();
        //时分秒
//        LocalTimeExample();
        //前两者之和
        //LocalDateTimeExample();
        //立刻
        //InstantExample();
        //api 使用
        //DateAPIUtilities();
        //日期解析与格式化
//        dateParsingAndFormatting();
        
        
         //dateAPILegacySupport();

    }
    private static void dateAPILegacySupport() {
        //Date to Instant
        Instant timestamp = new Date().toInstant();
        //Now we can convert Instant to LocalDateTime or other similar classes
        LocalDateTime date = LocalDateTime.ofInstant(timestamp,
                ZoneId.of(ZoneId.SHORT_IDS.get("PST")));
        System.out.println("Date = "+date);

        //Calendar to Instant
        Instant time = Calendar.getInstance().toInstant();
        System.out.println(time);
        //TimeZone to ZoneId
        ZoneId defaultZone = TimeZone.getDefault().toZoneId();
        System.out.println(defaultZone);

        //ZonedDateTime from specific Calendar
        ZonedDateTime gregorianCalendarDateTime = new GregorianCalendar().toZonedDateTime();
        System.out.println(gregorianCalendarDateTime);

        //Date API to Legacy classes
        Date dt = Date.from(Instant.now());
        System.out.println(dt);

        TimeZone tz = TimeZone.getTimeZone(defaultZone);
        System.out.println(tz);

        GregorianCalendar gc = GregorianCalendar.from(gregorianCalendarDateTime);
        System.out.println(gc);
    }

    private static void dateParsingAndFormatting() {
        //Format examples
        LocalDate date = LocalDate.now();
        //默认的格式化
        System.out.println("默认的格式化"+date);
        //格式化
        System.out.println(date.format(DateTimeFormatter.ofPattern("d::MMM:uuu")));
        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));

        LocalDateTime today = LocalDateTime.now();
        System.out.println("未格式化的date "+ today);
        System.out.println(today.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));
        System.out.println(today.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒")));
        System.out.println(today.format(DateTimeFormatter.BASIC_ISO_DATE));

        //Parse examples
//        LocalDateTime dt = LocalDateTime.parse("27::Apr::2014 21::39::48",
//                DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));
//        System.out.println("Default format after parsing = "+dt);


    }

    private static void DateAPIUtilities() {
        LocalDate today = LocalDate.now();

        //Get the Year, check if it's leap year
        System.out.println("Year "+today.getYear()+" is Leap Year? "+today.isLeapYear());

        //Compare two LocalDate for before and after
        System.out.println("Today is before 01/01/2015? "+today.isBefore(LocalDate.of(2015,1,1)));

        //Create LocalDateTime from LocalDate
        System.out.println("Current Time="+today.atTime(LocalTime.now()));

        //plus and minus operations
        System.out.println("10 days after today will be "+today.plusDays(10));
        System.out.println("3 weeks after today will be "+today.plusWeeks(3));
        System.out.println("20 months after today will be "+today.plusMonths(20));

        System.out.println("10 days before today will be "+today.minusDays(10));
        System.out.println("3 weeks before today will be "+today.minusWeeks(3));
        System.out.println("20 months before today will be "+today.minusMonths(20));

        //Temporal adjusters for adjusting the dates
        System.out.println("First date of this month= "+today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of this year= "+lastDayOfYear);

        Period period = today.until(lastDayOfYear);
        System.out.println("Period Format= "+period);
        System.out.println("Months remaining in the year= "+period.getMonths());
    }

    private static void InstantExample() {
        Instant now = Instant.now();
        Instant now1 = Instant.now(Clock.systemDefaultZone());
        System.out.println("获取的时间为："+now);
        System.out.println("获取的时间为："+now1);

        //Instant from timestamp
        Instant specificTime = Instant.ofEpochMilli(now.toEpochMilli());
        System.out.println("Specific Time = "+specificTime);
    }

    private static void LocalDateTimeExample() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时区的时间为："+now);

        //Current Date using LocalDate and LocalTime
        now = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("Current DateTime="+now);

        LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);
        System.out.println("Specific Date="+specificDate);
    }

    private static void LocalTimeExample() {
        LocalTime now = LocalTime.now();
        System.out.println("现在的时间为"+now);
        LocalTime specificTime=LocalTime.of(12, 20, 25, 40);
        System.out.println("Specific Time of Day="+specificTime);

        LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current Time in IST="+timeKolkata);
        //Getting date from the base date i.e 01/01/1970
        LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
        System.out.println("10000th second time= "+specificSecondTime);
    }

    private static void localDateExample() {
        //获取当前日期
        LocalDate today = LocalDate.now();
        System.out.println("Current Date="+today);
        //根据输入的参数获取日期
        LocalDate firstDay_2014  = LocalDate.of(2014, Month.JANUARY, 20);
        System.out.println("Specific Date="+firstDay_2014);
        //输入一个不存在的日期
        //LocalDate feb29_2014 = LocalDate.of(2014, Month.FEBRUARY, 29);
        //Exception in thread "main" java.time.DateTimeException:
        //Invalid date 'February 29' as '2014' is not a leap year
        LocalDate Kolkata = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Kolkata的日期为"+Kolkata);
        //Getting date from the base date i.e 01/01/1970
        LocalDate dateFromBase = LocalDate.ofEpochDay(1234234); //输入天数算年月日
        System.out.println("365th day from base date= "+dateFromBase);
        LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);//2014年的第一百天
        System.out.println("100th day of 2014="+hundredDay2014);



    }
}
