package com.example.astroapp;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.SystemClock;
import android.util.Log;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import com.example.astroapp.activites.TabMoon;
import com.example.astroapp.activites.TabSun;

import java.text.DecimalFormat;

public class AstroCalculatorThread extends Thread {

    AstroDateTime astroDate;
    AstroCalculator astroCalculator;
    AstroCalculator.Location astroLocation;
    public boolean isRunning;
    TabSun sun;
    TabMoon moon;
    long freq;

    public AstroCalculatorThread() {
        freq = (long)GlobalValues.refreshFreq * (long)60000;
    }

    private void initAstro(){
        astroLocation = new com.astrocalculator.AstroCalculator.Location(GlobalValues.latitude, GlobalValues.longitude);
        Calendar c = Calendar.getInstance();

        //IConverter iConverter = Converter.getInstance(TimeZoneListStore.class);
       // TimeZone timeZone = iConverter.getTimeZone(GlobalValues.latitude, GlobalValues.longitude);

        astroDate = new AstroDateTime(Integer.parseInt(new SimpleDateFormat("yyyy").format(c.getTime())),
                Integer.parseInt(new SimpleDateFormat("MM").format(c.getTime())),
                Integer.parseInt(new SimpleDateFormat("dd").format(c.getTime())),
                Integer.parseInt(new SimpleDateFormat("hh").format(c.getTime())),
                Integer.parseInt(new SimpleDateFormat("mm").format(c.getTime())),
                Integer.parseInt(new SimpleDateFormat("ss").format(c.getTime())),
                2,
                false
        );

        astroCalculator = new com.astrocalculator.AstroCalculator(astroDate, astroLocation);
    }

    @Override
    public void run() {
        isRunning = true;

        while(isRunning){
            sun = TabSun.getInstance();
            moon = TabMoon.getInstance();
            SystemClock.sleep(100);
            try{
                initAstro();
                updateSun();
                updateMoon();
                SystemClock.sleep(freq);
            }catch (Exception e){
                continue;
            }

        }

    }

    private void updateSun(){
        String s, h, m;
        DecimalFormat df = new DecimalFormat("#.##");

        if(astroCalculator.getSunInfo().getTwilightMorning().getHour() < 10){
            h = ("0" + astroCalculator.getSunInfo().getTwilightMorning().getHour());
        }
        else{
            h = ("" + astroCalculator.getSunInfo().getTwilightMorning().getHour());
        }

        if(astroCalculator.getSunInfo().getTwilightMorning().getMinute() < 10){
            m = ("0" + astroCalculator.getSunInfo().getTwilightMorning().getMinute());
        }
        else{
            m = ("" + astroCalculator.getSunInfo().getTwilightMorning().getMinute());
        }

        s = (h + ":" + m + " | " + df.format(astroCalculator.getSunInfo().getAzimuthRise()) + (char)0x00B0);
        sun.updateSunrise(s);

        if(astroCalculator.getSunInfo().getTwilightEvening().getHour() < 10){
            h = ("0" + astroCalculator.getSunInfo().getTwilightEvening().getHour());
        }
        else{
            h = ("" + astroCalculator.getSunInfo().getTwilightEvening().getHour());
        }

        if(astroCalculator.getSunInfo().getTwilightEvening().getMinute() < 10){
            m = ("0" + astroCalculator.getSunInfo().getTwilightEvening().getMinute());
        }
        else{
            m = ("" + astroCalculator.getSunInfo().getTwilightEvening().getMinute());
        }

        s = (h + ":" + m + " | " + df.format(astroCalculator.getSunInfo().getAzimuthSet()) + (char)0x00B0);
        sun.updateSunset(s);

        if(astroCalculator.getSunInfo().getSunset().getHour() < 10){
            h = ("0" + astroCalculator.getSunInfo().getSunset().getHour());
        }
        else{
            h = ("" + astroCalculator.getSunInfo().getSunset().getHour());
        }

        if(astroCalculator.getSunInfo().getSunset().getMinute() < 10){
            m = ("0" + astroCalculator.getSunInfo().getSunset().getMinute());
        }
        else{
            m = ("" + astroCalculator.getSunInfo().getSunset().getMinute());
        }

        s = (h + ":" + m);
        sun.updateTwilight(s);

        if(astroCalculator.getSunInfo().getSunrise().getHour() < 10){
            h = ("0" + astroCalculator.getSunInfo().getSunrise().getHour());
        }
        else{
            h = ("" + astroCalculator.getSunInfo().getSunrise().getHour());
        }

        if( astroCalculator.getSunInfo().getSunrise().getMinute() < 10){
            m = ("0" +  astroCalculator.getSunInfo().getSunrise().getMinute());
        }
        else{
            m = ("" +  astroCalculator.getSunInfo().getSunrise().getMinute());
        }

        s = (h + ":" + m);
        sun.updateDaylight(s);
    }

    private void updateMoon(){
        String s, h, m;
        DecimalFormat df = new DecimalFormat("#.##");

        if(astroCalculator.getMoonInfo().getMoonrise().getHour() < 10){
            h = ("0" + astroCalculator.getMoonInfo().getMoonrise().getHour());
        }
        else{
            h = ("" + astroCalculator.getMoonInfo().getMoonrise().getHour());
        }

        if( astroCalculator.getMoonInfo().getMoonrise().getMinute() < 10){
            m = ("0" +  astroCalculator.getMoonInfo().getMoonrise().getMinute());
        }
        else{
            m = ("" +  astroCalculator.getMoonInfo().getMoonrise().getMinute());
        }

        s = (h + ":" + m);
        moon.updateMoonrise(s);

        if(astroCalculator.getMoonInfo().getMoonset().getHour() < 10){
            h = ("0" + astroCalculator.getMoonInfo().getMoonset().getHour());
        }
        else{
            h = ("" + astroCalculator.getMoonInfo().getMoonset().getHour());
        }

        if( astroCalculator.getMoonInfo().getMoonset().getMinute() < 10){
            m = ("0" +  astroCalculator.getMoonInfo().getMoonset().getMinute());
        }
        else{
            m = ("" +  astroCalculator.getMoonInfo().getMoonset().getMinute());
        }

        s = (h + ":" + m);
        moon.updateMoonset(s);



        s = (astroCalculator.getMoonInfo().getNextNewMoon().getDay() + "." +
                astroCalculator.getMoonInfo().getNextNewMoon().getMonth() + "." +
                astroCalculator.getMoonInfo().getNextNewMoon().getYear());
        moon.updateNewmoon(s);

        s = (astroCalculator.getMoonInfo().getNextFullMoon().getDay() + "." +
                astroCalculator.getMoonInfo().getNextFullMoon().getMonth() + "." +
                astroCalculator.getMoonInfo().getNextFullMoon().getYear());
        moon.updateFullmoon(s);

        s = df.format(astroCalculator.getMoonInfo().getIllumination() * 100.0);
        moon.updatePhase(s + "%");

        df = new DecimalFormat("#");
        s = df.format(astroCalculator.getMoonInfo().getAge());
        moon.updateSynodic("" +s);
    }

}
