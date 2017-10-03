package sample.hawk.com.mybasicappcomponents.basic;

/**
 * Created by ha271 on 2016/10/20.
 */

// Java with multiple inheritance
public class Multi_Inheritance {

    class Father_1 extends ParentClass{
        public int strong(){
            return super.strong() + 1;
        }
    }

    class Mother_1 extends ParentClass2{
        public int kind(){
            return super.inteligent() - 2;
        }
    }

    public int getStrong(){
        return new Father_1().strong();
    }

    public int getKind(){
        return new Mother_1().kind();
    }
}
