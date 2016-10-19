package sample.hawk.com.mybasicappcomponents.Polymorphism;

/**
 * Created by ha271 on 2016/10/18.
 */

public class Knowledge {
    private String name;
    private int age;
    private boolean sex;

    public String getName() {
        return name;
    }

    public void setName(String n) {
        if (n == null || n.equals("")) {
            name = n;
        }
        else {
            name = n;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int num) {
        age = num;
    }

    public boolean setSex() {
        return sex;
    }

    public void setSex(boolean b) {
        sex = b;
    }


}
