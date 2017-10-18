package sample.hawk.com.mybasicappcomponents.designpattern.flyweight;

/**
 * Created by ha271 on 2017/10/17.
 */

// 棋子享元抽像物件
abstract class ChessFlyweight
{
    protected String mName; // 共享資料
    public ChessFlyweight(String name)
    {
        this.mName = name;
    }
    public abstract void Display(int x, int y);

}