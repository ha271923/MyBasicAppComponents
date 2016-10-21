package sample.hawk.com.mybasicappcomponents.oo;

import android.graphics.Color;

import sample.hawk.com.mybasicappcomponents.Polymorphism.Brian;
import sample.hawk.com.mybasicappcomponents.Polymorphism.Head;
import sample.hawk.com.mybasicappcomponents.Polymorphism.Human;
import sample.hawk.com.mybasicappcomponents.Polymorphism.IHeadActions;
import sample.hawk.com.mybasicappcomponents.Polymorphism.IIQActions;
import sample.hawk.com.mybasicappcomponents.oo.ChildClass;
import sample.hawk.com.mybasicappcomponents.oo.ParentClass;

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

    // Runtime Polymorhism( or Dynamic polymorphism )
    public void DynamicPolymorphism(){
        Brian obj1 = new Brian(); // Brian ref -> Brian obj
        Brian obj2 = new Head();  // Brian ref -> Head obj
        obj1.think();
        obj2.think();  // call Head.think()  ***

        // obj1.see("Girls");         // ERROR: Brian ref can NOT access entire Head obj. ***
        // ((Head)obj1).see("Girls"); // ERROR: Brian ref CAST to Head ref --> exception.

        // obj2.see("Girls");     // ERROR: Brian ref can NOT access entire Head obj. ***
        if( obj2 instanceof Head ){
            ((Head)obj2).see("Girls");// PASS : Brian ref 'CAST to' Head ref --> can access entire Head obj. *** Polymorphism!! ***
        }

        Head obj3 = new Head();
        if( obj3 instanceof IHeadActions ){ // PASS : Head ref can access entire Head interface included child class. *** Polymorphism!! ***
            obj3.speak();
        }

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
