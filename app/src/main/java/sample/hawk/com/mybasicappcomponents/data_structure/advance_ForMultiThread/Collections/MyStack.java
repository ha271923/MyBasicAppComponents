package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MultiThreadAccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/12.
 */

public class MyStack implements MultiThreadAccessIF {

    public void InitData(Stack<String> stack, int num) {
        for (int i = 0; i < num; i++) {
            stack.add("Reading data["+i+"]rrr");
        }
    }

    public void ThreadsAccessData(Stack<String> stack, int num) {
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(new MyStack.ReadTask(stack));
            executorService.execute(new MyStack.WriteTask(stack, i));
        }
        executorService.shutdown();
    }

    private static class ReadTask implements Runnable {
        Stack<String> stack;
        public ReadTask(Stack<String> stack) {
            this.stack = stack;
        }
        public void run() {
            synchronized (stack) {
                for(String str:stack) {
                    SMLog.i(str);
                }
            }
        }
    }

    private static class WriteTask implements Runnable {
        Stack<String> stack;
        int index;
        public WriteTask(Stack<String> stack, int index) {
            this.stack = stack;
            this.index = index;
        }
        public void run() {
            synchronized (stack) {
                stack.remove(index);
                stack.add(index, "Writing data["+index+"]www");
            }
        }
    }

    @Override
    public void error_case(boolean error) {
        final int data_num = 100;
        try{
            if(error == true){  // BUG: ConcurrentModificationException if Multi-Threading accesses
                // TODO:
            }
            else{  // ANS: use this data structure
                Stack<String> stack = new Stack<String>();
                InitData(stack, data_num);
                ThreadsAccessData(stack, data_num);
            }
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

}
