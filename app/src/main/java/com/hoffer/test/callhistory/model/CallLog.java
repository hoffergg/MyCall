package com.hoffer.test.callhistory.model;

/**
 * Created by hoffer on 17/2/25.
 */

public class CallLog {
    String name;
    String number;
    String type;
    String time;
    String durationSec;

    public CallLog(String name, String number, String flag, String time, String durationSec) {
        this.name = name;
        this.number = number;
        this.type = flag;
        this.time = time;
        this.durationSec = durationSec;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getDurationSec() {
        return durationSec;
    }
}
