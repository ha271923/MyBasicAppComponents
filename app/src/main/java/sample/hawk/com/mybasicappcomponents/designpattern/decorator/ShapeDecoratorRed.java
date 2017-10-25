package sample.hawk.com.mybasicappcomponents.designpattern.decorator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/23.
 */

public class ShapeDecoratorRed extends ShapeDecorator {

    public ShapeDecoratorRed(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setBorder(decoratedShape);
    }

    private void setBorder(Shape decoratedShape){
        SMLog.i("Border Color: Red");
    }
}