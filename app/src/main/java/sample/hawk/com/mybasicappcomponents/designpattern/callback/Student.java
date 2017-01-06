package sample.hawk.com.mybasicappcomponents.designpattern.callback;

/**
 * Created by ha271 on 2017/1/6.
 */

public class Student {
    QuestionListener questionListener; // 身為學身可以註冊發問權

    public void raise_hands() {
        // 當學生舉手時就透過 questionListener 發問
        questionListener.putQuestion();
    }

    public void setQuestionListener(QuestionListener questionListener) {
        this.questionListener = questionListener;
    }
}
