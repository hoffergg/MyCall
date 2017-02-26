package com.hoffer.test.callhistory.ui;

import com.hoffer.test.callhistory.model.CallLog;

import java.util.List;

/**
 * Created by hehu on 17-2-26.
 */

public interface ILogView {

    void showLogs(List<CallLog> logs);

    void addLog(CallLog log);

    void removeLog(CallLog log);
}
