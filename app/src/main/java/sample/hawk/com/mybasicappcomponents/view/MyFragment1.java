package sample.hawk.com.mybasicappcomponents.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/11/11.
 */

public class MyFragment1 extends Fragment{
    String   mState;
    TextView mStatus;

    @Override   // Fragment only
    public void onAttach(Context context) {
        super.onAttach(context);SMLog.i();
        mState +="(C0)onAttach->";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);SMLog.i();
        mState +="(C1)onCreate->";
    }

    @Override   // Fragment only
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.myfragment1, container, false);
        mStatus = (TextView) v.findViewById(R.id.MyFragment1_subtitle);
        mState +="(C2/D4-1)onCreateView->"; mStatus.setText(mState);
        return v;
    }

    @Override   // Fragment only
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);SMLog.i();
        mState +="(C3)onActivityCreated->"; mStatus.setText(mState);
    }

    @Override
    public void onStart() {
        super.onStart();SMLog.i();
        mState +="(C4/D3-1)onStart->"; mStatus.setText(mState);
    }

    @Override
    public void onResume() {
        super.onResume();SMLog.i();
        mState +="(C5)onResume->"; mStatus.setText(mState);
    }

    @Override
    public void onPause() {
        super.onPause();SMLog.i();
        mState +="(D1)onPause->"; mStatus.setText(mState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);SMLog.i();
    }


    @Override
    public void onStop() {
        super.onStop();SMLog.i();
        mState +="(D2)onStop->"; mStatus.setText(mState);
    }
/*
    @Override   // Activity only
    public void onRestart() {
        super.onRestart();SMLog.i();
        mState +="(D3-1)onRestart->"; mStatus.setText(mState);
    }
*/

    @Override   // Fragment only, the fragment returns to the layout from the back stack.
    public void onDestroyView() {
        super.onDestroyView();SMLog.i();
        mState +="(D3)onDestroyView->"; mStatus.setText(mState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();SMLog.i();
        mState +="(D4-0)onDestroy->"; mStatus.setText(mState);
    }

    @Override   // Fragment only
    public void onDetach() {
        super.onDetach();SMLog.i();
        mState +="(D5)onDetach->"; mStatus.setText(mState);
    }


}
