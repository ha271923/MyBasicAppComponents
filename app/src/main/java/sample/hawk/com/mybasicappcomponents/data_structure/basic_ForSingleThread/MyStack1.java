package sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Using array to implement Stack
 */

public class MyStack1 implements AccessIF {
    private Object[] data;
    private int top;
    public MyStack1() { // constructor
        data = new Object[1024];
    }
    public void push(Object obj) {
        if (top >= data.length) {
            Object[] tmp = new Object[data.length*2];
            System.arraycopy(data, 0, tmp, 0, data.length);
            data = tmp;
        }
        data[top++] = obj;
    }
    public Object pop() {
        return data[--top];
    }
    public Object peek() {
        return data[top-1];
    }
    public int size() {
        return top;
    }

    @Override
    public void show() {
        show_by_forloop();
        show_by_foreach();
    }

    @Override
    public void show_by_forloop() {
        SMLog.i("show_by_forloop ----");
        if(top > 0){
            for(int i=0; i<top; i++){
                SMLog.i("["+i+"]="+data[i]);
            }
        }
    }

    @Override
    public void show_by_foreach() {
        SMLog.i("show_by_foreach ----");
        if(top > 0){
            for(Object obj : data){
                SMLog.i("["+obj+"]="+obj);
            }
        }

    }

    @Override
    public void show_by_iterator() {

    }

    @Override
    public void use_case() {

    }
}
