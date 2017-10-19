package sample.hawk.com.mybasicappcomponents.designpattern.prototype;

/**
 * Created by ha271 on 2017/10/18.
 */

public class Car implements Cloneable {
    // 也許還有一些複雜的設定
    private Wheel[] wheels = {new Wheel(), new Wheel(), new Wheel(), new Wheel()};
    private CarData mCarData = new CarData();

    // 關鍵在這裡!!! 如何把物件clone產生
    protected Object clone() throws CloneNotSupportedException {
        Car copy = (Car) super.clone(); // 複製汽車
        copy.wheels = (Wheel[]) this.wheels.clone(); // 複製輪胎[]
        for (int i = 0; i < this.wheels.length; i++) {
            copy.wheels[i] = (Wheel) this.wheels[i].clone(); // 複製輪胎物件
        }
        return copy;
    }
    // 也許還有別的方法
    public CarData getCarData(){
        return mCarData;
    }

    // public void setCarData(CarData carData){
    //     mCarData = carData;
    // }
}
