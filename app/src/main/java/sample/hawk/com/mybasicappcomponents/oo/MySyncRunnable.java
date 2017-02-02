package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/*
synchronized keyword 可以確保資料的順序性, 但是代價將是效能的嚴重犧牲

 Test Result:
    (1) mSync = false   TimeCost: 1.032sec ---------------------------------------------------------------------------
 :: run() mSync=false  run +++    name=T[0]
 :: run() mSync=false  run +++    name=T[1]
 :: run() mSync=false  run +++    name=T[4]
 :: run() mSync=false  run +++    name=T[3]
 :: run() mSync=false  run +++    name=T[2]
 :: run() mSync=false  run +++    name=T[5]
 :: run() run        name=T[0]  var=1  static_var=6  CommonResources.static_var=6
 :: run() run        name=T[1]  var=1  static_var=7  CommonResources.static_var=7
 :: run() run        name=T[4]  var=1  static_var=8  CommonResources.static_var=8
 :: run() run        name=T[3]  var=1  static_var=9  CommonResources.static_var=9
 :: run() run        name=T[2]  var=1  static_var=10  CommonResources.static_var=10
 :: run() run        name=T[5]  var=1  static_var=11  CommonResources.static_var=11
 :: run() run        name=T[0]  var=2  static_var=12  CommonResources.static_var=12
 :: run() run        name=T[1]  var=2  static_var=12  CommonResources.static_var=12*
 :: run() run        name=T[4]  var=2  static_var=14  CommonResources.static_var=14
 :: run() run        name=T[3]  var=2  static_var=15  CommonResources.static_var=15
 :: run() run        name=T[2]  var=2  static_var=16  CommonResources.static_var=16
 :: run() run        name=T[5]  var=2  static_var=17  CommonResources.static_var=17
 :: run() run        name=T[0]  var=3  static_var=18  CommonResources.static_var=18
 :: run() run        name=T[1]  var=3  static_var=18  CommonResources.static_var=18*
 :: run() run        name=T[4]  var=3  static_var=20  CommonResources.static_var=20
 :: run() run        name=T[3]  var=3  static_var=21  CommonResources.static_var=21
 :: run() run        name=T[2]  var=3  static_var=22  CommonResources.static_var=22
 :: run() run        name=T[5]  var=3  static_var=23  CommonResources.static_var=23
 :: run() run        name=T[0]  var=4  static_var=24  CommonResources.static_var=24
 :: run() run        name=T[1]  var=4  static_var=24  CommonResources.static_var=24*
 :: run() run        name=T[2]  var=4  static_var=26  CommonResources.static_var=26
 :: run() run        name=T[3]  var=4  static_var=26  CommonResources.static_var=26*
 :: run() run        name=T[4]  var=4  static_var=28  CommonResources.static_var=28
 :: run() run        name=T[5]  var=4  static_var=29  CommonResources.static_var=29
 :: run() run        name=T[1]  var=5  static_var=30  CommonResources.static_var=30
 :: run() run        name=T[0]  var=5  static_var=31  CommonResources.static_var=31
 :: run() run        name=T[2]  var=5  static_var=32  CommonResources.static_var=32
 :: run() run        name=T[3]  var=5  static_var=32  CommonResources.static_var=32*
 :: run() run        name=T[4]  var=5  static_var=34  CommonResources.static_var=34
 :: run() run        name=T[5]  var=5  static_var=35  CommonResources.static_var=35
 :: run() run        name=T[1]  var=6  static_var=36  CommonResources.static_var=36
 :: run() run        name=T[0]  var=6  static_var=37  CommonResources.static_var=37
 :: run() run        name=T[2]  var=6  static_var=38  CommonResources.static_var=38
 :: run() run        name=T[4]  var=6  static_var=38  CommonResources.static_var=38*
 :: run() run        name=T[3]  var=6  static_var=38  CommonResources.static_var=38*
 :: run() run        name=T[5]  var=6  static_var=40  CommonResources.static_var=40
 :: run() run        name=T[1]  var=7  static_var=42  CommonResources.static_var=42
 :: run() run        name=T[0]  var=7  static_var=43  CommonResources.static_var=43
 :: run() run        name=T[3]  var=7  static_var=44  CommonResources.static_var=44
 :: run() run        name=T[2]  var=7  static_var=44  CommonResources.static_var=44*
 :: run() run        name=T[5]  var=7  static_var=45  CommonResources.static_var=45
 :: run() run        name=T[4]  var=7  static_var=45  CommonResources.static_var=45*
 :: run() run        name=T[1]  var=8  static_var=47  CommonResources.static_var=47
 :: run() run        name=T[0]  var=8  static_var=48  CommonResources.static_var=48
 :: run() run        name=T[2]  var=8  static_var=49  CommonResources.static_var=49
 :: run() run        name=T[3]  var=8  static_var=50  CommonResources.static_var=50
 :: run() run        name=T[5]  var=8  static_var=51  CommonResources.static_var=51
 :: run() run        name=T[4]  var=8  static_var=52  CommonResources.static_var=52
 :: run() run        name=T[1]  var=9  static_var=53  CommonResources.static_var=53
 :: run() run        name=T[0]  var=9  static_var=54  CommonResources.static_var=54
 :: run() run        name=T[2]  var=9  static_var=55  CommonResources.static_var=55
 :: run() run        name=T[3]  var=9  static_var=55  CommonResources.static_var=55*
 :: run() run        name=T[5]  var=9  static_var=57  CommonResources.static_var=57
 :: run() run        name=T[4]  var=9  static_var=58  CommonResources.static_var=58
 :: run() run        name=T[1]  var=10  static_var=59  CommonResources.static_var=59
 :: run() run        name=T[0]  var=10  static_var=59  CommonResources.static_var=59*
 :: run()           run    --- name=T[0]
 :: run()           run    --- name=T[1]
 :: run() run        name=T[3]  var=10  static_var=59  CommonResources.static_var=59*
 :: run() run        name=T[2]  var=10  static_var=59  CommonResources.static_var=59*
 :: run()           run    --- name=T[3]
 :: run()           run    --- name=T[2]
 :: run() run        name=T[5]  var=10  static_var=59  CommonResources.static_var=59*
 :: run() run        name=T[4]  var=10  static_var=59  CommonResources.static_var=59*
 :: run()           run    --- name=T[5]
 :: run()           run    --- name=T[4]

    (2) mSync = true   TimeCost: 6.114sec ----------------------------------------------------------------------------
:: run() mSync=true  run +++    name=T[0]
:: run() mSync=true  run +++    name=T[3]
:: run() mSync=true  run +++    name=T[4]
:: run() mSync=true  run +++    name=T[2]
:: run() mSync=true  run +++    name=T[5]
:: run() mSync=true  run +++    name=T[1]
:: run() run        name=T[0]  var=1  static_var=1  CommonResources.static_var=1
:: run() run        name=T[0]  var=2  static_var=2  CommonResources.static_var=2
:: run() run        name=T[0]  var=3  static_var=3  CommonResources.static_var=3
:: run() run        name=T[0]  var=4  static_var=4  CommonResources.static_var=4
:: run() run        name=T[0]  var=5  static_var=5  CommonResources.static_var=5
:: run() run        name=T[0]  var=6  static_var=6  CommonResources.static_var=6
:: run() run        name=T[0]  var=7  static_var=7  CommonResources.static_var=7
:: run() run        name=T[0]  var=8  static_var=8  CommonResources.static_var=8
:: run() run        name=T[0]  var=9  static_var=9  CommonResources.static_var=9
:: run() run        name=T[0]  var=10  static_var=10  CommonResources.static_var=10
:: run()           run    --- name=T[0]
:: run() run        name=T[3]  var=1  static_var=11  CommonResources.static_var=11
:: run() run        name=T[3]  var=2  static_var=12  CommonResources.static_var=12
:: run() run        name=T[3]  var=3  static_var=13  CommonResources.static_var=13
:: run() run        name=T[3]  var=4  static_var=14  CommonResources.static_var=14
:: run() run        name=T[3]  var=5  static_var=15  CommonResources.static_var=15
:: run() run        name=T[3]  var=6  static_var=16  CommonResources.static_var=16
:: run() run        name=T[3]  var=7  static_var=17  CommonResources.static_var=17
:: run() run        name=T[3]  var=8  static_var=18  CommonResources.static_var=18
:: run() run        name=T[3]  var=9  static_var=19  CommonResources.static_var=19
:: run() run        name=T[3]  var=10  static_var=20  CommonResources.static_var=20
:: run()           run    --- name=T[3]
:: run() run        name=T[4]  var=1  static_var=21  CommonResources.static_var=21
:: run() run        name=T[4]  var=2  static_var=22  CommonResources.static_var=22
:: run() run        name=T[4]  var=3  static_var=23  CommonResources.static_var=23
:: run() run        name=T[4]  var=4  static_var=24  CommonResources.static_var=24
:: run() run        name=T[4]  var=5  static_var=25  CommonResources.static_var=25
:: run() run        name=T[4]  var=6  static_var=26  CommonResources.static_var=26
:: run() run        name=T[4]  var=7  static_var=27  CommonResources.static_var=27
:: run() run        name=T[4]  var=8  static_var=28  CommonResources.static_var=28
:: run() run        name=T[4]  var=9  static_var=29  CommonResources.static_var=29
:: run() run        name=T[4]  var=10  static_var=30  CommonResources.static_var=30
:: run()           run    --- name=T[4]
:: run() run        name=T[2]  var=1  static_var=31  CommonResources.static_var=31
:: run() run        name=T[2]  var=2  static_var=32  CommonResources.static_var=32
:: run() run        name=T[2]  var=3  static_var=33  CommonResources.static_var=33
:: run() run        name=T[2]  var=4  static_var=34  CommonResources.static_var=34
:: run() run        name=T[2]  var=5  static_var=35  CommonResources.static_var=35
:: run() run        name=T[2]  var=6  static_var=36  CommonResources.static_var=36
:: run() run        name=T[2]  var=7  static_var=37  CommonResources.static_var=37
:: run() run        name=T[2]  var=8  static_var=38  CommonResources.static_var=38
:: run() run        name=T[2]  var=9  static_var=39  CommonResources.static_var=39
:: run() run        name=T[2]  var=10  static_var=40  CommonResources.static_var=40
:: run()           run    --- name=T[2]
:: run() run        name=T[5]  var=1  static_var=41  CommonResources.static_var=41
:: run() run        name=T[5]  var=2  static_var=42  CommonResources.static_var=42
:: run() run        name=T[5]  var=3  static_var=43  CommonResources.static_var=43
:: run() run        name=T[5]  var=4  static_var=44  CommonResources.static_var=44
:: run() run        name=T[5]  var=5  static_var=45  CommonResources.static_var=45
:: run() run        name=T[5]  var=6  static_var=46  CommonResources.static_var=46
:: run() run        name=T[5]  var=7  static_var=47  CommonResources.static_var=47
:: run() run        name=T[5]  var=8  static_var=48  CommonResources.static_var=48
:: run() run        name=T[5]  var=9  static_var=49  CommonResources.static_var=49
:: run() run        name=T[5]  var=10  static_var=50  CommonResources.static_var=50
:: run()           run    --- name=T[5]
:: run() run        name=T[1]  var=1  static_var=51  CommonResources.static_var=51
:: run() run        name=T[1]  var=2  static_var=52  CommonResources.static_var=52
:: run() run        name=T[1]  var=3  static_var=53  CommonResources.static_var=53
:: run() run        name=T[1]  var=4  static_var=54  CommonResources.static_var=54
:: run() run        name=T[1]  var=5  static_var=55  CommonResources.static_var=55
:: run() run        name=T[1]  var=6  static_var=56  CommonResources.static_var=56
:: run() run        name=T[1]  var=7  static_var=57  CommonResources.static_var=57
:: run() run        name=T[1]  var=8  static_var=58  CommonResources.static_var=58
:: run() run        name=T[1]  var=9  static_var=59  CommonResources.static_var=59
:: run() run        name=T[1]  var=10  static_var=60  CommonResources.static_var=60
:: run()           run    --- name=T[1]

 */

