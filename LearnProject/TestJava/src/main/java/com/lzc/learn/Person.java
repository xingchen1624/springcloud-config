package com.lzc.learn;

@FunctionalInterface
public interface Person {
    /***
     * 必须要有一个抽象方法，且只能有一个
     **/
    abstract void say(String str);
    default void age() {
        System.out.println("age");
    }

    default void address() {
        System.out.println("address");
    }

    static void printHello(){
        System.out.println("Hello");
    }
    static void xttblogHello(){
        System.out.println("Xttblog Hello");
    }

}
