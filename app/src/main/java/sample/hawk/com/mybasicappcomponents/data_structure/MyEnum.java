package sample.hawk.com.mybasicappcomponents.data_structure;

import android.graphics.Color;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/11/16.
 */

public class MyEnum {

    public enum MyEnumList{
        WHITE, GREEN, YELLOW, RED, BLUE, INDIGO, PURPLE, BLACK
    }

    public MyEnum(){
        SMLog.i("MyEnum has been created!");
    }


    public int switch_case(MyEnumList value){
        int color;
        switch(value){
            case WHITE:
                color=Color.WHITE;
                break;
            case GREEN:
                color=Color.GREEN;
                break;
            case YELLOW:
                color=Color.YELLOW;
                break;
            case RED:
                color=Color.RED;
                break;
            case BLUE:
                color=Color.BLUE;
                break;
            case INDIGO:
                color=Color.BLUE|Color.BLACK;
                break;
            case PURPLE:
                color=Color.BLUE|Color.RED;
                break;
            case BLACK:
                color=Color.BLACK;
                break;
            default:
                color=Color.WHITE;
        }

        return color;
    }


}
