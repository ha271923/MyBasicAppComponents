package sample.hawk.com.mybasicappcomponents.designpattern.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ha271 on 2017/10/18.
 */

public class Cars {
    private Map<CarData, Car> prototypes = new HashMap<CarData, Car>(); // get()的關鍵key是CarData物件

    void addPrototype(CarData carData, Car prototype) { // HashMap put
        prototypes.put(carData, prototype);
    }
    Car getPrototype(CarData carData) throws CloneNotSupportedException { // HashMap get+clone
        return (Car) prototypes.get(carData).clone(); // IMPORTANT!! 取回後的物件並不是原始物件, 而是複製過的新物件
    }
}