package sample.hawk.com.mybasicappcomponents.designpattern.factory;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/6.
 */

public class GreenTea extends Material implements IDrinkAction {
    public GreenTea(){
        this.AddMaterial();
        this.Brew();
        this.DeliverCpu();
        SMLog.i("GreenTea is ready now!");
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
}
