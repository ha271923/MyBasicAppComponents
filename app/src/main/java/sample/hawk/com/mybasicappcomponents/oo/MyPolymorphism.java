package sample.hawk.com.mybasicappcomponents.oo;

import android.graphics.Color;

import sample.hawk.com.mybasicappcomponents.Polymorphism.Brian;
import sample.hawk.com.mybasicappcomponents.Polymorphism.Head;
import sample.hawk.com.mybasicappcomponents.Polymorphism.IHeadActions;
import sample.hawk.com.mybasicappcomponents.Polymorphism.IIQActions;

/**
 * Created by ha271 on 2016/10/17.
 */

public class MyPolymorphism {

    void test() {
        ParentClass pc = new ParentClass();
        ChildClass cc1 = new ChildClass("cc1");
        ChildClass cc2 = new ChildClass("cc2");
    }

    // Compile time Polymorhism( or Static polymorphism or Method Overloading )
    public void StaticPolymorphism(){
        Head Obj = new Head();    // Head  ref -> Head  obj
        Obj.see("Fruit");
        Obj.see("Fruit", Color.YELLOW);
        Obj.see("Fruit", Color.YELLOW, 5);

    }

    // Runtime Polymorhism( or Dynamic polymorphism ) Trace code by 'CTRL+left-CLICK' may NOT right.
    public void DynamicPolymorphism(){
        Brian obj1 = new Brian(); // Brian ref -> Brian obj
        Brian obj2 = new Head();  // Brian ref -> Head obj
        obj1.think();
        obj2.think();  // call Head.think()  *** If you trace code by 'CTRL+left-CLICK', it will goto Brian.think(), and it's ERROR.

    // A. parent ref point to parent obj
        // obj1.see("Girls");         // ERROR: Brian ref can NOT access entire Head obj. ***
        // ((Head)obj1).see("Girls"); // ERROR: Brian ref CAST to Head ref --> exception.

    // B. parent ref point to child obj
        // obj2.see("Girls");     // ERROR: Brian ref can NOT access entire Head obj. ***
        if( obj2 instanceof Head ){
            ((Head)obj2).see("Girls");// PASS : Brian ref 'CAST to' Head ref --> can access entire Head obj. *** Polymorphism!! ***
        }

    // C. child ref point to child obj
        Head obj3 = new Head();
        if( obj3 instanceof IHeadActions ){ // PASS : Head ref can access entire Head interface included child class. *** Polymorphism!! ***
            obj3.speak();
        }

    // D. interface ref point to class obj
        IHeadActions IheadAction = new Head(); // PASS : IheadAction Interface ref can access entire Head object.
        IheadAction.speak();
        IheadAction.see("Girls");
        IheadAction.eat("SeaFood");
        if( IheadAction instanceof IIQActions ) { // PASS : IheadAction Interface ref can use IIQActions ability.
            ((IIQActions) IheadAction).think();
        }

    // E. interface ref point to interface obj
        // IHeadActions IheadAction = new IHeadActions(); // ERROR: IHeadActions is abstract, cannot be instantiated.
        // IIQActions IiqAction = new IIQActions(); // ERROR: IIQActions is abstract, cannot be instantiated.

    }

    // Method Overriding in Child Class
    public void MethodOverriding(){
        Brian obj1 = new Brian(); // Brian ref -> Brian obj
        Head  obj2 = new Head();  // Head  ref -> Head  obj
        obj1.think();
        obj2.think();             // call Head.think()
        ((Brian)obj2).think();    // WARN: still call Head.think() ***
        obj2.see("Girls");
    }

}
