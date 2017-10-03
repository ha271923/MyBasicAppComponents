package sample.hawk.com.mybasicappcomponents.basic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * abstract class
 *   變數: 可以有自己的數據成員，也可以有非abstarct的成員方法
 *   函式: 可以有default function
 *   多型: 繼承關係，一個 class只能使用一次繼承關係
 *
 * Interface
 *   變數: 只能夠有靜態的不能被修改的數據成員（也就是必須是static final的，不過在interface中一般不定義數據成員）
 *   函式: 無法有default function, 因為所有的成員方法都是abstract的。為了繞過這個限制，必須使用delegate
 *   多型: 一個 class卻可以實現多個interface
 */

public abstract class MyAbstractClass { // JAVA: you can't new a abstract class obj directly.
    private static final String TAG = "[MyAbstractClass]";
    public static int static_Var; // You can access this var without instance
    public int mVar;

    public boolean MyFunctionInAbstrace(int param) {
        SMLog.i(TAG, "MyAbstractClass::MyFunctionInAbstrace = "+ param);
        static_Var = 100;
        mVar = 1000;
        return true;
    }

    public abstract boolean MyAbstractFunction(int p); // A class with ANY abstract method which is abstract class, you also can create a abstract without ANY abstract method.

    // public abstract boolean MyAbstractFunction_Impl(int p){int x;};  // ERROR: abstract methods cannot have body.
}