package sample.hawk.com.mybasicappcomponents.designpattern.factory.Simple;

import java.util.HashMap;

import sample.hawk.com.mybasicappcomponents.designpattern.factory.Cafe;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.GreenTea;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Material;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.MilkTea;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.RedTea;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.TeaType;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 優點：
 *  1. 工廠含必要的邏輯判斷，可以決定在什麼時候創建哪一個產品的實例，客戶端可以免除直接創建產品對象的責任。
 *  2. 可以減少使用者的記憶量。
 *  3. 可以在不修改客戶端資料的情況下，變更或新增具體產品，提高了系統的靈活性。
 *
 * 缺點：
 *  1. 工廠集中所有產品的創建邏輯，一旦無法正常使用，整個系統都受影響。
 *  2. 系統擴展困難，一旦增加新產品就不得不修改工廠邏輯，而在產品多的情況下，可能造成工廠邏輯複雜，不利於系統的擴展與維護。
 */

public class SimpleFactory {

    private static HashMap<Integer, Material> mProductHashMap = new HashMap<>();

    public static Material createProduct(TeaType type) {

        Material material = mProductHashMap.get(type);

        if (material == null) { // only support 4 types of new product
            switch (type) {
                case GreenTea:
                    material = new GreenTea();
                    break;
                case MilkTea:
                    material = new MilkTea();
                    break;
                case RedTea:
                    material = new RedTea();
                    break;
                case Cafe:
                    material = new Cafe();
                    break;
                default:
                    SMLog.i("Not support this type product!!!");
                    break;
            }
            if(material!=null)
                mProductHashMap.put(type.getValue(), material);
        }
        return material;
    }
}