package sample.hawk.com.mybasicappcomponents.designpattern.factory.Abstract;

import sample.hawk.com.mybasicappcomponents.designpattern.factory.Material;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.TeaType;

/**
 * 優點：
 *  1. 抽象工廠模式可以實現高內聚低耦合的設計目的，因此抽象工廠模式被廣泛的應用。
 *  2. 當一個產品族中的多個對象被設計成一起工作時，它能保證客戶端始終使用同一個產品族中的對象。
 *  3. 增加新的具體工廠和產品族很方便，不需要修改已有的系統，符合“開閉原則”。
 *
 * 缺點：
 *  1. 在產品族中擴充功能新的產品是很困難的，它需要修改抽象工廠的介面。
 *  2. 增加新的工廠和產品族容易，增加新的產品等級結構麻煩(開閉原則的傾斜性)。
 */

public class FoodFactory implements IAbstractFactory {
    public Material createProduct(FoodType type){
        Material material = null;
        switch (type) {
            case Bakery:
                material = new Bakery();
                break;
            case Cookie:
                material = new Cookie();
                break;
            default:
                ;
        }
        return material;
    }

    @Override
    public Material createProduct(TeaType type) {
        return null;
    }
}
