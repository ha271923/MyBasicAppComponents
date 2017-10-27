package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2;

import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.IAttackMode;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.IItemSet;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.ISpeed;

/**
 * Created by ha271 on 2017/10/27.
 */

public class RoleKnight extends Role {
    private ISpeed mSpeedObj;
    private IItemSet mItemObj;
    private IAttackMode mAtkModeObj;

    public RoleKnight(ISpeed strategy_Speed){
        super();
        this.mSpeedObj = strategy_Speed;
    }

    public RoleKnight(ISpeed strategy_Speed, IItemSet strategy_Item, IAttackMode strategy_AtkMode)
    {
        super();
        this.mSpeedObj = strategy_Speed;
        this.mItemObj = strategy_Item;
        this.mAtkModeObj = strategy_AtkMode;
    }

    public void Speed()
    {
        if(mSpeedObj != null)
            this.mSpeedObj.Speed();
        else
            super.Speed();
    }

    public void UseItem()
    {
        if(mItemObj != null)
            this.mItemObj.UseItem();
        else
            super.UseItem();
    }

    public void Attack()
    {
        if(mAtkModeObj != null)
            this.mAtkModeObj.Attack();
        else
            super.Attack();
    }

    public void setStrategy(ISpeed strategy_Speed, IItemSet strategy_Item, IAttackMode strategy_AtkMode)
    {
        this.mSpeedObj = strategy_Speed;
        this.mItemObj = strategy_Item;
        this.mAtkModeObj = strategy_AtkMode;
    }
}
