package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Bio;
import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Brian;
import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Head;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 泛型(Java Generic) 提供類似C++的 Template的一部分功能
 */

public class AnyClass<T> {

    private T object;
    public void set(T object) { this.object = object; }
    public T get() { return object; }

    public <T extends Bio> void print(T t){
        SMLog.i(""+t.getClass());
        SMLog.i(""+Class.class);

        t.BioApi();
        if( t instanceof Head)
            ((Head)t).eat("my food");
    }

    public static <T> T createInstance1(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public static <T extends Brian> T createInstance2(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public void show() throws InstantiationException, IllegalAccessException {
        //测试泛型继承自Fruit
        print(new Bio());
        print(new Brian());
        print(new Head());

        //Constructor: 優點不須再透過Type Casting
        Bio     bio1 = createInstance1(Bio.class);
        Brian brian1 = createInstance1(Brian.class);
        Head   head1 = createInstance1(Head.class);

        //测试指定上限通配符
        // Bio     bio2 = createInstance2(Bio.class); // ERROR: Bio class is not extended from Brian.
        Brian brian2 = createInstance2(Brian.class);
        Head   head2 = createInstance2(Head.class);
        brian2.BrianApi();
        head2.head_api();

        // 使用了泛型，再也不需强制型態轉換
        AnyClass<Bio> bio3 = new AnyClass<Bio>();
        AnyClass<Brian> brian3 = new AnyClass<Brian>();
        AnyClass<Head> head3 = new AnyClass<Head>();
        // set
        bio3.set(new Bio());
        brian3.set(new Brian());
        head3.set(new Head());
        // get
        Bio     bio31 = bio3.get(); // 不需强制型態轉換
        Brian brian31 = brian3.get();
        Head   head31 = head3.get();

        // 没有使用泛型，仍需强制型態轉換
        AnyClass anyClass2 = new AnyClass();
        // set
        anyClass2.set(new Bio());
        anyClass2.set(new Brian());
        anyClass2.set(new Head());
        // get
        Bio     bio4 = (Bio) anyClass2.get(); // 需强制型態轉換
        Brian brian4 = (Brian) anyClass2.get();
        Head   head4 = (Head) anyClass2.get();
    }
}
