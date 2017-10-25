package sample.hawk.com.mybasicappcomponents.designpattern.decorator;

/**
 * Created by ha271 on 2017/10/23.
 */

abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw(){
        decoratedShape.draw();
    }
}
