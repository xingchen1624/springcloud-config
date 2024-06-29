/**
 * Copyright (C), 2012-2024, by xavier chen
 * FileName: TestJdk8
 * Author:   xingc
 * Date:     2024/5/19 17:20
 * Description: jdk1.8新特性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉
 * 〈jdk1.8新特性〉
 *
 * @author xingc
 * @date 2024/5/19
 * @since 1.0.0
 **/
public class TestJdk8 {
    public static void main(String[] args) {
        /***
         * lambda表达式
         * 语法：(parameters) -> expression 或者 (parameters) -> { statements; }
         * (parameters)参数可以是0个或多个，如果只有1个，那么小括号可以省略
         **/
        //1.使用lambda表达式实现函数式接口
        System.out.println("----------------------1-----------------------");
        TestInterface testInterface = name -> System.out.println(name);
        testInterface.myName("张三");

        //2.使用lambda表达式简化比较器Comparator
        System.out.println("-----------------------2----------------------");
        List<String> strings = Arrays.asList("apple", "banana", "orange");
        Collections.sort(strings, (String a,String b) -> b.compareTo(a));
        strings.forEach(item -> System.out.println(item));

        /*3.1 Stream API特点
        流式操作：Stream API 使用流式操作，允许对数据进行流式处理，逐个处理每个元素而不是一次性处理整个集合。
        链式调用：Stream API 中的操作可以进行链式调用，使得代码更加简洁和易读。
        惰性求值：Stream API 中的操作通常是惰性求值的，只有在终端操作被调用时才会触发中间操作的执行，这有助于提高性能。
        支持并行：Stream API 支持并行操作，可以利用多核处理器进行并行计算，提高程序的性能。
        3.2 Stream API 操作类型
        Stream API 中的操作可以分为两种类型：中间操作和终端操作。
        中间操作用于对流中的元素进行处理和转换，如过滤、映射、排序等。
        终端操作用于触发流的执行，并产生最终的结果，如收集、计数、查找等。*/

        //3.list转map
        System.out.println("-------------------------3--------------------");
        testListToMap();

        /*方法引用分为三种，可以通过一对双冒号:: 来表示，方法引用是一种函数式接口的另一种书写方式
        静态方法引用，通过类名::静态方法名， 如 Integer::parseInt
        实例方法引用，通过实例对象::实例方法，如 str::substring
        构造方法引用，通过类名::new， 如 User::new*/
                
        //4.Steam API的中间操作
        //4.1 过滤长度大于5的字符串
        System.out.println("-------------------------4.1--------------------");
        List<String> list = Arrays.asList("apple", "banana", "orange", "grape", "watermelon");
        list.stream().filter(item -> item.length()>5).collect(Collectors.toList())
                .forEach(item -> System.out.println(item));

        System.out.println("-------------------------4.2--------------------");
        //4.2 映射转换大写并排序
        list.stream().map(String::toUpperCase).sorted().collect(Collectors.toList())
                .forEach(item -> System.out.println(item));

        //5.Steam API的终端操作
        //5.1 计算集合中长度为5的字符串数量
        System.out.println("-------------------------5.1--------------------");
        long count = list.stream().filter(item -> item.length() > 5).count();
        System.out.println(count);
        
        //5.2 查找第一个以字母"g"开头的字符串
        System.out.println("-------------------------5.2--------------------");
        String strg = list.stream().filter(str -> str.startsWith("g")).findFirst()
                .orElse("无");
        System.out.println("first g word:"+strg);
        
        //6.Optional类特点和用法
        /***
         * ① 避免空指针异常
         * 使用 Optional 类可以帮助我们更加安全地处理可能为 null 的值，避免出现空指针异常。通过 Optional 类，我们可以对可能为 null 的值进行包装和处理，从而使得代码更加健壮和可靠。
         *
         * ② 方法链操作
         * Optional 类提供了丰富的方法来进行操作，如 map、flatMap、filter、orElse、orElseGet 等。这些方法可以通过方法链的方式来组合使用，从而实现对包装值的各种操作和转换。
         *
         * ③ 提倡使用 Optional 类
         * Java 8 开始，官方提倡在返回值可能为 null 的情况下，使用 Optional 类作为方法的返回值类型，以便提醒调用者可能会得到空值，并且鼓励调用者使用 Optional 类来处理返回值。
         *
         * ④ 不鼓励在字段上使用
         * 虽然 Optional 类对于方法的返回值是非常适用的，但是对于字段（成员变量）的使用却不太合适。官方不推荐在字段上直接使用 Optional 类，因为这样可能会增加额外的复杂性。
         * 
         **/
        System.out.println("-------------------------6--------------------");
        // 创建一个包装值为空的Optional对象
        Optional<String> emptyOptional = Optional.empty();

        // 创建一个包装非空值的Optional对象
        Optional<String> nonEmptyOptional = Optional.of("Hello, world!");

        // 创建一个可能为null的Optional对象
        String str = null;
        Optional<String> nullableOptional = Optional.ofNullable(str);

        // 检查Optional对象是否包含值
        System.out.println("Is empty optional? " + emptyOptional.isPresent()); // false
        System.out.println("Is non-empty optional? " + nonEmptyOptional.isPresent()); // true
        System.out.println("Is nullable optional? " + nullableOptional.isPresent()); // false

        // 获取Optional对象中的值
        String value = nonEmptyOptional.get();
        System.out.println("Value: " + value); // "Hello, world!"

        // 使用orElse方法获取值，如果值为空则返回默认值
        String defaultValue = nullableOptional.orElse("Default Value");
        System.out.println("Default value: " + defaultValue); // "Default Value"

        //7.新的日期和时间 API
        /***
         * LocalDate：用于表示日期，不包含时间和时区信息。
         * LocalTime：用于表示时间，不包含日期和时区信息。
         * LocalDateTime：用于表示日期和时间，不包含时区信息。
         * Instant：表示时间戳，与 Unix 时间类似，精确到纳秒。
         * Duration：表示两个时间点之间的时间间隔。
         * Period：表示两个日期之间的时间间隔，以年、月、日为单位。
         * ZoneId：用于表示时区。
         * ZonedDateTime：带有时区的日期和时间。
         * 
         **/
        System.out.println("-------------------------7--------------------");
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate);

