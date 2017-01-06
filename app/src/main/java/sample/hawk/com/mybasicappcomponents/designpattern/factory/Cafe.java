package sample.hawk.com.mybasicappcomponents.designpattern.factory;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/6.
 */

public class Cafe extends  Material implements IDrinkAction {
    public Cafe(){
        this.AddMaterial();
        this.AddSuger();
        this.AddMilk();
        this.Brew();
        this.DeliverCpu();
        SMLog.i("Cafe is ready now!");
    }

    @Override
    public void AddMaterial() {
        SMLog.i("AddMaterial");
    }

    @Override
    public void Brew() {
        SMLog.i("Brew");
    }

    @Override
    public void DeliverCpu() {
        SMLog.i("DeliverCpu");
    }

    public void AddSuger() {
        SMLog.i("AddSuger");
    }

    public void AddMilk() {
        SMLog.i("AddMilk");
    }

}
