package sample.hawk.com.mybasicappcomponents.designpattern.state;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/7.
 */

public class ConcreteState090 extends LevelStateContext {

    @Override
    public void stateWork(LevelStateMachine state)
    {
        if (state.level < 90)
        {
            SMLog.i("Level=" +  state.level +  "  Hard");
        }
        else
        {
            state.setStateContext(new ConcreteStateMAX());
            state.stateWork();
        }
    }
}
