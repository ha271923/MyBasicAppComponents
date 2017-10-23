package sample.hawk.com.mybasicappcomponents.designpattern.facade;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * Facade(門面) == Wrapper??
 *
 * Facade常用在兩個地方:
 * 第一種用法是將原來程序導向的系統，利用Facade包成OO，如MFC包裝Win32 API
 * 第二種用法是將原來複雜的OO系統，再包成更簡單的OO，如.NET Enterprise Library將.NET Framework再做一次包裝，讓你更容易使用.NET。
 * 另外3-tier也是典型Facade的應用，DAL(Data Access Layer)就是一個Facade，讓BLL(Business Logic Layer)在存取資料時，不用對付ADO.NET複雜的機制，只需面對DAL即可。
 *
 * EX:
 * 如我們坐捷運買票，目前的賣票的機器只能吃銅板，所以得另外的提供換鈔機，讓旅客將紙幣換成銅板，也就是說，若一個
 * 旅客身上只有紙幣又要賣捷運票，他必須做兩個步驟，先將紙幣換成銅板，再用銅板去買車票。但對於旅客來說，似乎太嫌麻
 * 煩，買個票要排兩次隊，若我們能設計一部機器，能吃紙鈔，且能購票，那對旅客將非常的方便。事實上，我們並不需重新設
 * 計這樣的機器，只要將這兩台機器整合成一台即可。
 *
 * 比較:
 *   1. Facade 很多時候是 1:m 的關係
 *   2. Adapter很多是候是 1:1 的關係
 *   3. Bridge 很多時候是 m:n 的關係
 *
 */

public class Facade_Pattern implements IDemo {
    @Override
    public void demo() {
        HumanFacade human = new HumanFacade();
        human.MoveForward();
        human.JumpUp();
    }
}
