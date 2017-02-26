package com.hoffer.test.callhistory.ui;

import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hoffer.test.callhistory.R;
import com.hoffer.test.callhistory.model.CallLog;
import com.hoffer.test.callhistory.task.ReadDataTask;

import java.util.List;


/**
 * main activity
 */
public class MainActivity extends AppCompatActivity implements ILogView{
    private ViewPager mPager;
    private TabLayout mTab;
    private ILogView mLogFragment,mSortFragment;
    private Fragment[] fragments;
    private String[] mTitles = new String[] {"LOG","SORT"};
    private String fileName = "original.txt";
    private CountDownTimer removeTimer = new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long l) {
        }
        @Override
        public void onFinish() {
            CallLog callLog = new CallLog("张三", "85558888", "去电", "13:05:25", "100s");
            mLogFragment.removeLog(callLog);
            mSortFragment.removeLog(callLog);
            addTimer.start();
        }
    };
    private CountDownTimer addTimer = new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long l) {
        }
        @Override
        public void onFinish() {
            CallLog callLog = new CallLog("张三","85558888","去电","8:07:35","45s");
            mLogFragment.addLog(callLog);
            mSortFragment.addLog(callLog);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initView(){
        mPager = (ViewPager) findViewById(R.id.main_pager);
        mTab = (TabLayout) findViewById(R.id.main_tab);
        mLogFragment = new LogFragment();
        mSortFragment = new SortFragment();
        fragments = new Fragment[]{(Fragment) mLogFragment, (Fragment) mSortFragment};
        mPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mTab.setupWithViewPager(mPager);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        new ReadDataTask(this,this).execute(fileName);
    }

    @Override
    public void showLogs(List<CallLog> logs) {
        mSortFragment.showLogs(logs);
        mLogFragment.showLogs(logs);
        removeTimer.cancel();
        removeTimer.start();
    }

    @Override
    public void addLog(CallLog log) {

    }

    @Override
    public void removeLog(CallLog log) {

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
