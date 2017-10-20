package sample.hawk.com.mybasicappcomponents.designpattern.bridge;


import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * RefinedAbstraction (改良後的抽象化) 參與者 (1/2)
 *  對 Abstraction 參與者新增功能的參與者。
 * */
public class ShapeCircle extends Shape {
    private double radius =10.0f;

    public ShapeCircle(IDrawAPI IdrawApi)
    {
        super(IdrawApi);
    }

    @Override
    public void Draw_OverrideInterfaceAPI() // low-level , API from Interface
    {
        IDrawAPI.drawIt(); // IMPORTANT!! 讓實作物件畫圖形
    }

    @Override
    public void Draw_OverrideAbstractAPI() { // high-level , API from Abstract class
        SMLog.i("ShapeCircle::Draw_OverrideAbstractAPI");
    }
}
