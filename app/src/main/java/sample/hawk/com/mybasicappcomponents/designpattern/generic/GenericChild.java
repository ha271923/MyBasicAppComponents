package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * GenericChild<T1, T2, T3>
 *     extends GenericParent<T1, T2>
 *         implements IGenericIF<T1, T2>
 */

// 如果決定要保留型態持有者，則父類別上宣告的型態持有者數目必須齊全，也就是說下式中，T1 與 T2 都要出現，
// 如果不保留型態持有者，則繼承下來的 T1 與 T2 自動變為Object，建議當然是父類別的型態持有者都保留。
public class GenericChild<T1, T2, T3> extends GenericParent<T1, T2> {
    private T3 t3;

    public void setT3(T3 t3) {
        this.t3 = t3;
    }

    public T3 getT3() {
        return t3;
    }

    public void show(){
        SMLog.i("t3="+t3);
    }
}
