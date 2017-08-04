package sample.hawk.com.mybasicappcomponents.designpattern.builder;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 *  Director:
 *  統一由指揮者 class 執行生產步驟
 */
public class MealDirector {

    private MealBuilder builder;

    public float getOrder(int order){
        switch(order){
            case 1:
                SMLog.i("SetNo1:");
                builder = new SetNo1();
                break;
            case 2:
                SMLog.i("SetNo2:");
                builder = new SetNo2();
                break;
            case 3:
                SMLog.i("SetNo3:");
                builder = new SetNo3();
                break;
            case 4:
                SMLog.i("SetNo4:");
                builder = new SetNo4();
                break;
            case 123456789:
                SMLog.i("SetAll:");
                builder = new SetAll();
                break;
            default:
                SMLog.i("Not support this order");
                return 0;
        }
        Meal meal = builder.BuildMeal();
        builder.BuildDrink(meal);
        builder.BuildFood(meal);
        builder.ListItems(meal);
        return meal.getCost();
    }
}
