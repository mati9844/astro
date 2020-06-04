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

public class TabMoon extends Fragment {

    private static TabMoon instance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_moon, container, false);
        instance = this;
        return view;
    }

    public static TabMoon getInstance() {
        return instance;
    }

    public void updateMoonrise(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.moonrise);
        textView.setText(s);
        GlobalValues.moonrise = s;
    }

    public void updateMoonset(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.moonset);
        textView.setText(s);
        GlobalValues.moonset = s;
    }

    public void updateNewmoon(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.newmoon);
        textView.setText(s);
        GlobalValues.newmoon = s;
    }

    public void updateFullmoon(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.fullmoon);
        textView.setText(s);
        GlobalValues.fullmoon = s;
    }

    public void updatePhase(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.phase);
        textView.setText(s);
        GlobalValues.phase = s;
    }

    public void updateSynodic(String s) {
        TextView textView = (TextView)getView().findViewById(R.id.synodic);
        textView.setText(s);
        GlobalValues.synodic = s;
    }

    @Override
    public void onViewStateRestored(@NonNull Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        updateSynodic(GlobalValues.synodic);
        updatePhase(GlobalValues.phase);
        updateFullmoon(GlobalValues.fullmoon);
        updateMoonset(GlobalValues.moonset);
        updateMoonrise(GlobalValues.moonrise);
        updateNewmoon(GlobalValues.newmoon);
    }
}