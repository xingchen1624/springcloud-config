package com.lzc.test;

/***
 * 函数式接口
 * 只包含一个抽象方法：函数式接口只能包含一个抽象方法，但可以包含多个默认方法或静态方法。
 * Lambda表达式实例化：函数式接口可以通过Lambda表达式来实例化，从而实现函数作为参数传递、函数式编程等功能。
 * 简化代码：函数式接口使得代码更加简洁和可读，特别是在处理回调函数、事件处理、集合操作等场景下。
 * 
 **/
@FunctionalInterface
public interface TestInterface {
    void myName(String name);
}
