package sample.hawk.com.mybasicappcomponents.basic;

/**
 * abstract class
 *   變數: 可以有自己的數據成員，也可以有非abstarct的成員方法
 *   函式: 可以有default function
 *   多型: 繼承關係，一個 class只能使用一次繼承關係
 *   abstract method不可包含private, final, static, native, synchronized等關鍵字 

 * Interface
 *   變數: 只能夠有靜態的不能被修改的數據成員（也就是必須是static final的，不過在interface中一般不定義數據成員）
 *   函式: 無法有default function, 因為所有的成員方法都是abstract的。為了繞過這個限制，必須使用delegate
 *   多型: 一個 class卻可以實現多個interface
 */

public interface MyInterface { // a class MUST implement ALL methods if keyword "implements" added.
    // 所有變數即使沒寫 static final, 但是interface最終還是會強制為 static final field
    static final String TAG = "[MyInterface]";
    static int static_Var=100; // static final will ADD
    int mVar=1000; // static final will ADD
    MyInterface mMyInterface_instance = new MyInterface(){
        @Override
        public void MyInterfaceImpl_InterfaceFunction(int i) {

        }
    };

    void MyInterfaceImpl_InterfaceFunction(int i);

    // void MyInterfaceAPI_Impl(int i){int x;}; // ERROR: interface methods cannot have body.

}
