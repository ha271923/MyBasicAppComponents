package sample.hawk.com.mybasicappcomponents.designpattern.factory.Abstract;

import sample.hawk.com.mybasicappcomponents.designpattern.factory.Material;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/3.
 */

public class Cookie extends Material implements ICookAction {

    public Cookie() {
        AddSuger();
        AddFlour();
        Mix();
        Fire();
        TimeElapsed();
        Cool();
        SMLog.i("Cookie is ready now!");
    }

    @Override
    public void Fire() {
        SMLog.i("Fire");
    }

    @Override
    public void TimeElapsed() {
        SMLog.i("TimeElapsed");
    }

    @Override
    public void Mix() {
        SMLog.i("Mix");
    }

    @Override
    public void Cool() {
        SMLog.i("Cool");
    }

    public void AddSuger() {
        SMLog.i("AddSuger");
    }
    public void AddEgg() {
        SMLog.i("AddEgg");
    }
    public void AddMilk() {
        SMLog.i("AddMilk");
    }
    public void AddFlour() {
        SMLog.i("AddFlour");
    }

}
