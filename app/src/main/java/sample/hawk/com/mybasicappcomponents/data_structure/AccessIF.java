package sample.hawk.com.mybasicappcomponents.data_structure;

import java.util.ListIterator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/11/8.
 */

public interface AccessIF {

    public void show();

    public void show_by_forloop(); //使用 for-loop 列出所有元素

    public void show_by_foreach(); //使用 for each 列出所有元素

    public void show_by_iterator();  //使用 iterator 列出所有元素

    public void use_case();

}
