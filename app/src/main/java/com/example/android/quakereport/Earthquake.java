package com.example.android.quakereport;

public class Earthquake {

    private final double mMagnitude;
    private final String mLocation;
    private final long mDateAndTimeInMilliseconds;
    private final String mEventURL;

    public Earthquake(double magnitude, String location, long dateAndTimeInMilliseconds, String URL) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mDateAndTimeInMilliseconds = dateAndTimeInMilliseconds;
        this.mEventURL = URL;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getDateAndTimeInMilliseconds() {
        return mDateAndTimeInMilliseconds;
    }

    public String getEventURL() {
        return mEventURL;
    }
    
}
