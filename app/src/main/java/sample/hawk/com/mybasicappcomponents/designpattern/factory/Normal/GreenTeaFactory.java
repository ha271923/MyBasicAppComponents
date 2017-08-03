package sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal;

import sample.hawk.com.mybasicappcomponents.designpattern.factory.GreenTea;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Material;

/**
 * Created by ha271 on 2017/8/3.
 */

public class GreenTeaFactory extends NormalFactory {
    @Override
    public Material createProduct() {
        return new GreenTea();
    }
}
