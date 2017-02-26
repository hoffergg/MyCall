package com.hoffer.test.callhistory.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * original call log
 * Created by hoffer on 17/2/25.
 */

public class CallLog implements Parcelable {
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

    public long getTimeStamp(){
        SimpleDateFormat sdf0 = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("H:mm:ss");
        try {
            return sdf0.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            return sdf1.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String getTime() {
        return time;
    }

    public String getDurationSec() {
        return durationSec;
    }


    @Override
    public String toString() {
        return "CallLog{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", durationSec='" + durationSec + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.number);
        dest.writeString(this.type);
        dest.writeString(this.time);
        dest.writeString(this.durationSec);
    }

    protected CallLog(Parcel in) {
        this.name = in.readString();
        this.number = in.readString();
        this.type = in.readString();
        this.time = in.readString();
        this.durationSec = in.readString();
    }

    public static final Parcelable.Creator<CallLog> CREATOR = new Parcelable.Creator<CallLog>() {
        @Override
        public CallLog createFromParcel(Parcel source) {
            return new CallLog(source);
        }

        @Override
        public CallLog[] newArray(int size) {
            return new CallLog[size];
        }
    };
}
