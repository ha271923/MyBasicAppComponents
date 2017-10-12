package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.CallBack1;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.ICallBack1;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.Teacher;
import sample.hawk.com.mybasicappcomponents.designpattern.iterator.Book;
import sample.hawk.com.mybasicappcomponents.designpattern.iterator.BookShelf;
import sample.hawk.com.mybasicappcomponents.designpattern.iterator.MyIterator;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Observer;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Person1;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Person2;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Subject;
import sample.hawk.com.mybasicappcomponents.designpattern.state.LevelConditionMachine;
import sample.hawk.com.mybasicappcomponents.designpattern.state.LevelStateMachine;
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
                Subject subject =  new Subject();
                subject.setTitle("0000");
                Observer observer1 = new Person1();
                Observer observer2 = new Person2();
                subject.setTitle("1111");
                subject.register(observer1);
                subject.register(observer2);
                subject._notifyAll();
                SMLog.i("title= "+ subject.getTitle());
                subject.setTitle("2222");
                subject.unRegister(observer1); //取消觀察者1的註冊
                subject._notifyAll();
                SMLog.i("title= "+ subject.getTitle());
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
                BookShelf bookShelf = new BookShelf(4);
                bookShelf.appendBook(new Book("Around the World in 80 Days"));
                bookShelf.appendBook(new Book("Bible"));
                bookShelf.appendBook(new Book("Cinderella"));
                bookShelf.appendBook(new Book("Daddy-Long-Legs"));
                //我們只有用到Iterator的方法，實際上BookShelf內部怎麼實作的我們不管。
                //如果今天BookShelf把陣列改成vector，下面的程式碼還是不會變動。
                // use my custom iterator
                MyIterator _iterator = bookShelf._iterator();
                while (_iterator.hasNext()) {
                    Book book = (Book)_iterator.next();
                    SMLog.i("MyIterator -> [ " + book.getName()+" ]");
                }
                // use java iterator
                Iterator iterator = bookShelf.iterator();
                while (iterator.hasNext()) {
                    Book book = (Book)iterator.next();
                    SMLog.i("Iterator -> [ " + book.getName()+" ]");
                }

                break;
            case 80: // Mediator

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

                break;
            default:
                SMLog.e("Not support this pattern yet");
        }
    }

}
