package com.hoffer.test.callhistory.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hoffer.test.callhistory.R;
import com.hoffer.test.callhistory.model.CallLog;

import java.util.List;

/**
 * call log detail activity
 */
public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_LOGS = "extra_logs";
    private ILogView mLogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        List<CallLog> callLogs = getIntent().getParcelableArrayListExtra(EXTRA_LOGS);
        mLogFragment = (ILogView) getSupportFragmentManager().findFragmentById(R.id.log_fragment);
        if(mLogFragment!=null)
            mLogFragment.showLogs(callLogs);
    }
}
