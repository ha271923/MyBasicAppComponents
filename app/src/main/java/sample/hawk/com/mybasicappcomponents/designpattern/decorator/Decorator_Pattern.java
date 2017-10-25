package sample.hawk.com.mybasicappcomponents.designpattern.decorator;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 *
 * 把一個物件當作參數(經由IF)傳給另一個物件裡面進行功能加強(Decorator), 但是使用的技術不是class繼承(硬連接), 而
 * 是decorate該object
 *     1. 加工Class(new 現有物件)
 *     2. 加工Class可以new一個被加工過的現有物件
 *
 * EX1: new RedShapeDecorator(new Circle());
 *    Circle  -- \                                                         | -- ShapeDecoratorRed
 *                -- Shape(I/F) --[decorates Object]-- ShapeDecorator(abstract)                            <-- demo call
 *  Rectangle -- |                                                         \ -- ShapeDecoratorGreen
 *    draw()<=原始     draw()                             draw()                draw() <== 在此加工
 *
 * EX2: new CoffeeDecorator(new SimpleCoffee());
 *                                                                            | -- WithMilk
 *   SimpleCoffee -- Coffee(I/F) --[decorates Object]-- CoffeeDecorator (abstract)                      <-- demo
 *                                                                            \ -- WithSprinkles
 *    getCost()<=原始  getCost()                           getCost()                getCost() <== 在此加工
 *
 * EX3: new WindowDecorator(new SimpleWindow());
 *                                                                            | -- VerticalScrollBarDecorator
 *   SimpleWindow -- Window(I/F) --[decorates Object]-- WindowDecorator (abstract)                                     <-- demo
 *                                                                            \ -- HorizontalScrollBarDecorator
 *    draw()<=原始       draw()                           draw()                   draw() <== 在此加工
 *
 */

public class Decorator_Pattern implements IDemo {
    @Override
    public void demo() {
        Shape circle = new Circle();

        Shape redCircle = new ShapeDecoratorRed(new Circle());

        Shape redRectangle = new ShapeDecoratorRed(new Rectangle());
        SMLog.i("Circle with normal border");
        circle.draw(); // call 原始circle.draw() directly

        SMLog.i("Circle of red border");
        redCircle.draw(); // call redCircle.draw() --> ShapeDecoratorRed.draw()[decorates it] --> Circle.draw()加工過

        SMLog.i("Rectangle of red border");
        redRectangle.draw(); // call redRectangle.draw() --> ShapeDecoratorRed.draw()[decorates it] --> Circle.draw()加工過
    }
}
