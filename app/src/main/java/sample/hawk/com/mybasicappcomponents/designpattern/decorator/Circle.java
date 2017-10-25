package sample.hawk.com.mybasicappcomponents.designpattern.decorator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/23.
 */

class Circle implements Shape {

    @Override
    public void draw() {
        SMLog.i("Shape: Circle");
    }
}