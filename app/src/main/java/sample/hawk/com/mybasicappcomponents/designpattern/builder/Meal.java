package sample.hawk.com.mybasicappcomponents.designpattern.builder;


import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/3.
 */

public class Meal {
    private List<IItem> items = new ArrayList<IItem>();

    public void buyItem(IItem item){
        items.add(item);
    }
    public float getCost(){
        float cost = 0;
        for(IItem item: items){
            cost += item.price();
        }
        return cost;
    }

    public void showItem(){
        for(IItem item : items){
            SMLog.i("Item: "+item.name()+ ", Packing["+item.packing().pack()+"], Price="+item.price());
        }
    }
}
