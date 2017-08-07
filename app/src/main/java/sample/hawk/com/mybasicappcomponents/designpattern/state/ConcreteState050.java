package sample.hawk.com.mybasicappcomponents.designpattern.state;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/7.
 */

public class ConcreteState050 extends LevelStateContext {

    @Override
    public void stateWork(LevelStateMachine state)
    {
        if (state.level < 50)
        {
            SMLog.i("Level=" +  state.level +  "  Medium");
        }
        else
        {
            state.setStateContext(new ConcreteState090());
            state.stateWork();
        }
    }
}
