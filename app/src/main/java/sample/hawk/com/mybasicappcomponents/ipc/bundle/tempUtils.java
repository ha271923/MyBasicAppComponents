package sample.hawk.com.mybasicappcomponents.ipc.bundle;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class tempUtils {

    static public String calcWeight(String sex, double height) {
        String weight="";
        if(sex.equals("M")){
            weight=format((height-80)*0.7);
        }else{
            weight=format((height-70)*0.6);
        }
        return weight;
    }

    //四捨五入用
    static public String format(double num) {
        NumberFormat formatter = new DecimalFormat("0.00");
        String s = formatter.format(num);
        return s;
    }
}
