package sample.hawk.com.mybasicappcomponents.designpattern.builder;


import java.util.ArrayList;
import java.util.List;

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
            System.out.print("Item: " + item.name());
            System.out.print(", Packing:" + item.packing().pack());
            System.out.println(", Price: " + item.price());
        }
    }
}
