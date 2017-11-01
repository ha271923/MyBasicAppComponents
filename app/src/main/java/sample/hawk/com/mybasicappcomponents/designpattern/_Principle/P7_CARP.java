package sample.hawk.com.mybasicappcomponents.designpattern._Principle;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * 7. 合成/聚合重覆使用原則 (CARP)(Composite/Aggregate Reuse Principle)
 *    多用合成/聚合，少用繼承。
 *
 *    在兩個物件有 has-a (has-parts、is-part-of)關係時 => 合成/聚合 (A has a B)
 *    當兩個物件有 is-a (is-a-kind-of)關係時 => 繼承 (Superman is a kind of Person)
 *    合成 (Composite)：A、B兩物件有"合成"關係時，表示其中一個物件消失(ex:書本)，另一個物件也會消失(ex:章節)。
 *    聚合 (Aggregate)：A、B兩物件有"聚合"關係時，表示其中一個物件消失(ex:球隊)，另一個物件不會消失(ex:球員)。
 *    合成複用原則就是指在一個新的對象里通過關聯關係（包括組合關係和聚合關係）來使用一些已有的對象，使之成為新對
 *    象的一部分；新對象通過委派調用已有對象的方法達到復用其已有功能的目的。
 *
 */

public class P7_CARP implements IDemo {
    @Override
    public void demo() {

    }
}
