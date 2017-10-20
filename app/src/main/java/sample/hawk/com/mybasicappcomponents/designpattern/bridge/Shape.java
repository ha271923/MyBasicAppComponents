package sample.hawk.com.mybasicappcomponents.designpattern.bridge;

/**
 *  Abstraction (抽象化) 參與者
 *    位於 功能的類別階層 最上層的類別，利用 Implementor 的方法只記載基本功能的類別。這個物件個體是保持住 Implementor。例如 Display 類別。
 */

public abstract class Shape {
    protected IDrawAPI IDrawAPI;

    protected Shape(IDrawAPI idrawAPI)
    {
        this.IDrawAPI = idrawAPI;
    }

    public abstract void Draw_OverrideInterfaceAPI(); // low-level
    public abstract void Draw_OverrideAbstractAPI(); // high-level

}
