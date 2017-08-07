package sample.hawk.com.mybasicappcomponents.designpattern.state;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/7.
 */

public class ConcreteStateMAX extends LevelStateContext {

    @Override
    public void stateWork(LevelStateMachine state)
    {
        if (state.level >= 90)
        {
            SMLog.i("Level=" +  state.level +  "  Nightmare");
        }
        else
        {
            SMLog.i("Not support!");
        }
    }
}
