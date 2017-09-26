package sample.hawk.com.mybasicappcomponents.oo.Polymorphism;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/18.
 */

abstract class Animal implements IActions{
    private static String className="Animal";
    private int age;
    private int weight;

    public Animal() {
        this(3, 15);
    }

    public Animal(int w) {
        this(3, w);
    }

    public Animal(int a, int w) {
        setAge(a);
        setWeight(w);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int n) {
        if (n < 0) {
            age = 1;
        }
        else {
            age = n;
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int n) {
        if (n < 0) {
            weight = 1;
        }
        else {
            weight = n;
        }
    }

    public void speak() {
        SMLog.i(className + ": Hello! My age=" + getAge() + "years old , weight=" + getWeight() + "kg");
    }


    public void see(String what){
        SMLog.i(className + ": see " + what);
    }

    public void see(String what, int color){
        SMLog.i(className + ": see " + what + " YELLOW");
    }

    public void see(String what, int color, int size){
        SMLog.i(className + ": see " + what + " YELLOW" + "  size="+size+"kg");
    }



}
