package sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/11.
 */

public class MyWeakHashMap  implements AccessIF {
    WeakHashMap<Integer, String> m_hashmap;

    public MyWeakHashMap(){
        m_hashmap = new WeakHashMap<>();
        m_hashmap.put(1,"Value1");
        m_hashmap.put(2,"Value2");
        m_hashmap.put(3,"Value3");
        m_hashmap.put(4,"Value4");
        m_hashmap.put(5,"Value5");
        m_hashmap.put(6,"Value6");
        m_hashmap.put(7,"Value7");
        m_hashmap.put(8,"Value8");
        m_hashmap.put(9,"Value9");
        m_hashmap.put(10,"Value10");
    }

    @Override
    public void show_by_forloop(){ //使用 for-loop 列出所有元素
        //Iterator iter;
        //iter = m_hashmap.entrySet().iterator(); // Obtaining an iterator for the entry set
        SMLog.i("show_by_forloop ----");
        if(!m_hashmap.isEmpty()){
            for(int i=1;i<(m_hashmap.size()+1);i++){
                //m_hashmap.get(3);
                //Map.Entry me = (Map.Entry)iter.next();
                SMLog.i("["+i+"]="+m_hashmap.get(i));
            }
        }
    }
    @Override
    public void show_by_foreach(){ //使用 for each 列出所有元素
        SMLog.i("show_by_foreach ----");
        if(!m_hashmap.isEmpty()){
            for(Map.Entry<Integer, String> me : m_hashmap.entrySet()){
                SMLog.i("["+me.getKey()+"]="+me.getValue());
            }
        }
    }
    @Override
    public void show_by_iterator(){  //使用 iterator 列出所有元素
        Set entrySet;
        Iterator iter;
        entrySet = m_hashmap.entrySet(); // Getting a Set of Key-value pairs
        iter = entrySet.iterator(); // Obtaining an iterator for the entry set

        SMLog.i("show_by_iterator ----");
        if(!m_hashmap.isEmpty()){
            while(iter.hasNext()){
                Map.Entry me = (Map.Entry)iter.next();
                SMLog.i("["+me.getKey()+"]="+me.getValue());
            }
        }
    }
    @Override
    public void show() {
        show_by_forloop();
        show_by_foreach();
        show_by_iterator();
    }
    @Override
    public void use_case(){
        Map<String, Integer> passwords = new WeakHashMap<>();
        passwords.put("Justin", 123456);
        passwords.put("caterpillar", 93933);

        SMLog.i("Justin's Password= "+passwords.get("Justin")); // 123456

        passwords.put("Hamimi", 970221);  // 增加一對鍵值
        SMLog.i("Hamimi's Password= "+passwords);  // {Justin=123456, caterpillar=93933, Hamimi=970221}

        passwords.remove("caterpillar");  // 刪除一對鍵值
        SMLog.i("caterpillar's Password= "+passwords);  // {Justin=123456, Hamimi=970221}

        SMLog.i("Password.entrySet= "+passwords.entrySet()); // [Justin=123456, Hamimi=970221]
        SMLog.i("Password.keySet= "+passwords.keySet());   // [Justin, Hamimi]
        SMLog.i("Password.values= "+passwords.values());   // [123456, 970221]
    }


    /*
    * Hawk:
    *   一次跑一個TEST case就好, 執行期間觀察memory monitor的圖表
    */
    public void test() {
        // TEST1: Compare the memory usage between WeakHashMap and HashMap allocated.
        // waste_memory(leak_map);  // 100MB
        // waste_memory(leak_wmap); // 3MB

        // TEST2: Compare the memory usage in WeakHashMap runtime behavior
        // behavior_and_memory_usage(true);   // no mem leakage if with get() API called
        // behavior_and_memory_usage(false);  // mem leakage, Out-Of-Memory Exception!

        // TEST3:
        refence_and_object();
    }

    HashMap<byte[][], byte[][]>     leak_map  = new HashMap<>();
    WeakHashMap<byte[][], byte[][]> leak_wmap = new WeakHashMap<>();

