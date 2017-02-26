package com.hoffer.test.callhistory.task;

import android.content.Context;
import android.os.AsyncTask;

import com.hoffer.test.callhistory.model.CallLog;
import com.hoffer.test.callhistory.ui.ILogView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoffer on 17/2/25.
 */

public class ReadDataTask extends AsyncTask<String, Integer, List<CallLog>> {
    ILogView mLogView;
    Context mContext;

    public ReadDataTask(ILogView mLogView, Context mContext) {
        this.mLogView = mLogView;
        this.mContext = mContext;
    }

    @Override
    protected List<CallLog> doInBackground(String... params) {
        List<CallLog> logs = new ArrayList<>();
        CallLog log;
        String fileName = params[0];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(mContext.getAssets().open(fileName)));
            String mLine = reader.readLine();
            while (mLine != null) {
                log = getLog(mLine);
                if(log!=null)
                    logs.add(log);
                mLine = reader.readLine();
            }
            reader.close();
            return logs;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CallLog getLog(String line){
        String[] key = line.split("  ");
        if(key.length==5){
            return new CallLog(key[0],key[1],key[2],key[3],key[4]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<CallLog> calls) {
        super.onPostExecute(calls);
        mLogView.showLogs(calls);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
