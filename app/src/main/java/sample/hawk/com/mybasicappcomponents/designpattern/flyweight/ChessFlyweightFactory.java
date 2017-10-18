package sample.hawk.com.mybasicappcomponents.designpattern.flyweight;

import java.util.Hashtable;

// 棋子享元工廠，回傳棋子物件
class ChessFlyweightFactory
{
    private Hashtable chessFlyweight = new Hashtable();

    public ChessFlyweight GetChessFlyweight(String key)
    {
        // 這邊的Oject儲存/取得/檢查 是本pattern的關鍵
        if (!chessFlyweight.containsKey(key)) // key只有"黑棋"/"白棋"兩種
        {
            chessFlyweight.put(key, new ConcreteChessFlyweight(key)); // 當"黑棋"/"白棋"從未曾new過時, 才使用new Object創造
        }
        return (ChessFlyweight)chessFlyweight.get(key); // 不用new, 直接取得現有的Object
    }

    // 取得目前棋子物件數量
    public int GetChessFlyweightCount()
    {
        return chessFlyweight.size();
    }
}
