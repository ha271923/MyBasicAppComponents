package sample.hawk.com.mybasicappcomponents.basic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/26.
 */

public class VariableClass {
    private final String TAG="Hawk";
    private int m_var;
    private int this_var;
    private static int s_var;

    private int m_incA;
    private int m_incB;
    private static int s_incA;
    private static int s_incB;


    private inner[] m_arr;
    private static inner[] s_arr;

    class inner{
        int a,b,c;
    }
    /* 證明 static var是屬於Class專用的mem space, 非動態Object
    * Output:
    *   s_var=1,2,3....10
    *   m_var=0 always
    *   this_var=0 always
    * */
    void VariableTest(){
        s_var = ++s_var;
        m_var = ++m_var;
        this.this_var = ++this.this_var;
        SMLog.i("\n s_var="+s_var+"\n m_var="+m_var+"\n this_var="+this.this_var);
    }


    /*
    * 錯誤使用 ++ 運算元
    * Try to compare A++, ++A.
    * I call this API outside for 10 loop.
    * Output:
    *   m_incA=0   s_incA=0   m_incB=0   s_incB=0
    * */
    void IncreaseTest(){
        m_incA = m_incA++;
        s_incA = s_incA++;
        {   // in the code Brace,
            m_incB = m_incB++;
            s_incB = s_incB++;
        }
        SMLog.i("\n m_incA="+m_incA+
                "\n s_incA="+s_incA+
                "\n m_incB="+m_incB+
                "\n s_incB="+s_incB);
    }

    void ArrayObjTest(){
        m_arr = new inner[1000];
        s_arr = new inner[1000];
        for(int i=0;i<1000;i++){
            m_arr[i] = new inner(); // m_arr[i] is only Ref, it will need to new 1000 objects.
            m_arr[i].a=1;
            m_arr[i].b=2;
            m_arr[i].c=3;
            SMLog.i(TAG,"m_arr["+i+"].a="+ m_arr[i].a);

            s_arr[i] = new inner();
            s_arr[i].a=11;
            s_arr[i].b=22;
            s_arr[i].c=33;
            SMLog.i(TAG,"s_arr["+i+"].a="+ s_arr[i].a);
        }
    }


}
