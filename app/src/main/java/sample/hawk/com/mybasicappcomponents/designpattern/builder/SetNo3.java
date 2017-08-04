package sample.hawk.com.mybasicappcomponents.designpattern.builder;

/**
 * Created by ha271 on 2017/8/4.
 */

public class SetNo3 implements MealBuilder {
    Meal mMeal;

    @Override
    public Meal BuildMeal() {
        mMeal = new Meal();
        return mMeal;
    }

    @Override
    public void BuildDrink(Meal meal) {
        mMeal.buyItem(new ChickenBurger());
    }

    @Override
    public void BuildFood(Meal meal) {
        mMeal.buyItem(new Coke());
    }

    @Override
    public void ListItems(Meal meal) {
        mMeal.showItem();
    }
}
