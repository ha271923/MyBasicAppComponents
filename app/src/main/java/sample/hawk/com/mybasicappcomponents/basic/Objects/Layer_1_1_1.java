package sample.hawk.com.mybasicappcomponents.basic.Objects;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/12.
 */

public class Layer_1_1_1  extends Layer_1_1{

    Layer_1_1_1(){
        SMLog.i("");
    }

    public void Layer_1_1_1_API(){
        SMLog.i("");
        Layer_1_API();   // Lowest Layer can call ALL public APIs under Layers.
        Layer_1_1_API();
    }
}
