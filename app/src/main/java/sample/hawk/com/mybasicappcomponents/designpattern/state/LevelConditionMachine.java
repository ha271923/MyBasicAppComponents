package sample.hawk.com.mybasicappcomponents.designpattern.state;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/7.
 */

public class LevelConditionMachine {
    public int level = 1; // 等級
    // 狀態處理，一般用 if else 的寫法
    public void stateWork()
    {
        if (level >= 1 && level < 20)
        {
            SMLog.i("Level=" +  level +  "  Easy");
        }
        else if (level >= 20 && level < 30)
        {
            SMLog.i("Level=" +  level +  "  Normal"); // insert a state
        }
        // else if (level >= 20 && level < 50)
        else if (level >= 30 && level < 50)
        {
            SMLog.i("Level=" +  level +  "  Medium");
        }
        else if (level >= 50 && level < 90)
        {
            SMLog.i("Level=" +  level +  "  Hard");
        }
        else if (level >= 90)
        {
            SMLog.i("Level=" +  level +  "  Nightmare");
        }
    }
}
