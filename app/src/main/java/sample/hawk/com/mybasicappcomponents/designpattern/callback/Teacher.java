package sample.hawk.com.mybasicappcomponents.designpattern.callback;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/6.
 */

public class Teacher implements QuestionListener { // 身為老師必須實作接收到問題時的行為

    public void onClass() {
        Student a = new Student();
        what_is_this(this);
        a.setQuestionListener(this);
        a.raise_hands(); // 有位學生 a 在課堂上舉手
    }

    @Override
    public void putQuestion() { // 這就是一種 Callback Function
        SMLog.i("Listener got a question");
    }

    private void what_is_this(Teacher teacher){ // just test.
        if( teacher instanceof Teacher)
            SMLog.i("this is Teacher object.");
        if( teacher instanceof QuestionListener)
            SMLog.i("this is an object with QuestionListener I/F.");
    }

}
