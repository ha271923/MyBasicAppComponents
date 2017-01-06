package sample.hawk.com.mybasicappcomponents.designpattern.factory;

import java.util.HashMap;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/6.
 */

public class ProductFactory {

    public enum Tea{
        GreenTea(0),
        BlackTea(1),
        MilkTea(2),
        RedTea(3),
        Cafe(4);

        private final int value;

        private Tea(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private static HashMap<Integer, Material> mProductHashMap = new HashMap<>();

    public static Material createProduct(Tea type) {

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