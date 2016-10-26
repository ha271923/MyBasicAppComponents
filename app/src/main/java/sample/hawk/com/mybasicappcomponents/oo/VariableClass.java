package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/26.
 */

public class VariableClass {
    private final String TAG="Hawk";
    private inner[] ref;
    private inner[] var;

    class inner{
        int a,b,c;
    }

    void ArrayObjTest(){
        ref = new inner[1000];
        var = new inner[1000];
        for(int i=0;i<1000;i++){
            // ERROR!! ref[i]==NULL , since we only have 1000 pointers without 1000 objects.
            // ref[i].a=1;ref[i].b=2;ref[i].c=3;
            // SMLog.i(TAG,"ref["+i+"].a="+ref[i].a);

            // SUCCESS!! var[i]!=NULL , since we have 1000 pointers with 1000 objects.
            var[i] = new inner();
            var[i].a=1;var[i].b=2;var[i].c=3;
            SMLog.i(TAG,"var["+i+"].a="+var[i].a);
        }
    }


}
