package com.hoffer.test.callhistory.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hoffer.test.callhistory.R;
import com.hoffer.test.callhistory.model.CallLog;
import com.hoffer.test.callhistory.ui.LogFragment;
import com.hoffer.test.callhistory.ui.SortFragment;

import java.util.List;


/**
 * main activity
 */
public class MainActivity extends AppCompatActivity implements OnDataLoadListener{
    private ViewPager mPager;
    private TabLayout mTab;
    private Fragment mLogFragment,mSortFragment;
    private Fragment[] fragments;
    private String[] mTitles = new String[] {"LOG","SORT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        mPager = (ViewPager) findViewById(R.id.main_pager);
        mTab = (TabLayout) findViewById(R.id.main_tab);
        mLogFragment = new LogFragment();
        mSortFragment = new SortFragment();
        fragments = new Fragment[]{mLogFragment,mSortFragment};
        mPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mTab.setupWithViewPager(mPager);
    }

    @Override
    public void onLoaded(List<CallLog> logs) {

    }

    public class MainPagerAdapter extends FragmentPagerAdapter{

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }
    }
}
