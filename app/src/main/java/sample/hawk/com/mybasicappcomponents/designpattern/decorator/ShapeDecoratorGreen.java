package sample.hawk.com.mybasicappcomponents.designpattern.decorator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/23.
 */

public class ShapeDecoratorGreen extends ShapeDecorator {

    public ShapeDecoratorGreen(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setBorder(decoratedShape);
    }

    private void setBorder(Shape decoratedShape){
        SMLog.i("Border Color: Green");
    }
}