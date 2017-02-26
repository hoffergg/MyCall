package com.hoffer.test.callhistory.ui;

import com.hoffer.test.callhistory.model.CallLog;

import java.util.List;

/**
 * Created by hoffer on 17/2/25.
 */

public interface OnDataLoadListener {
    /**
     * call logs loaded
     * @param logs
     */
    void onLoaded(List<CallLog> logs);

    void onAddLog(CallLog log);

    void onRemoveLog(CallLog log);
}