        // 获取当前时间
        LocalTime currentTime = LocalTime.now();
        System.out.println("Current Time: " + currentTime);

        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current Date and Time: " + currentDateTime);

        // 根据指定日期和时间创建对象
        LocalDate date = LocalDate.of(2022, Month.FEBRUARY, 14);
        LocalTime time = LocalTime.of(12, 30, 0);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        System.out.println("Custom Date and Time: " + dateTime);

        // 格式化日期和时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("Formatted Date and Time: " + formattedDateTime);
        
        //8.CompletableFuture 类
        /***
         * ① 异步执行
         * CompletableFuture 类支持异步执行任务，可以通过 supplyAsync()、runAsync() 等静态方法来创建一个异步任务，并返回一个 CompletableFuture 对象，该对象表示了任务的执行状态和结果。
         *
         * ② 依赖关系
         * CompletableFuture 类支持任务之间的依赖关系，可以通过 thenApply()、thenAccept()、thenCompose() 等方法来构建任务之间的依赖关系，实现任务的串行或并行执行。
         *
         * ③ 异常处理
         * CompletableFuture 类提供了丰富的方法来处理异步任务的异常情况，如 exceptionally()、handle() 等方法可以处理任务执行过程中抛出的异常，并返回默认值或执行备选操作。
         *
         * ④ 回调函数
         * CompletableFuture 类支持回调函数的方式来处理异步任务的结果，如 thenApply()、thenAccept()、thenRun() 等方法可以在任务执行完成后自动调用指定的回调函数。
         *
         * ⑤ 组合操作
         * CompletableFuture 类支持各种组合操作，如 allOf()、anyOf() 等静态方法可以组合多个 CompletableFuture 对象，实现一组任务的并行执行或等待任意一个任务的完成。
         *
         * ⑥ 支持并行
         * CompletableFuture 类可以利用多核处理器进行并行计算，提高程序的性能和响应性，从而加速任务的执行和结果的获取。
         * 
         **/
        System.out.println("-------------------------8--------------------");
        // 异步执行任务
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时任务
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "模拟异步任务执行完成!";
        });

        // 处理任务的结果
        future.thenAccept(result -> {
            System.out.println("Task Result: " + result);
        });

        // 主线程等待异步任务完成
        try {
            future.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主任务执行完成!");
        
    }

    /***
     * list转map
     **/
    public static void testListToMap() {
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList.add(new UserInfo(1L, "捡田螺的小男孩", 18));
        userInfoList.add(new UserInfo(2L, "程序员田螺", 27));
        userInfoList.add(new UserInfo(2L, "捡瓶子的小男孩", 26));

        /**
         *  list 转 map - steam流式操作
         *  使用Collectors.toMap的时候，如果有可以重复会报错，所以需要加(k1, k2) -> k1
         *  (k1, k2) -> k1 表示，如果有重复的key,则保留第一个，舍弃第二个
         */
        Map<Long, UserInfo> userInfoMap = userInfoList.stream().collect(Collectors.toMap(UserInfo::getId, userInfo -> userInfo, (k1, k2) -> k1));
        //使用lambda表达式遍历集合
        userInfoMap.values().forEach(a -> System.out.println(a.toString()));
    }
}