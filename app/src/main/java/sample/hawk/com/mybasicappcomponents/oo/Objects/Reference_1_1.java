package sample.hawk.com.mybasicappcomponents.oo.Objects;

/**
 * Created by ha271 on 2017/1/12.
 */

public class Reference_1_1 implements RefMemLeakActions {
    private Reference_1_1_1 m_r111;

    @Override
    public void MemoryLeak_Next(){
        m_r111 = new Reference_1_1_1();
        m_r111.MemoryLeak_Next();
    }

}