    /*
      Output Result:
        HashMap before Garbage Collected :     {hashmapkey=hashmapvalue}
        WeakHashMap before Garbage Collected : {weakhashmapkey=weakhashmapvalue}
        GC()
        HashMap after Garbage Collected :      {hashmapkey=hashmapvalue}
        WeakHashMap after Garbage Collected :  {}                     <-- DANGEROUS!!!
    */
    private void waste_memory(WeakHashMap<byte[][], byte[][]> wmap){
        byte[][] key;
        byte[][] value;
        for(int i = 0;i<100;i++) { // 100MB
            key = new byte[1024][1024];
            value = new byte[1024][1024];
            wmap.put(key, value); // 1MB,1MB
        }
        SMLog.i("+++ wmap waste_memory size="+wmap.size());
        System.gc();
        SMLog.i("--- wmap waste_memory size="+wmap.size());
    }

    private void waste_memory(HashMap<byte[][], byte[][]> map){
        byte[][] key;
        byte[][] value;
        for(int i = 0;i<100;i++) { // 100MB
            key = new byte[1024][1024];
            value = new byte[1024][1024];
            map.put(key, value); // 1MB,1MB
        }
        SMLog.i("+++ map waste_memory size="+map.size());
        System.gc();
        SMLog.i("--- map waste_memory size="+map.size());
    }


    static WeakHashMap<byte[][],byte[][]> temp;

    // 由於Java默認內存是64M，所以在不改變內存參數的情況下,bAccess=false代表從未存取過,記憶體將不釋放,會發生Out-Of-Memory
    public void behavior_and_memory_usage(boolean bAccess) {
        SMLog.i("behavior_and_memory_usage("+bAccess+")  +++++++++");
        List<WeakHashMap<byte[][],byte[][]>> list_maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
        for(int i = 0;i<1024;i++){
            WeakHashMap<byte[][], byte[][]> wmap = new WeakHashMap<byte[][],byte[][]>();
            try{
                wmap.put(new byte[1024][1024], new byte[1024][1024]); // 1MB
            }
            catch (OutOfMemoryError  e){
                e.printStackTrace();
            }
            list_maps.add(wmap);
            System.gc();
            SMLog.i("["+i+"]");
            if(bAccess == true){ // 若在進行GC 前, 對WeakHashMap進行一次read/write,該wmap memory將在GC時被釋放
                for(int j= 0;j<i;j++) {
                    SMLog.i("<"+j+">   size="+list_maps.get(j).size());
                }
            }
        }
        SMLog.i("behavior_and_memory_usage("+bAccess+")  ---------     size()="+list_maps.size());
    }

    /*
    *  map  --> 'aKey' --> map.remove('aKey')
    *  wmap --> 'aKey'                      -\-> wmap('aKey') might NOT existing.
    *
    */
    public void refence_and_object() {
        String aKey = new String("aKey");
        String bKey = new String("bKey");
        Map map = new HashMap();
        Map wmap = new WeakHashMap();
        map.put(aKey, "aValue");
        map.put(bKey, "bValue");
        wmap.put(aKey,"aValue"); // store into cache
        wmap.put(bKey,"bValue");

        map.remove(aKey); // <-- Although wmap.remove(a) did NOT called ever, wmap.('a') KEY might lose.

        waste_memory(leak_map);
        waste_memory(leak_wmap);

        aKey=null;
        bKey=null;
        System.gc();

        Iterator i = map.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry en = (Map.Entry)i.next();
            SMLog.i("map:" +en.getKey()+ ":" +en.getValue());
        }

        Iterator j = wmap.entrySet().iterator(); // get from cache
        while(j.hasNext()){
            Map.Entry en = (Map.Entry)j.next();
            SMLog.i("wmap:" +en.getKey()+ ":" +en.getValue());  // Hawk: we MIGHT lose 'a' KEY cache data sometimes if Iterator can't found it.
        }

        SMLog.i(" map ALL= "+ map);
        SMLog.i("wmap ALL= "+ wmap);
    }

}

