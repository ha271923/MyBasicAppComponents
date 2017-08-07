package sample.hawk.com.mybasicappcomponents.designpattern.state;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/7.
 */

public class ConcreteState030_insert extends LevelStateContext {

    @Override
    public void stateWork(LevelStateMachine state)
    {
        if (state.level < 30)
        {
            SMLog.i("Level=" +  state.level +  "  Normal");
        }
        else
        {
            state.setStateContext(new ConcreteState050());
            state.stateWork();
        }
    }
}
