package sample.hawk.com.mybasicappcomponents.designpattern.decorator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/23.
 */

class Rectangle implements Shape {

    @Override
    public void draw() {
        SMLog.i("Shape: Rectangle");
    }
}
