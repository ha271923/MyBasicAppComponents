package sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal;

import sample.hawk.com.mybasicappcomponents.designpattern.factory.Material;

/**
 * 優點：
 *  工廠模式創建客戶所需要的產品，也對客戶隱藏了具體產品實做的細節，客戶只需要關心產品對應的工廠，不需要知道創建的細節。
 *  工廠角色和產品角色的多態性設計是工廠模式的關鍵。
 *  工廠模式在系統中增加新產品時，只需要添加一個具體工廠和具體產品即可。而這樣系統的擴展性也變得很好，符合”開閉原則“。
 *
 * 缺點：
 *  系統中的類別數量增加，會增加系統的複雜度，帶給系統額外的開銷。
 */

public abstract class NormalFactory {


    public abstract Material createProduct();

}
