package sample.hawk.com.mybasicappcomponents.designpattern.state;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/7.
 */

public class ConcreteState001 extends LevelStateContext {

    @Override
    public void stateWork(LevelStateMachine state)
    {
        if (state.level < 20)
        {
            SMLog.i("Level=" +  state.level +  "  Easy");
        }
        else
        {
            // state.setStateContext(new ConcreteState050());
            state.setStateContext(new ConcreteState030_insert());
            state.stateWork();
        }
    }
}
