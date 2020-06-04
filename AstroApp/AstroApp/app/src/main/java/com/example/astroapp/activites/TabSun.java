package com.example.astroapp.activites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.astroapp.GlobalValues;
import com.example.astroapp.R;

public class TabSun extends Fragment {

    private static TabSun instance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_sun, container, false);
        instance = this;
        return view;
    }

    public static TabSun getInstance() {
        return instance;
    }

    public void updateSunrise(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.sunrise);
        textView.setText(s);
        GlobalValues.sunrise = s;
    }

    public void updateSunset(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.sunset);
        textView.setText(s);
        GlobalValues.sunset = s;
    }

    public void updateTwilight(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.twilight);
        textView.setText(s);
        GlobalValues.twilight = s;
    }

    public void updateDaylight(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.daylight);
        textView.setText(s);
        GlobalValues.daylight = s;
    }


    @Override
    public void onViewStateRestored(@NonNull Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
            updateSunset(GlobalValues.sunset);
            updateSunrise(GlobalValues.sunrise);
            updateTwilight(GlobalValues.twilight);
            updateDaylight(GlobalValues.daylight);
    }

}