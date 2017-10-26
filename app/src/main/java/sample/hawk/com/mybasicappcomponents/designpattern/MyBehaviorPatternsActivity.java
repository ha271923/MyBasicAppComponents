package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.CallBack1;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.ICallBack1;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.Teacher;
import sample.hawk.com.mybasicappcomponents.designpattern.iterator.Iterator_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.iterator.compare.Iterator_Pattern2;
import sample.hawk.com.mybasicappcomponents.designpattern.mediator.Mediator_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Observer_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.state.LevelConditionMachine;
import sample.hawk.com.mybasicappcomponents.designpattern.state.LevelStateMachine;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.Visitor_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.compare.Visitor_Pattern2;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/6.
 */

public class MyBehaviorPatternsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydesignpatterns_behavior);

    }

    public void onClick_MyBehaviorPatternsClass(View view){
        MyDesignPattern(Integer.parseInt(view.getTag().toString()));
    }

    private void MyDesignPattern(int pattern_type){
        switch(pattern_type){
            case 10: // callback in caller
                CallBack1 mcb = new CallBack1();
                mcb.register(new ICallBack1() {
                    @Override
                    public void onCall_API() {
                        SMLog.i("***  api()'s callback was defined by caller. ***");
                    }
                });
                mcb.api(); // Call scb's call_api() will callback onCall_API.
                break;
            case 11: // callback in callee
                Teacher teacher = new Teacher(); // 有位老師
                teacher.onClass(); // 這位老師正在上課
                break;
            case 20: // Observer: 定義對象間的一對多的依賴關係，當一個對象狀態發生改變時，所有依賴他的對像都得到通知並被自動更新。
                new Observer_Pattern().demo();
                break;
            case 30: // Interpreter

                break;
            case 40: // Template Method

                break;
            case 50: // Chain of Responsibility

                break;
            case 60: // Command

                break;
            case 70: // Iterator
                new Iterator_Pattern().demo();
                break;
            case 71: // Iterator V.S Composite
                new Iterator_Pattern2().demo();
                break;
            case 80: // Mediator
                new Mediator_Pattern().demo();
                break;
            case 90: // Mememto

                break;
            case 100: // State by OOP 適合複雜情況採用, 且沒比較快
                LevelStateMachine player1 = new LevelStateMachine();
                player1.level = 1;   // STATE變化
                player1.stateWork(); // STATE machine運作
                player1.level = 19;
                player1.stateWork();
                player1.level = 27;
                player1.stateWork();
                player1.level = 62;
                player1.stateWork();
                player1.level = 93;
                player1.stateWork();
                break;
            case 101: // State by if-else 簡單易懂
                LevelConditionMachine player2 = new LevelConditionMachine();
                player2.level = 1;   // STATE變化
                player2.stateWork(); // STATE machine運作
                player2.level = 19;
                player2.stateWork();
                player2.level = 27;
                player2.stateWork();
                player2.level = 62;
                player2.stateWork();
                player2.level = 93;
                player2.stateWork();
                break;
            case 110: // Strategy

                break;
            case 120: // Visitor
                new Visitor_Pattern().demo();
                break;
            case 121:
                new Visitor_Pattern2().demo();
                break;
            default:
                SMLog.e("Not support this pattern yet");
        }
    }



}
