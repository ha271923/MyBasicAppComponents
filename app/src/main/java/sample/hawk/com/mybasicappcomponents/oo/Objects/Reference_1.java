package sample.hawk.com.mybasicappcomponents.oo.Objects;

/**
 * Created by ha271 on 2017/1/12.
 */

public class Reference_1 implements RefMemLeakActions {
    private Reference_1_1 m_r11;
    private Reference_1_2 m_r12;

    @Override
    public void MemoryLeak_Next(){
        m_r11 = new Reference_1_1();
        m_r11.MemoryLeak_Next();

        m_r12 = new Reference_1_2();
        m_r12.MemoryLeak_Next();
    }


}
