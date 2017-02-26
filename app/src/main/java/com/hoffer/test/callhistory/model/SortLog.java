package com.hoffer.test.callhistory.model;

import java.util.ArrayList;
import java.util.List;

/**
 * call log after sort
 * Created by hehu on 17-2-26.
 */

public class SortLog {
    private String number;
    private String name;
    private String type;
    private long lastTimeStamp;
    private String lastTime;

    private int count;
    private List<CallLog> logs;

    public SortLog(String number, String name, String type) {
        this.number = number;
        this.name = name;
        this.type = type;
    }

    public void addLog(CallLog log){
        long timeStamp = log.getTimeStamp();
        if(timeStamp>lastTimeStamp) {
            lastTime = log.getTime();
            lastTimeStamp = timeStamp;
        }
        if(logs == null)
            logs = new ArrayList<>();
        logs.add(log);
        count+=1;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLastTime() {
        return lastTime;
    }

    public int getCount() {
        return count;
    }

    public List<CallLog> getLogs() {
        return logs;
    }
}
