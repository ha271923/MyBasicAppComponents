package sample.hawk.com.mybasicappcomponents.Polymorphism;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/18.
 */

public class Human extends Animal {
    private static String className="Animal";
    private Knowledge know;
    public Brian brian;
    public Head head;
    private Body body;
    private Hand Lhand;
    private Hand Rhand;
    private Leg  Lleg;
    private Leg  Rleg;

    public Human(String name, int age, boolean sex) {
        this();
        know.setName(name);
        know.setAge(age);
        know.setSex(sex);
    }

    public Human(String name, int age) {
        this();
        know.setName(name);
        know.setAge(age);
    }

    public Human(Knowledge knowSource) {
        this();
        know = knowSource;
    }

    public Human() {
        know = new Knowledge();
        body = new Body();
        head = new Head();
        Lhand = new Hand("L");
        Rhand = new Hand("R");
        Lleg = new Leg("L");
        Rleg = new Leg("R");
    }


}
