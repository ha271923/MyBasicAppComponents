package sample.hawk.com.mybasicappcomponents.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/11/14.
 */

public class MyTabsActivity extends FragmentActivity {

    private android.support.design.widget.TabLayout mTabs;

    private ViewPager mViewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytabs);

        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.tabs);
        mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter( viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
        mTabs.setupWithViewPager(mViewPager); // bind with pager
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        int pagecount=3;
        private int color[]=new int[]{android.R.color.holo_orange_light,android.R.color.holo_green_dark,android.R.color.holo_red_dark};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ViewPagerFragment.getInstance(position+1,color[position]);
        }

        @Override
        public int getCount() {
            return pagecount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page"+(position+1);
        }
    }

}
