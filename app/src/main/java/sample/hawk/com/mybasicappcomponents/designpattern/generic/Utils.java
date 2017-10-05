package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by ha271 on 2017/10/5.
 */

public class Utils {

    public static <T> T[] ListToArray(final List<T> obj) {
        if (obj == null || obj.isEmpty()) {
            return null;
        }
        final T t = obj.get(0);
        final T[] res = (T[]) Array.newInstance(t.getClass(), obj.size());
        for (int i = 0; i < obj.size(); i++) {
            res[i] = obj.get(i);
        }
        return res;
    }
}
