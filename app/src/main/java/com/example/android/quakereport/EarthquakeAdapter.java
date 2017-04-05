package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";
    private static final String LOCATION_NO_DISTANCE_PLACEHOLDER = "Near the";

    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.eqMagnitude = (TextView) convertView.findViewById(R.id.eq_magnitude);
            viewHolder.eqDistance = (TextView) convertView.findViewById(R.id.eq_distance);
            viewHolder.eqRegion = (TextView) convertView.findViewById(R.id.eq_region);
            viewHolder.eqDate = (TextView) convertView.findViewById(R.id.eq_date);
            viewHolder.eqTime = (TextView) convertView.findViewById(R.id.eq_time);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Earthquake currentEarthquake = getItem(position);

        double eqMagnitude = currentEarthquake.getMagnitude();
        DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
        viewHolder.eqMagnitude.setText(magnitudeFormatter.format(eqMagnitude));

        GradientDrawable magnitudeCircle = (GradientDrawable) viewHolder.eqMagnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(eqMagnitude);
        magnitudeCircle.setColor(magnitudeColor);

        String eqLocation = currentEarthquake.getLocation();
        if (eqLocation.contains(LOCATION_SEPARATOR)) {
            String[] eqLocationParts = eqLocation.split(LOCATION_SEPARATOR);
            String eqDistance = eqLocationParts[0] + LOCATION_SEPARATOR;
            String eqRegion = eqLocationParts[1];
            viewHolder.eqDistance.setText(eqDistance);
            viewHolder.eqRegion.setText(eqRegion);
        } else {
            viewHolder.eqDistance.setText(LOCATION_NO_DISTANCE_PLACEHOLDER);
            viewHolder.eqRegion.setText(eqLocation);
        }

        Date eqDateAndTimeInMilliseconds = new Date(currentEarthquake.getDateAndTimeInMilliseconds());
        SimpleDateFormat eqDateFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat eqTimeFormat = new SimpleDateFormat("H:mm");
        viewHolder.eqDate.setText(eqDateFormat.format(eqDateAndTimeInMilliseconds));
        viewHolder.eqTime.setText(eqTimeFormat.format(eqDateAndTimeInMilliseconds));

        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    static class ViewHolder {
        TextView eqMagnitude;
        TextView eqDistance;
        TextView eqRegion;
        TextView eqDate;
        TextView eqTime;
    }

}