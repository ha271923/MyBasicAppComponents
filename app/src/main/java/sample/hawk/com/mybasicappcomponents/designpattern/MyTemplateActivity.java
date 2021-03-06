package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Hand;
import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Head;
import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Leg;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.AnyArray;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.AnyClass;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.AnyType1;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.AnyType2;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.GenericChild;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.GenericParent;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.LimitedType;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.RecursiveAnyType;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.UnknownType;
import sample.hawk.com.mybasicappcomponents.designpattern.generic.Utils;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Q: 何時該使用Generic? 原本Object不就可以了麼?
 * A: 使用Object透過轉型, 一旦發生錯誤是 run time error,
 *    使用 generic 則會得到 compile time error,
 *    compile time error 比 run time error 要好
 *
 * 也就是說, Generic Type其實就是一種在Compiler time就把該位置所需的Type字串, 給置換好的靜態技術!
 **/

public class MyTemplateActivity extends Activity {
    AnyType1 anyType1;
    AnyType2 anyType2;
    RecursiveAnyType recursiveAnyType;

    enum Elements {
        E0,E1,E2,E3
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydesignpatterns_template);

    }

    public void onClick_MyGenericPatternsClass(View view){
        MyGenericPattern(Integer.parseInt(view.getTag().toString()));
    }

    private void MyGenericPattern(int pattern_type){
        switch(pattern_type){
            case 0:
                AnyArryTest();
                break;
            case 10: // Basic Generic
                AnyTypeTest();
                break;
            case 11: // Limited Generic
                LimitedTypeTest();
                break;
            case 12: // Unknown Generic
                UnknownTypeTest();
                break;
            case 13: // Generic Class
                AnyClassTest();
                break;
            case 14:
                UtilTests(1);
                break;
            case 15:
                GenericLayerTest();
                break;
            default:
                SMLog.i("Not support yet!");
        }
    }

    public void AnyTypeTest(){
        anyType1 = new AnyType1(1);
        anyType1.setValue(1);
        anyType1.setValue("str1");
        anyType1.setValue(Elements.E1);
        anyType1.setValue(new AnyType1("new Obj"));
        anyType1.show();

        anyType2 = new AnyType2(21,22);
        anyType2.setValue1(21);
        anyType2.setValue1("str21");
        anyType2.setValue1(Elements.E1);
        anyType2.setValue1(new AnyType2("new Param11a","new Param11b"));
        anyType2.setValue2(22);
        anyType2.setValue2("str22");
        anyType2.setValue2(Elements.E2);
        anyType2.setValue2(new AnyType2("new Param22a","new Param22b"));
        anyType2.show();

        recursiveAnyType = new RecursiveAnyType(1);
        recursiveAnyType.AddItem(1);
        recursiveAnyType.AddItem("str1");
        recursiveAnyType.AddItem(Elements.E1);
        recursiveAnyType.AddItem(new RecursiveAnyType("new Param1"));
        recursiveAnyType.show();
    }

    public void LimitedTypeTest() {
        // Generic Type can store any type in mList[].
        LinkedList linkedList = new LinkedList();
        linkedList.add(0);
        linkedList.add("str1");
        linkedList.add(Elements.E1);
        linkedList.add(new RecursiveAnyType("new Param1"));
        LimitedType<LinkedList> limitedTypeLinkedList = new LimitedType<>(linkedList);
        // limitedTypeLinkedList.setList(1);  // ERROR: T is not extended from List interface.
        limitedTypeLinkedList.setList(linkedList);
        limitedTypeLinkedList.show();
        // Generic Type can store any type in mList[].
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        arrayList.add("str1");
        arrayList.add(Elements.E1);
        arrayList.add(new RecursiveAnyType("new Param1"));
        LimitedType<ArrayList> limitedTypeArrayList = new LimitedType<ArrayList>(arrayList);
        limitedTypeArrayList.setList(arrayList);
        limitedTypeArrayList.show();

    }

    public void UnknownTypeTest() {
        UnknownType unknownType = new UnknownType();
        unknownType.show();
    }

    public void AnyArryTest() {
        AnyArray anyArray = new AnyArray();

        String[] sArr = new String[4];
        sArr[0] = new String("str0");
        sArr[1] = new String("str1");
        sArr[2] = new String("str2");
        sArr[3] = new String("str3");
        anyArray.setArray(sArr);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(0,"str0");
        arrayList.add(1,"str1");
        arrayList.add(2,"str2");
        arrayList.add(3,"str3");
        anyArray.setArrayList(arrayList);

        anyArray.show();
    }

    public void UtilTests(int testCase) {
        AnyArray anyArray = new AnyArray();
        String[] sArr = new String[4];
        ArrayList<String> listStrArr = new ArrayList<String>();
        listStrArr.add(0,"str0");
        listStrArr.add(1,"str1");
        listStrArr.add(2,"str2");
        listStrArr.add(3,"str3");
        switch(testCase){
            case 1:
                sArr = Utils.ListToArray(listStrArr);
                SMLog.i("sArr[0]="+sArr[0]);
                SMLog.i("sArr[1]="+sArr[1]);
                SMLog.i("sArr[2]="+sArr[2]);
                SMLog.i("sArr[3]="+sArr[3]);
                break;
        }
    }

    public void AnyClassTest() {
        AnyClass<String> anyClass = new AnyClass<String>();
        try {
            anyClass.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void GenericLayerTest() {
        GenericChild genericChild = new GenericChild();
        genericChild.setT1(new Head());
        genericChild.setT2(new Hand("Hand T2"));
        genericChild.setT3(new Leg("Leg T3"));
        genericChild.show(); // T3
        ((GenericParent) genericChild).show(); // It's still T3 even casting did.
    }
}
