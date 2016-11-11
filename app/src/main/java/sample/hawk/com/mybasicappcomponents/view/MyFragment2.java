package sample.hawk.com.mybasicappcomponents.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/11/11.
 */

public class MyFragment2 extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.myfragment2, container, false);
        return v;
    }
}
