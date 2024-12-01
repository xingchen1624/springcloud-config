package com.lzc.learn;

import com.lzc.learn.pojo.User;

import java.time.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        //1.lambda表达式
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(str -> System.out.println(str));
        Collections.sort(list,(String a,String b) -> a.compareTo(b));
        list.forEach(System.out::println);

        //2.方法引用
        //使用方法引用
        System.out.println("---------------------方法引用---------------------");
        Function<String, Integer> stringToInteger = Integer::parseInt;
        Arrays.asList("1", "2", "3").stream().map(stringToInteger)
                .forEach(System.out::println);

        //3.四大内置核心接口函数
        System.out.println("---------------------四大内置核心接口函数---------------------");
        Predicate<String> predicate = s -> s.length() > 10;
        boolean test = predicate.test("aaaaaaaaaa1");
        System.out.println(test);

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));

        //4.Optional
        System.out.println("---------------------Optional---------------------");
        Optional<Object> obj1 = Optional.ofNullable(null);

        Optional<String> str = Optional.of("1");
        System.out.println(str.get());
        
        //5.Stream
        System.out.println("---------------------Stream---------------------");
        User zhangsan = new User("zhangsan", "1", 10);
        User lisi = new User("lisi", "1", 20);
        User wangwu = new User("wangwu", "1", 30);
        List<User> users = Arrays.asList(zhangsan, lisi, wangwu);
        users.stream().filter(user -> user.getAge() > 20).forEach(System.out::println);
        
        //6.LocalDate,LocalDateTime
        System.out.println("---------------------LocalDate,LocalDateTime---------------------");
        //Clock类通过指定一个时区，可以获取到当前的时刻，日期与时间
        Clock clock = Clock.system(ZoneId.of("Asia/Shanghai"));
        System.out.println("测试Clock：");
        System.out.println(clock.millis());
        System.out.println(clock.instant());

        //Instant 使用方法
        System.out.println("测试Instant：");
        Instant now = Instant.now();
        //精确到秒
        System.out.println(now.getEpochSecond());
        //精确到毫秒
        System.out.println(now.toEpochMilli());


        //LocalDate 以ISO-8601 格式显示的日期类型，无时区信息
        LocalDate date = LocalDate.now();
        LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println("测试LocalDate：");
        System.out.println(date);
        System.out.println(dateFromClock);

        //LocalTime是以ISO-8601 格式显示日期类型，无时区信息
        final LocalTime time = LocalTime.now();
        LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println("测试LocalTime：");
        System.out.println(time);
        System.out.println(timeFromClock);

        //LocalDateTime 以ISO-8601 格式显示的日期和时间
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime datetimeFromClock = LocalDateTime.now(clock);
        System.out.println("测试LocalDateTime：");
        System.out.println(dateTime);
        System.out.println(datetimeFromClock);

        //7.并行数组
        System.out.println("---------------------并行数组---------------------");
        int[] arr = {1, 3, 2, 4, 55, 64, 98, 23};
        Arrays.parallelSort(arr);
        Arrays.stream(arr).forEach(i -> {
            System.out.print(i + " ");
        });
    }
}