public class MySyncRunnable implements Runnable{
    static String TAG = "[MySyncRunnable]";
    String mThreadName="";
    boolean mSync=false;
    int var=0; // object_var
    static volatile int static_var;

    MySyncRunnable(String name, boolean sync) {
        // mThreadName = Thread.currentThread().getName();
        mSync = sync;
        // SMLog.i(TAG,mThreadName+"constructor");
    }

    public void run() {
        int var=0; // stack_var
        String ThreadName = Thread.currentThread().getName();
        SMLog.i(TAG, "mSync="+mSync+"  run +++    name=" + ThreadName);
        if(mSync) {
            synchronized(this) { // synchronized +++
                for (int i = 0; i < 10; i++) {
                    try {
                        static_var++;var++;CommonResources.static_var++;CommonResources.StaticInnerClass.static_var++;
                        // CommonResources.var++; CommonResources.InnerClass.var++; CommonResources.StaticInnerClass.var++; // ERROR: Non-static field var 'var' cannot be reference from a static context.
                        Thread.sleep(100); // Hawk: simulate context-switch
                        //SMLog.i(TAG, "run        name="+ThreadName+"  var="+ var+"  static_var="+ static_var);
                        SMLog.i(TAG, "run        name="+ThreadName+"  var="+ var+"  static_var="+ static_var+"  CommonResources.static_var="+CommonResources.static_var);
                        //SMLog.i(TAG, "run        name="+ThreadName+"  CommonResources.static_var="+CommonResources.static_var+"  CommonResources.StaticInnerClass.static_var="+CommonResources.StaticInnerClass.static_var);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } // synchronized ---
        }
        else {
            for (int i = 0; i < 10; i++) {
                try {
                    static_var++;var++;CommonResources.static_var++;
                    // CommonResources.var++; // ERROR: Non-static field var 'var' cannot be reference from a static context.
                    Thread.sleep(100); // Hawk: simulate context-switch
                    //SMLog.i(TAG, "run        name="+ThreadName+"  var="+ var+"  static_var="+ static_var);
                    SMLog.i(TAG, "run        name="+ThreadName+"  var="+ var+"  static_var="+ static_var+"  CommonResources.static_var="+CommonResources.static_var);
                    //SMLog.i(TAG, "run        name="+ThreadName+"  CommonResources.static_var="+CommonResources.static_var+"  CommonResources.StaticInnerClass.static_var="+CommonResources.StaticInnerClass.static_var);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        SMLog.i(TAG, "          run    --- name=" + ThreadName);
    }
}
