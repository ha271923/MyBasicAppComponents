package sample.hawk.com.mybasicappcomponents.designpattern.facade;

import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Head;
import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Body;
import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Hand;
import sample.hawk.com.mybasicappcomponents.basic.Polymorphism.Leg;

/**
 * Created by ha271 on 2017/10/23.
 */

public class HumanFacade {
    private Head  mHead;
    private Body  mBody;
    private Hand  mLHand, mRHand;
    private Leg   mLLeg, mRLeg;

    public HumanFacade() {
        mHead  = new Head();
        mBody  = new Body();
        mLHand = new Hand("Left");
        mRHand = new Hand("Right");
        mLLeg  = new Leg("Left");
        mRLeg  = new Leg("Right");
    }

    public void MoveForward(){
        mHead.head_api();
        mBody.BodyApi();
        mLHand.HandApi();
        mRHand.HandApi();
        mLLeg.walk(100);
        mRLeg.walk(-100);
    }

    public void JumpUp(){
        mHead.head_api();
        mBody.BodyApi();
        mLHand.HandApi();
        mRHand.HandApi();
        mLLeg.jump(30);
        mRLeg.jump(20);
    }
}
