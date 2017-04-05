package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mLoadUrl;

    public EarthquakeLoader(Context context, String loadUrl) {
        super(context);
        this.mLoadUrl = loadUrl;
        Log.v(LOG_TAG, "EarthquakeLoader constructor invoked");
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG, "onStartLoading method invoked");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.v(LOG_TAG, "loadInBackground() method invoked");
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mLoadUrl == null || mLoadUrl == "") {
            return null;
        }
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakes(mLoadUrl);
        return earthquakes;
    }


}
